package com.laranda.primerparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ParcialApp()
                }
            }
        }
    }
}

@Composable
fun ParcialApp() {
    LazyColumn {
        items(propiedades) {
            Propiedad(propiedad = it, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun Propiedad(
    propiedad: Propiedad, modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Box {
            Image(
                painter = painterResource(propiedad.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()  // Make the image fill the width of the Card
                    .heightIn(max = 300.dp),  // Set the maximum height for the image
                contentScale = ContentScale.Crop  // Ensures the image scales to fill the space without distortion
            )

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

        Text(
            text = stringResource(propiedad.direccion),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ParcialAppPreview() {
    PrimerParcialTheme(darkTheme = false) {
        ParcialApp()
    }
}
