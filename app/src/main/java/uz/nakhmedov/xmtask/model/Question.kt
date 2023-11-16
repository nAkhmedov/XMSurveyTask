package uz.nakhmedov.xmtask.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

@Stable
data class Question(
    val id: Int,
    val question: String,
    val answer: MutableState<String> = mutableStateOf(""),
    val submitCount: MutableState<Int> = mutableIntStateOf(0),
    val isSubmitButtonEnable: MutableState<Boolean> = mutableStateOf(false)
)
