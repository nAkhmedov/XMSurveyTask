package uz.nakhmedov.xmtask.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.nakhmedov.xmtask.MainDispatcherRule
import uz.nakhmedov.xmtask.data.QuestionsRepository
import uz.nakhmedov.xmtask.data.Result
import uz.nakhmedov.xmtask.model.Answer


class SubmitAnswerUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var submitAnswerUseCase: SubmitAnswerUseCase
    private val repository: QuestionsRepository = mockk()

    @Before
    fun init() {
        submitAnswerUseCase = SubmitAnswerUseCase(repository = repository)
    }

    @Test
    fun `submit answer and receive Success`() = runTest {
        val answer = Answer(1, "visit space")
        coEvery { repository.submitAnswer(answer) } returns Result.Success
        val result = submitAnswerUseCase.invoke(answer)
        assertTrue(result == Result.Success)
    }

    @Test
    fun `submit answer and receive Failure`() = runTest {
        val answer = Answer(1, "football")
        coEvery { repository.submitAnswer(answer) } returns Result.Failure
        val result = submitAnswerUseCase.invoke(answer)
        assertTrue(result == Result.Failure)
    }
}