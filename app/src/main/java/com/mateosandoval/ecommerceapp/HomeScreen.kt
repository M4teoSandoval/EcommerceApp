@file:OptIn(ExperimentalMaterial3Api::class)

package com.mateosandoval.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter



@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF9900),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "Bienvenido",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Localized description",
                            tint = Color.White,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Localized description",
                            tint = Color.White,
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    )
    { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {


            Text(
                text = "Promociones",
                modifier = Modifier.padding(top = 16.dp, start = 16.dp,bottom = 8.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF9900),
            )
            val listadoPromociones = listOf(
                "https://img.freepik.com/vector-premium/promocion-oferta-especial-descuento-diseno-plantilla-banner-super-venta_165143-1029.jpg",
                "https://previews.123rf.com/images/dzein/dzein1703/dzein170300011/73432682-diseños-de-banner-de-web-de-vectores-de-venta-de-verano-y-ofertas-especiales-para-promociones-de.jpg",
                "https://img.pikbest.com/templates/20240425/spirited-mothers-day-holiday-wishes-222024-png-images-png_10534920.jpg!w700wp"
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { CardPromo(listadoPromociones[0]) }
                item { CardPromo(listadoPromociones[1]) }
                item { CardPromo(listadoPromociones[2]) }



            }

        }


    }


    }

    @Preview
    @Composable
    fun HomeScreenPreview() {
        HomeScreen(navController = NavController(LocalContext.current))
    }


    @Composable
    fun CardPromo(urlImage : String)
    {
        Card(
            modifier = Modifier.size(width = 300.dp, height = 180.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(urlImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        }
    }