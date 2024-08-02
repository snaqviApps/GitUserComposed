package sample.gituserappcomposed.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sample.gituserappcomposed.reposlist.data.repository.GitProfileRepositoryImpl
import sample.gituserappcomposed.reposlist.domain.repository.GitProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesGitProfileRepository(
        gitRepositoryImpl: GitProfileRepositoryImpl
    ): GitProfileRepository

}