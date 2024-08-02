package sample.gituserappcomposed.reposlist.data.remote

import sample.gituserappcomposed.reposlist.data.remote.response.GithubReposListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitUserRestApi {
    @GET("{username}/repos")
    suspend fun getGithubReposList (
        @Path("username") username: String = "",      // default
    ) : GithubReposListDTO?
}