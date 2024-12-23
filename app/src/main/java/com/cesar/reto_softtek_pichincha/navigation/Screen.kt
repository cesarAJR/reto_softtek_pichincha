package com.cesar.reto_softtek_pichincha.navigation

sealed class Screen(val route : String){
    object Splash : Screen("splash_screen")
    object OnBoarding : Screen("on_boarding_screen")

    object List : Screen("list_screen")

    object Search : Screen("search_screen/?recipes={recipes}"){
        fun createRoute(recipes:String) = "search_screen/?recipes=$recipes"
    }

    object Detail : Screen("detail_screen/?recipe={recipe}"){
        fun createRoute(recipe:String) = "detail_screen/?recipe=$recipe"
    }
}