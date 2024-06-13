package com.example.onthi.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onthi.repository.RepositorySanPham
import com.example.onthi.room.DBHelper
import com.example.onthi.screen.Home
import com.example.onthi.screen.Welcome
import com.example.onthi.viewmodel.SanPhamViewModel

@Composable
fun NavApp(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = DBHelper.getIntance(context)
    val repository = RepositorySanPham(db)
    val viewModel = SanPhamViewModel(repository)

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route){ Welcome(navController)}
        composable(Screen.Home.route){ Home(navController,viewModel) }
    }
}