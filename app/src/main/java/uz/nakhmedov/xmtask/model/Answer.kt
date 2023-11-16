package uz.nakhmedov.xmtask.model

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val id: Int,
    val answer: String
)
