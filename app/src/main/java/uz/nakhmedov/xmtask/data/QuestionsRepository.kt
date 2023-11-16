package uz.nakhmedov.xmtask.data

import uz.nakhmedov.xmtask.model.Answer
import uz.nakhmedov.xmtask.model.ApiQuestion

interface QuestionsRepository {
    suspend fun getQuestions(): List<ApiQuestion>
    suspend fun submitAnswer(answer: Answer): Result
}