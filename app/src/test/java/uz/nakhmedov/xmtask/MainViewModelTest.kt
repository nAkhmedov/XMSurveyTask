package uz.nakhmedov.xmtask

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import uz.nakhmedov.xmtask.domain.GetQuestionsUseCase
import uz.nakhmedov.xmtask.domain.SubmitAnswerUseCase
import uz.nakhmedov.xmtask.model.Question
import uz.nakhmedov.xmtask.ui.main.MainViewModel
import uz.nakhmedov.xmtask.ui.main.SurveyEvent

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MainViewModel
    private val questionsUseCase: GetQuestionsUseCase = mockk()
    private val submitAnswerUseCase: SubmitAnswerUseCase = mockk()

    @Test
    fun `send OnAnswerTextChange event and receive new state`() = runTest {
        coEvery { questionsUseCase.invoke() } returns listOf(
            Question(1, "What is your hobby?"),
            Question(2, "What is your dream?"),
        )
        viewModel = MainViewModel(questionsUseCase, submitAnswerUseCase)
        val newText = "visit space"
        delay(1000)
        viewModel.process(SurveyEvent.OnAnswerTextChange(1, newText))
        assertTrue(viewModel.uiState.questions[1].answer.value == newText)
    }
}