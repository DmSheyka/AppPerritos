package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.woof.data.Perro
import com.example.woof.data.perros
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppPerros()
                }
            }
        }
    }
}

@Composable
fun AppPerros() {
    Scaffold(
        topBar = {
            BarraSuperiorApp()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(perros) {
                TemaPerro(
                    perro = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun TemaPerro(
    perro: Perro,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }
    val primaryContainer = Color.White
    val tertiaryContainer = Color.LightGray
    val colorFondo by animateColorAsState(
        if (expandido) tertiaryContainer else primaryContainer
    )

    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(colorFondo)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                IconoPerro(perro.idRecursoImg)
                InformacionPerro(perro.nombre, perro.annio)
                Spacer(Modifier.weight(1f))
                BotonTemaPerro(
                    expandido = expandido,
                    onClick = { expandido = !expandido },
                )
            }
            if (expandido) {
                PasatiempoPerro(
                    perro.pasatiempos, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}


@Composable
private fun BotonTemaPerro(
    expandido: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expandido) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.boton_descripcion),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun BarraSuperiorApp(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun IconoPerro(
    @DrawableRes iconoPerro: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(iconoPerro),
        contentDescription = null
    )
}

@Composable
fun InformacionPerro(
    @StringRes nombrePerro: Int,
    edadPerro: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(nombrePerro),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.annios, edadPerro),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun PasatiempoPerro(
    @StringRes pasatiempoPerro: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.acerca),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(pasatiempoPerro),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun VistaPreviaApp() {
    WoofTheme(darkTheme = false) {
        AppPerros()
    }
}

@Preview
@Composable
fun VistaPreviaAppTemaOscuro() {
    WoofTheme(darkTheme = true) {
        AppPerros()
    }
}
