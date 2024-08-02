package sample.gituserappcomposed.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.gituserappcomposed.BuildConfig.BASE_URL
import sample.gituserappcomposed.reposlist.data.remote.GitUserRestApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GitRepositoryModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor = interceptor)
        .build()

    @Provides
    @Singleton
    fun providesGithubReposApi(): GitUserRestApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())         // airplane-mode ON causes crash
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GitUserRestApi::class.java)
    }

}


