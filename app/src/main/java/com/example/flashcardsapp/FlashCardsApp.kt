package com.example.flashcardsapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashcardsapp.ui.screens.createExercise.CreateExerciseScreen
import com.example.flashcardsapp.ui.screens.homePage.AssuntosScreen
import com.example.flashcardsapp.ui.screens.subjectDetail.SubjectDetailScreen
import com.example.flashcardsapp.ui.viewmodels.AppViewModel

@Composable
fun FlashCardsApp(username: String) {
    Text(
        text = "Bem-vindo ao FlashCardsApp, $username!",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
    )
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()

    NavHost(navController = navController, startDestination = "assuntos") {

        composable("assuntos") {
            AssuntosScreen(
                viewModel = appViewModel,
                onNavigateToSubject = { subject ->
                    navController.navigate("subject_detail/${subject.id}")
                }
            )
        }

        composable(
            route = "subject_detail/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getInt("subjectId") ?: return@composable
            SubjectDetailScreen(
                subjectId = subjectId,
                viewModel = appViewModel,
                onBackClick = { navController.popBackStack() },
                onNavigateToCreateExercise = { subjectId ->
                    navController.navigate("create_exercise/$subjectId")
                }
            )
        }

        composable (
            route = "create_exercise/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.IntType })
        ){
                backStackEntry ->
            val subjectId = backStackEntry.arguments?.getInt("subjectId") ?: return@composable
            CreateExerciseScreen(
                subjectId = subjectId,
                viewModel = appViewModel,
                onBackClick = { navController.popBackStack() },
                onNavigateToCreateQuizEercise = {},
                onNavigateToCreateBasicExercise = {},
                onNavigateToClozeExercise = {}
            )

        }
    }
}