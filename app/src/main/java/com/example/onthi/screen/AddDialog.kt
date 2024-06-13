package com.example.onthi.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onthi.viewmodel.SanPhamViewModel


// add san pham
@Composable
fun AddSanPham(
    viewModel: SanPhamViewModel,
    context: Context,
    onConfirm: () -> Unit) {
    var inputName by remember {
        mutableStateOf("")
    }

    var inputPrice by remember {
        mutableStateOf("")
    }

    var inputDescription by remember {
        mutableStateOf("")
    }

    var inputStatus by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = { onConfirm() },
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
                    val msg = viewModel.addSanPham(
                        name = inputName,
                        price = inputPrice,
                        description = inputDescription,
                        status = inputStatus
                    )
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    if (msg.equals("Them thanh cong")){
                        onConfirm()
                    }
                }
            ) {
                Text(text = "Save")
            }
        },
        title = {
            Text(
                text = "Add San pham",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(5.dp)
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = inputName,
                    onValueChange = {inputName = it},
                    label = {
                        Text(text = "Name")
                    },
                )
                OutlinedTextField(
                    value = inputPrice,
                    onValueChange = {inputPrice = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = "Price")
                    },
                )
                OutlinedTextField(
                    value = inputDescription,
                    onValueChange = {inputDescription = it},
                    label = {
                        Text(text = "Description")
                    },
                )
                OutlinedTextField(
                    value = inputStatus,
                    onValueChange = {inputStatus = it},
                    label = {
                        Text(text = "Status")
                    },
                )
            }
        }
    )
}