package com.example.todocompose.presentation.ui.navigators

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.features.add.composes.AddNoteScreen
import com.example.todocompose.presentation.ui.features.add.model.AddNoteUIModel
import com.example.todocompose.presentation.ui.features.list.composes.NoteListScreen
import com.example.todocompose.presentation.ui.features.list.model.NoteListUIModel

internal const val NOTE_LIST = "noteList"
internal const val ADD_NOTE = "addNote"
internal const val EDIT_NOTE_ARGS = "editNote?noteId={noteId}&noteColor={noteColor}"
internal const val EDIT_NOTE_ARGS_VALUE = "editNote?noteId=%d&noteColor=%d"

@Composable
internal fun NavHostController.NavigateAppRoute(startAppRoute: AppRoute) {
    val navController = this
    val composableScreens = loadComposeScreens()
    NavHost(navController, startDestination = startAppRoute.routeName) {
        composableScreens.forEach { compScreen ->
            composable(compScreen.routeName, compScreen.routeArgs) {
                compScreen.generate(navController, it.arguments).invoke()
            }
        }

        /*  composable(AppRoute.NoteListRoute.routeName) {
              NoteListScreen().Generate(
                  navController = navController,
                  data = NoteListUIModel()
              )
          }

          composable(AppRoute.AddNoteRoute.routeName) {
              AddNoteScreen().Generate(
                  navController = navController,
                  data = AddNoteUIModel()
              )
          }*/

    }
}

private fun loadComposeScreens(): List<ComposeAppRouteScreen<*>> {

    return ArrayList<ComposeAppRouteScreen<*>>().apply {


        add(ComposeAppRouteScreen
            .ComposeAppRouteScreenBuilder.newBuilder<NoteListUIModel>(AppRoute.NoteListRoute.routeName)
            .addScreen(NoteListScreen(), NoteListUIModel())
            .build())

        add(ComposeAppRouteScreen
            .ComposeAppRouteScreenBuilder.newBuilder<AddNoteUIModel>(AppRoute.AddNoteRoute.routeName)
            .addScreen(AddNoteScreen(), AddNoteUIModel())
            .build())

        add(ComposeAppRouteScreen
            .ComposeAppRouteScreenBuilder.newBuilder<AddNoteUIModel>(AppRoute.EditNoteRoute.routeName)
            .addScreen(AddNoteScreen(), AddNoteUIModel())
            .addScreenArgs(navArgument(name = "noteId") {
                type = NavType.IntType
                defaultValue = -1
            })
            .addScreenArgs(navArgument(name = "noteColor") {
                type = NavType.IntType
                defaultValue = -1
            })
            .build())

    }

}


data class ComposeAppRouteScreen<T> constructor(
    val composeAppRouteScreenBuilder: ComposeAppRouteScreenBuilder<T>,
) {
    val routeName = composeAppRouteScreenBuilder.routeName
    val routeArgs = composeAppRouteScreenBuilder.routeArgs

    fun generate(navController: NavHostController, arguments: Bundle?): @Composable () -> Unit {

        composeAppRouteScreenBuilder.screen.putArgs(arguments)
        return {
            composeAppRouteScreenBuilder.screen.Generate(navController,
                composeAppRouteScreenBuilder.data!!)
        }

    }


    class ComposeAppRouteScreenBuilder<T> private constructor(val routeName: String) {

        lateinit var screen: CreateCompose<T>
        var data: T? = null
        var routeArgs = ArrayList<NamedNavArgument>(0)

        companion object {
            fun <T> newBuilder(routeName: String): ComposeAppRouteScreenBuilder<T> {
                return ComposeAppRouteScreenBuilder(routeName)
            }
        }

        fun addScreen(screen: CreateCompose<T>, data: T): ComposeAppRouteScreenBuilder<T> {
            return this.apply {
                this.screen = screen
                this.data = data
            }
        }

        fun addScreenArgs(namedNavArgument: NamedNavArgument): ComposeAppRouteScreenBuilder<T> {
            return this.apply {
                this.routeArgs.add(namedNavArgument)
            }
        }

        fun build(): ComposeAppRouteScreen<T> {
            return ComposeAppRouteScreen(this)

        }

    }


}