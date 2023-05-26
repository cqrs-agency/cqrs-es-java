package agency.cqrs.api.events

interface EventHandler<T : Event> {
    suspend fun handle(event: T)
}
