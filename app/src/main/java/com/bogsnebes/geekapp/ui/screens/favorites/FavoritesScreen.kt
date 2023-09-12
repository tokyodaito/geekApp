package com.bogsnebes.geekapp.ui.screens.favorites

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bogsnebes.geekapp.R
import com.bogsnebes.geekapp.model.database.dto.FactDto
import com.bogsnebes.geekapp.ui.screens.BaseScreen
import com.bogsnebes.geekapp.ui.screens.favorites.elm.Event
import com.bogsnebes.geekapp.ui.screens.favorites.elm.Store
import com.bogsnebes.geekapp.ui.theme.GeekappTheme

class FavoritesScreen : BaseScreen {

    @Composable
    fun StarOfFavorite(fact: FactDto, viewModel: Store) {
        val startPaddingOfStar = 12.dp

        Image(
            painter = painterResource(id = R.drawable.baseline_star_24),
            contentDescription = stringResource(R.string.delete_from_favorites),
            modifier = Modifier
                .padding(start = startPaddingOfStar)
                .clickable {
                    viewModel.update(Event.Ui.DeleteFavorite(fact))
                }
        )
    }

    @Composable
    fun CardList(viewModel: Store) {
        val items = viewModel.state.observeAsState().value?.data

        if (items != null) {
            LazyColumn {
                items(items) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = item.text,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)
                            )
                            StarOfFavorite(item, viewModel)
                        }
                    }
                }
            }
        }
    }

    @Composable
    override fun DisplayContent(context: Context) {
        val viewModel: Store = viewModel()
        viewModel.update(Event.Ui.Init)

        GeekappTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                CardList(viewModel)
            }
        }
    }
}