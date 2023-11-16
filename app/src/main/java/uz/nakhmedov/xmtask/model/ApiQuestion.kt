package uz.nakhmedov.xmtask.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiQuestion(
    val id: Int,
    val question: String
)