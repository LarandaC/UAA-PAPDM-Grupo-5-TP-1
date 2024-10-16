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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.laranda.primerparcial.data.delete
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
    val propiedadState = remember { mutableStateListOf<Propiedad>() }
    propiedadState.addAll(propiedades)

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
                    PropiedadList(
                        propiedad = propiedad,
                        onDelete = {
                            delete(propiedad)
                        },
                        modifier = Modifier.padding(8.dp))
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
    propiedad: Propiedad,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .clip(
                        MaterialTheme.shapes.small
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                        .padding(8.dp)
                ) {
                    InfoPropiedad(propiedad, onDelete)
                }
            }

        }
    }
}

@Composable
fun InfoPropiedad(
    propiedad: Propiedad,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        //Direccion
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dirección: ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = propiedad.direccion,
            )
        }

        // Lazy Row para el resto
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Espacio entre elementos
        ) {
            items(listOf(
                Pair("Tipo: ", propiedad.tipo.name),
                Pair("Habitaciones: ", propiedad.numHabitaciones.toString())
            )) { (titulo, valor) ->
                Row(
                    modifier = Modifier.padding(8.dp), // Espacio alrededor de cada item
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = titulo,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = valor)
                }
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Espacio entre elementos
        ) {
            items(
                listOf(
                    Pair("Superficie: ", "${propiedad.superficie} m²"),
                    Pair("Precio: ", "$${propiedad.precio}")
                )
            ) { (titulo, valor) ->
                Row(
                    modifier = Modifier.padding(8.dp), // Espacio alrededor de cada item
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = titulo,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = valor)
                }
            }
        }

        SmallFloatingActionButton(
            onClick = onDelete,
            containerColor = MaterialTheme.colorScheme.onErrorContainer,
            contentColor = MaterialTheme.colorScheme.surface,

        ) {
            Icon(Icons.Filled.Delete, "Eliminar" )
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
@Preview
@Composable
fun ParcialAppDarkPreview() {
    PrimerParcialTheme(darkTheme = true) {
        ParcialApp()
    }
}

