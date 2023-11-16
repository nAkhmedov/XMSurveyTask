package uz.nakhmedov.xmtask.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import uz.nakhmedov.xmtask.data.QuestionsRepository
import uz.nakhmedov.xmtask.data.Result
import uz.nakhmedov.xmtask.domain.di.Dispatcher
import uz.nakhmedov.xmtask.domain.di.DispatchersEnum
import uz.nakhmedov.xmtask.model.Answer
import javax.inject.Inject

class SubmitAnswerUseCase @Inject constructor(
    private val repository: QuestionsRepository,
    @Dispatcher(DispatchersEnum.IO) private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(answer: Answer): Result {
        return withContext(dispatcher) {
            repository.submitAnswer(answer)
        }
    }
}