package uz.nakhmedov.xmtask.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.nakhmedov.xmtask.data.Result
import uz.nakhmedov.xmtask.domain.GetQuestionsUseCase
import uz.nakhmedov.xmtask.domain.SubmitAnswerUseCase
import uz.nakhmedov.xmtask.model.Answer
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val questionUseCase: GetQuestionsUseCase,
    private val submitAnswerUseCase: SubmitAnswerUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SurveyState())
        private set

    private val submitAnswerResult = MutableStateFlow<Result?>(null)
    val onSubmitAnswerResult by lazy { submitAnswerResult.asStateFlow() }

    init {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val newsItems = questionUseCase.invoke()
            uiState = uiState.copy(questions = newsItems)
            uiState = uiState.copy(isLoading = false)
        }
    }

    fun process(event: SurveyEvent) {
        when (event) {
            is SurveyEvent.OnAnswerTextChange -> onAnswerTextChange(event.currentIndex, event.text)
            is SurveyEvent.OnSubmitClick -> onSubmitClick(event.id, event.text)
        }
    }

    private fun onAnswerTextChange(currentIndex: Int, text: String) {
        with(uiState.questions[currentIndex]) {
            answer.value = text
            isSubmitButtonEnable.value = validateSubmitButton(this.submitCount.value, text)
        }
    }

    private fun validateSubmitButton(submitCount: Int, text: String): Boolean {
        return !(submitCount > 0 || text.isBlank())
    }

    private fun onSubmitClick(id: Int, text: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = submitAnswerUseCase.invoke(Answer(id, text))
            uiState = uiState.copy(isLoading = false)
            when (result) {
                Result.Failure -> {
                    submitAnswerResult.emit(Result.Failure)
                }

                Result.Success -> {
                    with(uiState.questions.find { it.id == id }) {
                        this?.submitCount?.value = 1
                        this?.isSubmitButtonEnable?.value = false
                    }
                    submitAnswerResult.emit(Result.Success)
                }
            }
        }
    }
}