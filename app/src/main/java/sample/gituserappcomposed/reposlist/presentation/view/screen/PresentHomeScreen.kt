package sample.gituserappcomposed.reposlist.presentation.view.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import sample.gituserappcomposed.reposlist.presentation.GitUserRepos
import sample.gituserappcomposed.reposlist.presentation.view.viewmodel.GitUserViewModel
import sample.gituserappcomposed.reposlist.util.Screen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentHomeScreen(viewModel: GitUserViewModel, navController: NavHostController?) {

//    below viewModel-instance is from MainActivity, it works too
//    val stateValueCollected = viewModel.state.collectAsState().value
    val viewModelUsingHilt = hiltViewModel<GitUserViewModel>()
    val stateValueCollected = viewModelUsingHilt.state.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.secondary,
                ),
                title = {
                    Text(
                        "",

                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Row {
                    ElevatedButton(
                        onClick = {
                            navController?.navigate(route = Screen.Details.screenId)
                            Log.d("TAG_Button1", "PresentHomeScreen: ")
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1f),
                    ) {
                        Text("Load Repositories")
                    }
                    ElevatedButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1f),
                    ) {
                        Text("Repository Details")
                    }
                }
            }

        }
    )
    { paddingValues ->
        Text(
            text = "Home Screen",
            modifier = Modifier.padding(paddingValues)
        )
        when (stateValueCollected) {
            is GitUserRepos.Empty -> {
                Text(text = "No repositories found")
                Toast.makeText(LocalContext.current, "No repositories found", Toast.LENGTH_SHORT).show()
            }

            is GitUserRepos.Success -> {
                LazyVerticalGrid(
                    GridCells.Fixed(2),
                    modifier = Modifier.scrollable(
                        orientation = Orientation.Vertical,
                        state = rememberScrollState()
                    ),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    items(
                        stateValueCollected.repos.size
                    ) { index ->
                        RowItem(
                            repo = stateValueCollected.repos[index],
                            navController = navController
                        )
                    }
                }
            }

            is GitUserRepos.Error -> {
                Text(text = stateValueCollected.toString())
            }

            is GitUserRepos.Loading -> TODO()
        }
    }
}