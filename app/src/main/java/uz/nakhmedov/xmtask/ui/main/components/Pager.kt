package uz.nakhmedov.xmtask.ui.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nakhmedov.xmtask.R
import uz.nakhmedov.xmtask.ui.main.SurveyState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    uiState: SurveyState,
    pagerState: PagerState,
    onAnswerTextChange: (currentIndex: Int, text: String) -> Unit,
    onSubmitClick: (id: Int, text: String) -> Unit
) {
    HorizontalPager(
        modifier = Modifier
            .fillMaxSize(),
        state = pagerState,
    ) { page ->
        val currentQuestion = uiState.questions[page]
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = colorResource(id = R.color.white)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(
                        id = R.string.questions_submited,
                        currentQuestion.submitCount.value
                    )
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp)
                    .weight(1f),
                text = currentQuestion.question,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                value = currentQuestion.answer.value,
                onValueChange = {
                    onAnswerTextChange(pagerState.currentPage, it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.type_here_for_answer),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(2f),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    onClick = {
                        onSubmitClick(currentQuestion.id, currentQuestion.answer.value)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white),
                        contentColor = colorResource(id = R.color.blue)
                    ),
                    enabled = currentQuestion.isSubmitButtonEnable.value
                ) {
                    Text(
                        text = stringResource(id = R.string.submit),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }
            }
        }
    }
}