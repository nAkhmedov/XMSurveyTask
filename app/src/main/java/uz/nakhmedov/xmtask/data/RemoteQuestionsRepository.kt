package uz.nakhmedov.xmtask.data

import uz.nakhmedov.xmtask.model.Answer
import uz.nakhmedov.xmtask.model.ApiQuestion
import uz.nakhmedov.xmtask.network.NetworkDataSource
import javax.inject.Inject

class RemoteQuestionsRepository @Inject constructor(
    private val network: NetworkDataSource
) : QuestionsRepository {
    override suspend fun getQuestions(): List<ApiQuestion> = network.getQuestions()
    override suspend fun submitAnswer(answer: Answer): Result {
        val response = network.submitAnswer(answer)
        return if (response.isSuccessful) {
            Result.Success
        } else {
            Result.Failure
        }
    }
}