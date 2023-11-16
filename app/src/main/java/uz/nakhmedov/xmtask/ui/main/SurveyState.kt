package uz.nakhmedov.xmtask.ui.main

import uz.nakhmedov.xmtask.model.Question

data class SurveyState(
    val isLoading: Boolean = false,
    val isPreviousButtonEnabled: Boolean = false,
    val isNextButtonEnabled: Boolean = true,
    val questions: List<Question> = listOf(),
)

sealed interface SurveyEvent {
    data class OnAnswerTextChange(val currentIndex: Int, val text: String) : SurveyEvent
    data class OnSubmitClick(val id: Int, val text: String) : SurveyEvent
}