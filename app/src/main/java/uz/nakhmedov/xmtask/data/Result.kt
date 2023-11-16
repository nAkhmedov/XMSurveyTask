package uz.nakhmedov.xmtask.data

sealed interface Result {
    data object Success : Result
    data object Failure : Result
}