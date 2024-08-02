package sample.gituserappcomposed.reposlist.util

/**
 * borrowed from
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
sealed class Screen(val screenId: String) {
    data object Home : Screen("main")
    data object MainReposList : Screen("reposOverview")
    data object Details : Screen("repoDetails")
}