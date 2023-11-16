package uz.nakhmedov.xmtask.network

import retrofit2.Response
import uz.nakhmedov.xmtask.model.Answer
import uz.nakhmedov.xmtask.model.ApiQuestion

interface NetworkDataSource {
    suspend fun getQuestions(): List<ApiQuestion>
    suspend fun submitAnswer(answer: Answer): Response<Unit>
}