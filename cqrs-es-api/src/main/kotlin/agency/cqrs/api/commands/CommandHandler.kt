package agency.cqrs.api.commands

interface CommandHandler<T : Command, R : Any> {
    suspend fun execute(command: T): R
}
