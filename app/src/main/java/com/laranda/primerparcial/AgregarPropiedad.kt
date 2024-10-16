package com.laranda.primerparcial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.laranda.primerparcial.data.add
import com.laranda.primerparcial.data.Propiedad
import com.laranda.primerparcial.data.propiedades
import com.laranda.primerparcial.data.tipoPropiedad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarPropiedadScreen(){
    var direccion by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var habitaciones by rememberSaveable { mutableStateOf("") }
    var superficie by rememberSaveable { mutableStateOf("") }
    var imagenUrl by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val opciones = listOf("Casa", "Apartamento")
    var opcionElegida by rememberSaveable { mutableStateOf(opciones[0]) }

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Direccion
        Text(
            text = stringResource(id = R.string.add_direccion),
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = direccion,
            onValueChange = {direccion = it}
        )

    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)  // Espacio entre los campos
    ){
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            // Tipo propiedad
            Text(
                text = stringResource(id = R.string.add_tipo),
                style = MaterialTheme.typography.bodyLarge
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded },
            ) {

                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = opcionElegida,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded=false }) {
                    opciones.forEachIndexed{ index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                opcionElegida = opciones[index]
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            //Text(text = "Opcion elegida: $opcionElegida" )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            // Cantidad habitaciones
            Text(
                text = stringResource(id = R.string.add_habitaciones),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                value = habitaciones,
                onValueChange = { habitaciones = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }
    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)  // Espacio entre los campos
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Precio
            Text(
                text = stringResource(id = R.string.add_price),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                value = price,
                onValueChange = {price = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )
        }

        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Superficie
            Text(
                text = stringResource(id = R.string.add_superficie),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = superficie,
                onValueChange = {superficie = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                placeholder = { Text(text = "Ej:400.0")}
            )
            Text(text = "m2" )
            /*Text(
                text = "m²",
                modifier = Modifier.padding(start = 8.dp)
            )*/
        }
    }

    // Imagen
    Text(
        text = stringResource(id = R.string.imagenUrl),
        style = MaterialTheme.typography.bodyLarge
    )

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = imagenUrl,
        onValueChange = {imagenUrl = it},
        placeholder = { Text(text = "https://example.com/imagen.jpg") },
    )

    if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage, color = Color.Red)
    }
    Spacer(modifier = Modifier.height(4.dp))
    // Boton para guardar
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = {
            // Validaciones
            val validaciones = validarForm(direccion, price, habitaciones, superficie, imagenUrl)
            errorMessage = validaciones.first

            if (validaciones.second){
                val nuevaPropiedad = Propiedad(
                    direccion = direccion,
                    precio = price.toDouble(),
                    numHabitaciones = habitaciones.toInt(),
                    superficie = superficie.toDouble(),
                    tipo = if (opcionElegida == "Casa") tipoPropiedad.CASA else tipoPropiedad.APARTAMENTO,
                    imageResourceId = imagenUrl
                )
                add(nuevaPropiedad)

                direccion = ""
                price = ""
                habitaciones = ""
                superficie = ""
                imagenUrl = "" }
        },
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(
            text = stringResource(id = R.string.guardar),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun validarForm(
    direccion: String,
    habitaciones: String,
    superficie: String,
    precio: String,
    imagenUrl: String
): Pair<String, Boolean> {
    if (direccion.isBlank()) {
        return "La dirección es obligatoria." to false
    }

    if (habitaciones.isBlank() || habitaciones.toIntOrNull() == null) {
        return "El número de habitaciones debe ser un número válido." to false
    }

    if (superficie.isBlank() || superficie.toDoubleOrNull() == null) {
        return "La superficie debe ser un número válido." to false
    }

    if (precio.isBlank() || precio.toDoubleOrNull() == null) {
        return "El precio debe ser un número válido." to false
    }

    if (imagenUrl.isBlank()) {
        return "La URL de la imagen es obligatoria." to false
    }

    return "" to true // Si todo está bien
}