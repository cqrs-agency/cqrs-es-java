package agency.cqrs.api.queries

interface QueryHandler<T : Query, R : QueryResult> {
    suspend fun execute(query: T): R
}
