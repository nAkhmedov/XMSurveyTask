package uz.nakhmedov.xmtask.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nakhmedov.xmtask.data.QuestionsRepository
import uz.nakhmedov.xmtask.data.RemoteQuestionsRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsQuestionRepository(repository: RemoteQuestionsRepository): QuestionsRepository
}