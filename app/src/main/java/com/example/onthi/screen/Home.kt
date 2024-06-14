package com.example.onthi.screen

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.onthi.R
import com.example.onthi.room.SanPhamModel
import com.example.onthi.viewmodel.SanPhamViewModel
import com.ibm.icu.text.Transliterator

@Composable
fun Home(navController: NavController, viewModel: SanPhamViewModel){
    val context = LocalContext.current
    val listSP by viewModel.sanPhams.collectAsState(initial = emptyList())
    val listTang by viewModel.getTang.collectAsState(initial = emptyList())
    val listGiam by viewModel.getGiam.collectAsState(initial = emptyList())

    var list by remember {
        mutableStateOf(mutableListOf<SanPhamModel>())
    }

    var listSearch by remember {
        mutableStateOf(emptyList<SanPhamModel>())
    }

    var isSearching by remember {
        mutableStateOf(false)
    }

    var isCheckTang by remember {
        mutableStateOf(false)
    }

    var isCheckGiam by remember {
        mutableStateOf(false)
    }

    var isShowDialogAdd by remember {
        mutableStateOf(false)
    }
    Scaffold (
        floatingActionButton ={
            FloatingActionButton(
                onClick = {
                          isShowDialogAdd = true
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background,
                shape = CircleShape) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ){
        // tim kiem voi sap xep
        Header(
            onCheckTang = {
                isCheckTang = true
                isCheckGiam = false
                isSearching = false
            },
            onCheckGiam = {
                isCheckTang = false
                isCheckGiam = true
                isSearching = false
            },
            onSearch = { key ->
                isCheckTang = false
                isCheckGiam = false
                isSearching = true
                val keySearch = key.removeDiacritics()
                listSearch = listSP.filter { sanPham ->
                    sanPham.name!!.removeDiacritics().contains(keySearch)
                }
            })

        when{
            isCheckTang -> GetList(listTang,viewModel,context, it )
            isCheckGiam -> GetList(listGiam,viewModel,context, it )
            isSearching -> GetList(listSearch,viewModel,context, it )
            else -> GetList(listSP,viewModel,context, it )
        }


        if (isShowDialogAdd){
            AddSanPham( viewModel = viewModel, context = context ) {
                isShowDialogAdd = false
            }
        }
    }
}

@Composable
fun Header(
    onCheckTang : () -> Unit,
    onCheckGiam: () -> Unit,
    onSearch: (String) -> Unit
){
    var inputKey by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(130.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Danh sach san pham", fontSize = 30.sp, color = Color.Green)
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = inputKey,
                onValueChange = { inputKey = it },
                modifier = Modifier.width(200.dp),
                label = {
                    Text(text = "Tim kiem theo name")
                })
            Button(onClick = {
                onSearch(inputKey)
            }) {
                Text(text = "Search")
            }
            Image(painter = painterResource(id = R.drawable.ic_up), contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onCheckTang()
                    })

            Image(painter = painterResource(id = R.drawable.ic_down), contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onCheckGiam()
                    })
        }
    }
}

// hien thi danh sach
@Composable
fun GetList(
    listSP: List<SanPhamModel>,
    viewModel: SanPhamViewModel,
    context: Context,
    paddingValues: PaddingValues){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 145.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(listSP.size == 0){
            Text(text = "No data", fontSize = 20.sp, fontStyle = FontStyle.Italic)
        }
        else LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(listSP){sp ->
                ItemSanPham(sp = sp, viewModel,context)
            }
        }
    }
}

// Item san pham
@Composable
fun ItemSanPham(sp: SanPhamModel,viewModel: SanPhamViewModel, context: Context){

    var isShowDilogDelete by remember {
        mutableStateOf(false)
    }

    var isShowDilogDetail by remember {
        mutableStateOf(false)
    }

    var isShowDilogUpdate by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                isShowDilogDetail = true
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = sp.image,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(10.dp)
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp)))

            Column(
                modifier = Modifier.padding(14.dp)
            ) {

                Text(
                    text = "Name: " + sp.name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),

                    )
                Text(
                    text = "Price: " + sp.price,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),

                    )
                Text(
                    text = "Description: " + sp.description,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),
                    )
                Text(
                    text = "Status: " + if (sp.status!!) "San pham moi" else "San pham cu",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp,5.dp),
                    )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ){
                Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            isShowDilogUpdate = true
                        }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Icon(imageVector = Icons.Default.Delete, contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            isShowDilogDelete = true
                        }
                )
            }

        }
    }

    if (isShowDilogDelete){
        DeleteDialog(viewModel, sp , context ) {
            isShowDilogDelete = false
        }
    }

    if (isShowDilogDetail){
        DetailSanPham(sp = sp) {
            isShowDilogDetail = false
        }
    }

    if (isShowDilogUpdate){
        UpdateSanPham(viewModel, context, sp) {
            isShowDilogUpdate = false
        }
    }
}

// ham xoa dau
fun String.removeDiacritics(): String {
    val transliterator = Transliterator.getInstance("NFD; [:Nonspacing Mark:] Remove; NFC")
    return transliterator.transliterate(this)
}
