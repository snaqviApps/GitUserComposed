package sample.gituserappcomposed

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sample.gituserappcomposed.reposlist.presentation.view.screen.PresentHomeScreen
import sample.gituserappcomposed.reposlist.presentation.view.viewmodel.GitUserViewModel
import sample.gituserappcomposed.reposlist.util.Screen
import sample.gituserappcomposed.ui.theme.GitUserAppComposedTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GitUserViewModel by viewModels() // as it is instance, we can

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitUserAppComposedTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.screenId
                ) {
                    composable(Screen.Home.screenId) {
                        PresentHomeScreen(viewModel, navController)
                    }
                    composable(
                        Screen.Details.screenId
//                        , arguments = listOf(
//                            navArgument("repoName") {
//                                type = NavType.StringType
//                            }
//                        )
                    )
                    {
                        DetailScreen(navController)
                    }
                }

            }
        }
    }

    @Composable
    fun DetailScreen(navController: NavHostController) {
        ElevatedButton(onClick = {
            navController.popBackStack()
        })
        {
            Text("Go back")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Detail Screen")
    }

}