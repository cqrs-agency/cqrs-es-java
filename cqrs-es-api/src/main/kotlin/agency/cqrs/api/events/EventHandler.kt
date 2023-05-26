package agency.cqrs.api.events

interface EventHandler<T : Event> {
    fun handle(event: T)
}
