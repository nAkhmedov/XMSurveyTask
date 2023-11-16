package uz.nakhmedov.xmtask.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.nakhmedov.xmtask.MainDispatcherRule
import uz.nakhmedov.xmtask.data.QuestionsRepository
import uz.nakhmedov.xmtask.model.ApiQuestion


class GetQuestionsUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var questionsUseCase: GetQuestionsUseCase
    private val repository: QuestionsRepository = mockk()

    @Before
    fun init() {
        questionsUseCase = GetQuestionsUseCase(repository = repository)
    }

    @Test
    fun `load questions`() = runTest {
        val result = listOf(
            ApiQuestion(1, "What is your hobby?"),
            ApiQuestion(2, "What is your dream?"),
        )
        coEvery { repository.getQuestions() } returns result

        val items = questionsUseCase.invoke()
        assert(result.size == items.size)
    }

}