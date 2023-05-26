package agency.cqrs.api.queries

interface QueryHandler<T : Query, R : QueryResult> {
    fun execute(query: T): R
}
