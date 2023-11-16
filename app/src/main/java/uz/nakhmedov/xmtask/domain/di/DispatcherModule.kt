package uz.nakhmedov.xmtask.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @Dispatcher(DispatchersEnum.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: DispatchersEnum)

enum class DispatchersEnum {
    IO,
    Default,
    Computation
}