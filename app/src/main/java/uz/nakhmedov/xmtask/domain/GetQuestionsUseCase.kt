package uz.nakhmedov.xmtask.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import uz.nakhmedov.xmtask.data.QuestionsRepository
import uz.nakhmedov.xmtask.domain.di.Dispatcher
import uz.nakhmedov.xmtask.domain.di.DispatchersEnum
import uz.nakhmedov.xmtask.model.Question
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: QuestionsRepository,
    @Dispatcher(DispatchersEnum.IO) private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): List<Question> {
        return withContext(dispatcher) {
            repository.getQuestions().map { Question(it.id, it.question) }
        }
    }
}