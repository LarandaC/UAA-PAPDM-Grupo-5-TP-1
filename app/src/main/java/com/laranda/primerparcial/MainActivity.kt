package com.laranda.primerparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laranda.primerparcial.data.Propiedad
import com.laranda.primerparcial.data.propiedades
import com.laranda.primerparcial.ui.theme.PrimerParcialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerParcialTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        ParcialApp()
                    }
                }
            }
        }
    }
}

@Composable
fun ParcialApp() {

    Column (
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.add_property) ,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        AgregarPropiedadScreen()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.lista) ,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )


        // Lista de propiedades
        LazyColumn {
            if (propiedades.isNotEmpty()) {
                items(propiedades) { propiedad ->
                    PropiedadList(propiedad = propiedad, modifier = Modifier.padding(8.dp))
                }
            } else {
                item {
                    Text("No hay propiedades disponibles.")
                }
            }
        }

    }
}

@Composable
fun PropiedadList(
    propiedad: Propiedad, modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Box {
            AsyncImage(
                model = propiedad.imageResourceId, // Usa la URL aquí
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp),
                contentScale = ContentScale.Crop
            )

//            Image(
//                painter = painterResource(propiedad.imageResourceId),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()  // Make the image fill the width of the Card
//                    .heightIn(max = 300.dp),  // Set the maximum height for the image
//                contentScale = ContentScale.Crop  // Ensures the image scales to fill the space without distortion
//            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)  // Align at the bottom-left of the image
                    .padding(8.dp)
                    .clip(
                        MaterialTheme.shapes.small
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)  // Align at the bottom-left of the image
                        .background(color = Color.White.copy(alpha = 0.8f))  // Semi-transparent background
                        .padding(8.dp)
                ) {
                    InfoPropiedad(propiedad)
                }
            }

        }
    }
}

@Composable
fun InfoPropiedad(
    propiedad: Propiedad, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = propiedad.tipo.name,
            )
            Text(
                text = "$${propiedad.precio}",
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = "Habitaciones: ",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = propiedad.numHabitaciones.toString(),
                )
            }
            Row {
                Text(
                    text = "Superficie: ",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = propiedad.superficie.toString(),
                )
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row {
                Text(
                    text = "Dirección: ",
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                SmallFloatingActionButton(
                    onClick = {
                    /*TODO*/ // logica para que borre
                    },
                    containerColor = MaterialTheme.colorScheme.onErrorContainer,
                    contentColor = MaterialTheme.colorScheme.surface
                ) {
                    Icon(Icons.Filled.Delete, "Eliminar" )
                }
            }
        }
    }
}

@Preview
@Composable
fun ParcialAppPreview() {
    PrimerParcialTheme(darkTheme = false) {
        ParcialApp()
    }
}
