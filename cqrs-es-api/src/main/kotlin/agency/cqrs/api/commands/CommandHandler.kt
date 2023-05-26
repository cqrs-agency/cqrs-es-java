package agency.cqrs.api.commands

interface CommandHandler<T : Command, R : Any> {
    fun execute(command: T): Result<R>
}
