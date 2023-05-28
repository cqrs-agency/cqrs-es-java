package agency.cqrs.core

import agency.cqrs.api.commands.Command
import kotlin.reflect.KClass

internal data class CommandHandlerId(
    internal val commandClass: KClass<out Command>,
    internal val resultClass: KClass<out Any>
)
