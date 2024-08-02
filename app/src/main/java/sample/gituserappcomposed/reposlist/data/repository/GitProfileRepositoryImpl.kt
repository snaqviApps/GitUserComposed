package sample.gituserappcomposed.reposlist.data.repository

import sample.gituserappcomposed.reposlist.data.remote.response.GithubReposDTO
import sample.gituserappcomposed.reposlist.data.remote.GitUserRestApi
import sample.gituserappcomposed.reposlist.domain.repository.GitProfileRepository
import sample.gituserappcomposed.reposlist.presentation.GitUserRepos
import javax.inject.Inject

/**
 * This class implement repository interface,
 * more it comes with some 'Notes' on different approaches of 'Flow'
 */
class GitProfileRepositoryImpl @Inject constructor(
    private val gitUserRestApi: GitUserRestApi,
 ) : GitProfileRepository {

    override suspend fun getProfiles(): GitUserRepos {
        val remoteResultRepoState: GitUserRepos = try {
            GitUserRepos.Success( gitUserRestApi.getGithubReposList("snaqviApps") as List<GithubReposDTO>)
//            GitUserRepos.Success( gitUserRestApi.getGithubReposList("") as List<GithubReposDTO>)
        }catch (e: retrofit2.HttpException){
            GitUserRepos.Error((e.message as String).plus(" page not found "))
        }
        return remoteResultRepoState
    }

}