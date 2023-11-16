package uz.nakhmedov.xmtask.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import uz.nakhmedov.xmtask.data.Result
import uz.nakhmedov.xmtask.ui.main.components.Pager
import uz.nakhmedov.xmtask.ui.main.components.TopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    onBackClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val pagerState = rememberPagerState(pageCount = {
        uiState.questions.size
    })
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val submitResult by viewModel.onSubmitAnswerResult.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(submitResult) {
        if (submitResult == Result.Success) {
            snackbarHostState.showSnackbar("Success")
        } else if (submitResult == Result.Failure) {
            snackbarHostState.showSnackbar(
                "Failure",
                withDismissAction = true,
                actionLabel = "Retry",
                duration = SnackbarDuration.Short
            )
        }
    }


    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            TopBar(
                pagerState.canScrollBackward,
                pagerState.canScrollForward,
                onBackClick = onBackClick,
                onPreviousClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                onNextClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .border(2.dp, MaterialTheme.colorScheme.secondary)
                        .padding(12.dp),
                    action = {
                        TextButton(
                            onClick = { data.performAction() },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.inversePrimary
                            )

                        ) { Text(data.visuals.actionLabel ?: "") }
                    }
                ) {
                    Text(data.visuals.message)
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

            } else {
                Pager(
                    uiState,
                    pagerState,
                    onAnswerTextChange = { currentIndex, text ->
                        viewModel.process(SurveyEvent.OnAnswerTextChange(currentIndex, text))
                    },
                    onSubmitClick = { id, text ->
                        viewModel.process(SurveyEvent.OnSubmitClick(id, text))
                    }
                )
            }
        }
    }
}