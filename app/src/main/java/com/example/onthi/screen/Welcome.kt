package com.example.onthi.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onthi.R
import com.example.onthi.navigator.Screen
import kotlinx.coroutines.delay

@Composable
fun Welcome(navController: NavController){
    LaunchedEffect(key1 = "") {
        delay(2000)
        navController.navigate(Screen.Home.route){
            popUpTo(Screen.Welcome.route){
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "",
            modifier = Modifier
                .size(240.dp, 240.dp)
                .clip(RoundedCornerShape(10.dp)))
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "MSSV : PH.......", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}