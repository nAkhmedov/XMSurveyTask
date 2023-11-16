package uz.nakhmedov.xmtask.network.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.nakhmedov.xmtask.model.Answer
import uz.nakhmedov.xmtask.model.ApiQuestion

interface ApiService {
    @GET(value = "questions")
    suspend fun getQuestions(): List<ApiQuestion>

    @POST(value = "question/submit")
    suspend fun submitAnswer(@Body answer: Answer): Response<Unit>
}