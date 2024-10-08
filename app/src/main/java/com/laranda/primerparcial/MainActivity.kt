package com.laranda.primerparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laranda.primerparcial.data.Propiedad
import com.laranda.primerparcial.data.propiedades
import com.laranda.primerparcial.data.tipoPropiedad
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            InfoPropiedad(direccion = propiedad.direccion, tipoPropiedad = propiedad.tipo)
        }
    }
}

@Composable
fun InfoPropiedad(
    @StringRes direccion: Int, tipoPropiedad: tipoPropiedad, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(direccion),
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = tipoPropiedad.name,
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
