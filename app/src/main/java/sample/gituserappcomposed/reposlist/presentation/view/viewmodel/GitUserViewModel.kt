package sample.gituserappcomposed.reposlist.presentation.view.viewmodel

import android.net.http.NetworkException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sample.gituserappcomposed.reposlist.domain.repository.GitProfileRepository
import sample.gituserappcomposed.reposlist.presentation.GitUserRepos
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class GitUserViewModel @Inject constructor(
    private val gitProfileRepository: GitProfileRepository
) : ViewModel() {
    private val _state = MutableStateFlow<GitUserRepos>(GitUserRepos.Empty(""))
    val state = _state.asStateFlow()

    init {
        getGitProfile()
    }

    private fun getGitProfile(): Job = viewModelScope.launch(Dispatchers.IO)
    {
        try {
            when (val response = gitProfileRepository.getProfiles()) {
                is GitUserRepos.Empty -> {
                    _state.update { GitUserRepos.Empty("") }
                }
                is GitUserRepos.Error -> {
                    _state.update { GitUserRepos.Error(response.error) }
                }

                is GitUserRepos.Loading -> {
                    _state.update { GitUserRepos.Loading(true) }
                }
                is GitUserRepos.Success -> {
                    _state.update {
                        GitUserRepos.Empty("")
                        GitUserRepos.Success(response.repos)
                    }
                }
            }
        } catch (e: Exception) {
            _state.update { failedState ->
                when (failedState) {
                    is GitUserRepos.Empty -> {
                        GitUserRepos.Empty("${e.cause?.message?.substring(0, 26)}")
                    }

                    is GitUserRepos.Error -> GitUserRepos.Empty("")
                    is GitUserRepos.Loading -> GitUserRepos.Empty("")
                    is GitUserRepos.Success -> GitUserRepos.Empty("")
                }
            }
        } catch (ne: NetworkException) {
            Log.e("ne-exception-log", ne.printStackTrace().toString())
            GitUserRepos.Error("http failure, reason: ${ne.message as String}")
        }
    }


}