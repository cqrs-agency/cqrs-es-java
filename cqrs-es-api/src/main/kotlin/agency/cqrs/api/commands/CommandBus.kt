package agency.cqrs.api.commands

interface CommandBus {
    fun <T : Command, R : Any> execute(command: T, resultClass: Class<R>): Result<R>
}
