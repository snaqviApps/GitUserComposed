package sample.gituserappcomposed.reposlist.presentation

import sample.gituserappcomposed.reposlist.data.remote.response.GithubReposDTO

sealed interface GitUserRepos {
    data class Success(val repos: List<GithubReposDTO> = emptyList()) : GitUserRepos
    data class Error(val error: String) : GitUserRepos
    class Loading(val isLoading: Boolean) : GitUserRepos
    class Empty(val exceptionMsg: String) : GitUserRepos

}