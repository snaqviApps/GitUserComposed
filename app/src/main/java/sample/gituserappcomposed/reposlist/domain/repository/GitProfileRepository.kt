package sample.gituserappcomposed.reposlist.domain.repository

import sample.gituserappcomposed.reposlist.presentation.GitUserRepos

interface GitProfileRepository {
    suspend fun getProfiles(): GitUserRepos
}