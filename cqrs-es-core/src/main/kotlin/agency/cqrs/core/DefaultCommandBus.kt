package agency.cqrs.core

import agency.cqrs.api.commands.Command
import agency.cqrs.api.commands.CommandBus
import agency.cqrs.api.commands.CommandHandler
import agency.cqrs.core.exceptions.CommandHandlerNotRegisteredException
import java.lang.reflect.ParameterizedType

internal class DefaultCommandBus(
    commandHandlers: Set<CommandHandler<out Command, *>>
) : CommandBus {
    private val commandHandlers: Map<CommandHandlerId, CommandHandler<out Command, *>> =
        commandHandlers.associateBy { it.commandAndResultClass() }

    override fun <T : Command, R : Any> execute(command: T, resultClass: Class<R>): Result<R> {
        val commandHandlerId = CommandHandlerId(command::class, resultClass.kotlin)

        @Suppress("UNCHECKED_CAST")
        val commandHandler = commandHandlers[commandHandlerId] as? CommandHandler<T, R>
        return commandHandler?.execute(command)
            ?: Result.failure(CommandHandlerNotRegisteredException(commandHandlerId))
    }

    private fun <T : Command, R : Any> CommandHandler<T, R>.commandAndResultClass(): CommandHandlerId {
        val parameterizedType = javaClass.genericInterfaces
            .filterIsInstance<ParameterizedType>()
            .first { (it.rawType as Class<*>).isAssignableFrom(CommandHandler::class.java) }

        @Suppress("UNCHECKED_CAST")
        val commandType = (parameterizedType.actualTypeArguments[0] as Class<T>).kotlin

        @Suppress("UNCHECKED_CAST")
        val resultType = (parameterizedType.actualTypeArguments[1] as Class<R>).kotlin
        return CommandHandlerId(commandType, resultType)
    }
}
