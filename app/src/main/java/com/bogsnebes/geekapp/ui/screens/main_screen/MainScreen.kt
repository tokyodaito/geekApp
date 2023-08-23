package com.bogsnebes.geekapp.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bogsnebes.geekapp.R
import com.bogsnebes.geekapp.ui.screens.BaseScreen
import com.bogsnebes.geekapp.ui.screens.main_screen.elm.Event
import com.bogsnebes.geekapp.ui.screens.main_screen.elm.State
import com.bogsnebes.geekapp.ui.screens.main_screen.elm.Store
import com.bogsnebes.geekapp.ui.theme.GeekappTheme

class MainScreen : BaseScreen {

    @Composable
    fun RefreshTheFact(viewModel: Store) {
        val state = viewModel.state.observeAsState(State(isLoading = false, data = null))
        val circularDimens = 30.dp

        Button(onClick = { viewModel.update(Event.Ui.ClickReload) }) {
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .height(circularDimens)
                        .width(circularDimens)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.button_of_getting_new_fact),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun TextOfFact(viewModel: Store) {
        val state = viewModel.state.observeAsState(State(isLoading = false, data = null))
        (if (state.value.data != null) state.value.data else "GACHI")?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    /* TODO: Эффект частиц.
* Сделать эффект частиц при нажатии на "Добавить в избранное"
* */

    @Composable
    fun StarOfFavorite() {
        var isSelected by remember { mutableStateOf(false) }
        val startPaddingOfStar = 12.dp

        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = stringResource(R.string.delete_from_favorites),
                modifier = Modifier
                    .padding(start = startPaddingOfStar)
                    .clickable { isSelected = false }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.baseline_star_border_24),
                contentDescription = stringResource(R.string.add_to_favorites),
                modifier = Modifier
                    .padding(start = startPaddingOfStar)
                    .clickable { isSelected = true }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    override fun Content() {
        val viewModel: Store = viewModel()
        viewModel.update(Event.Ui.Init)
        GeekappTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextOfFact(viewModel)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RefreshTheFact(viewModel)
                            StarOfFavorite()
                        }
                    }
                }
            }
        }
    }
}