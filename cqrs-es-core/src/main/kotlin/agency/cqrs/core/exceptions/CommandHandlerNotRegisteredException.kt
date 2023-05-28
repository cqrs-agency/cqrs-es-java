package agency.cqrs.core.exceptions

import agency.cqrs.core.CommandHandlerId

data class CommandHandlerNotRegisteredException internal constructor(
    private val commandHandlerId: CommandHandlerId
) : Exception(
    "Command handler not registered for command=${commandHandlerId.commandClass.qualifiedName} and result=${commandHandlerId.resultClass.qualifiedName}"
)
