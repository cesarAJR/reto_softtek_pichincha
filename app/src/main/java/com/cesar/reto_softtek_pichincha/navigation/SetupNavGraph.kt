package com.cesar.reto_softtek_pichincha.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cesar.domain.model.Recipe
import com.cesar.reto_softtek_pichincha.presentation.detail.DetailScreen
import com.cesar.reto_softtek_pichincha.presentation.list.ListScreen
import com.cesar.reto_softtek_pichincha.presentation.onBoarding.OnBoardingScreen
import com.cesar.reto_softtek_pichincha.presentation.search.SearchScreen

import com.google.gson.Gson

@Composable
fun SetupNavGraph(navController: NavHostController,startDestination:String) {
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.List.route){
            ListScreen(onDetail = {
                val recipeJson = Gson().toJson(it)
                navController.navigate(Screen.Detail.createRoute(recipeJson))
            }, onSearch = {
                val recipeListJson = Gson().toJson(it)
                navController.navigate(Screen.Search.createRoute(recipeListJson))
             }
            )
        }

        composable(route = Screen.OnBoarding.route){
            OnBoardingScreen{
                navController.popBackStack()
                navController.navigate(Screen.List.route)
            }
        }

        composable(route = Screen.Search.route,
            arguments = listOf(
                navArgument("recipes"){defaultValue= "" }
            )){
            SearchScreen(
                onBack = {
                    navController.navigateUp()
                },
                onDetail = {
                    val recipeJson = Gson().toJson(it)
                    navController.navigate(Screen.Detail.createRoute(recipeJson))
                })
        }

        composable(route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("recipe"){defaultValue=""}
                )
            ){
             val recipeJson = it.arguments?.getString("recipe")
            val recipe = Gson().fromJson(recipeJson,Recipe::class.java)
            requireNotNull(recipe)
            DetailScreen(recipe = recipe){
                navController.navigateUp()
            }
        }
    }
    
}