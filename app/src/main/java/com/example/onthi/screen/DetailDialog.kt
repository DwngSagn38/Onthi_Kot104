package com.example.onthi.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.onthi.room.SanPhamModel
import com.example.onthi.viewmodel.SanPhamViewModel

// add san pham
@Composable
fun DetailSanPham(
    sp : SanPhamModel,
    onConfirm: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onConfirm() },
        dismissButton = {},
        confirmButton = {
            Button(
                onClick = { onConfirm() }
            ) {
                Text(text = "Close")
            }
        },
        title = {
            Text(
                text = "Chi tiet san pham",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(5.dp)
            )
        },
        text = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                AsyncImage(
                    model = sp.image,
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.padding(10.dp).size(240.dp,180.dp)
                        .clip(RoundedCornerShape(12.dp)))
                Text(text = "Id : ${sp.uid}", fontSize = 22.sp, modifier = Modifier.padding(0.dp,5.dp))
                Text(text = "Name : ${sp.name}", fontSize = 18.sp, modifier = Modifier.padding(0.dp,5.dp))
                Text(text = "Price : ${formatCurrency(sp.price.toString())}", fontSize = 18.sp, modifier = Modifier.padding(0.dp,5.dp))
                Text(text = "Description : ${sp.description}", fontSize = 18.sp, modifier = Modifier.padding(0.dp,5.dp))
                Text(text = "Status : "+ if(sp.status!!) "San pham moi" else "San pham cu", fontSize = 18.sp, modifier = Modifier.padding(0.dp,5.dp))
            }
        }
    )
}