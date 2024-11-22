package com.e.rezende.notas.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.e.rezende.notas.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.e.rezende.notas.feature_note.presentation.notes.NotesScreen
import com.e.rezende.notas.feature_note.presentation.util.Screen
import com.e.rezende.notas.ui.theme.NOTASTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NOTASTheme {
                Surface(
                   color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen,
                    ) {
                        composable<Screen.NotesScreen>(
                            enterTransition = ::slideInToRight,
                            exitTransition = ::slideOutLeft
                        ) {
                            NotesScreen(
                                navController = navController
                            )
                        }
                        composable<Screen.AddEditNoteScreen>(
                            enterTransition = ::slideInToLeft,
                            exitTransition = ::slideOutRight
                        ){
                            val args = it.toRoute<Screen.AddEditNoteScreen>()

                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = args.noteColor
                            )
                        }
                    }
                }
            }
        }
    }

    fun slideInToLeft(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
        return scope.slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(300)
        )
    }

    fun slideInToRight(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
        return scope.slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(300)
        )
    }

    fun slideOutLeft(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
        return scope.slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(300)
        )
    }

    fun slideOutRight(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
        return scope.slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(300)
        )
    }
}