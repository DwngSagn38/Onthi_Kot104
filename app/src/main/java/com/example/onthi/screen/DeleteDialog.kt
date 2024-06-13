package com.example.onthi.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onthi.room.SanPhamModel
import com.example.onthi.viewmodel.SanPhamViewModel


// delete san pham
@Composable
fun DeleteDialog(
    viewModel: SanPhamViewModel,
    sp: SanPhamModel,
    context: Context,
    onConfirm: () -> Unit
){
    AlertDialog(onDismissRequest = { onConfirm() },
        dismissButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val msg = viewModel.deleteSanPham(sp)
                    onConfirm()
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "Ok")
            }
        },
        title = {
            Text(
                text = "Canh bao!!!",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(5.dp)
            )
        },
        text = {
            Text(text = "Ban co muon xoa san pham nay khong?")
        })
}