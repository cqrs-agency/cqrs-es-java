package agency.cqrs.core

import agency.cqrs.api.commands.Command
import agency.cqrs.api.commands.CommandHandler
import agency.cqrs.core.exceptions.CommandHandlerNotRegisteredException
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val TEXT_FIXTURE = "Great success!"
private const val LONG_FIXTURE = 10L

internal class DefaultCommandBusTest {
    @Test
    fun `should execute command handler for given class`() {
        // Given
        val returnStringCommandHandler = ReturnStringCommandHandler()
        val returnLongCommandHandler = ReturnLongCommandHandler()
        val commandBus = DefaultCommandBus(setOf(returnStringCommandHandler, returnLongCommandHandler))

        // When
        val actual = commandBus.execute(ReturnStringCommand(), String::class.java)

        // Then
        assertEquals(expected = TEXT_FIXTURE, actual = actual.getOrThrow())
    }

    @Test
    fun `should throw exception when no command handler exists for given command`() {
        // Given
        val returnStringCommandHandler = ReturnStringCommandHandler()
        val commandBus = DefaultCommandBus(setOf(returnStringCommandHandler))

        // When
        val actual = commandBus.execute(ReturnLongCommand(), Long::class.java)

        // Then
        assertTrue { actual.isFailure }
        assertEquals(
            expected = CommandHandlerNotRegisteredException(CommandHandlerId(ReturnLongCommand::class, Long::class)),
            actual = actual.exceptionOrNull()
        )
    }

    inner class ReturnStringCommand : Command

    inner class ReturnStringCommandHandler : CommandHandler<ReturnStringCommand, String> {
        override fun execute(command: ReturnStringCommand): Result<String> = Result.success(TEXT_FIXTURE)
    }

    inner class ReturnLongCommand : Command

    inner class ReturnLongCommandHandler : CommandHandler<ReturnLongCommand, Long> {
        override fun execute(command: ReturnLongCommand): Result<Long> = Result.success(LONG_FIXTURE)
    }
}
