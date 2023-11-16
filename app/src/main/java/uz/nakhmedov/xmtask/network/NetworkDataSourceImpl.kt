package uz.nakhmedov.xmtask.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import uz.nakhmedov.xmtask.model.Answer
import uz.nakhmedov.xmtask.model.ApiQuestion
import uz.nakhmedov.xmtask.network.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://xm-assignment.web.app/"

@Singleton
class NetworkDataSourceImpl @Inject constructor(
    networkJson: Json,
    okhttpFactory: Call.Factory
) : NetworkDataSource {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(okhttpFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(ApiService::class.java)

    override suspend fun getQuestions(): List<ApiQuestion> = api.getQuestions()
    override suspend fun submitAnswer(answer: Answer): Response<Unit> = api.submitAnswer(answer)
}