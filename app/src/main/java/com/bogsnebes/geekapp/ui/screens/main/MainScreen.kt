package com.bogsnebes.geekapp.ui.screens.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.R
import com.bogsnebes.geekapp.ui.screens.BaseScreen
import com.bogsnebes.geekapp.ui.screens.main.elm.Effect
import com.bogsnebes.geekapp.ui.screens.main.elm.Event
import com.bogsnebes.geekapp.ui.screens.main.elm.State
import com.bogsnebes.geekapp.ui.screens.main.elm.Store
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
    fun TranslateFact(viewModel: Store) {
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = stringResource(id = R.string.translate_the_fact),
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun TextOfFact(viewModel: Store) {
        val state = viewModel.state.observeAsState(State(isLoading = false, data = null))
        Text(
            text = state.value.data?.text ?: "GACHI",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }

    /* TODO: Эффект частиц.
* Сделать эффект частиц при нажатии на "Добавить в избранное"
* */

    @Composable
    fun StarOfFavorite(viewModel: Store) {
        var isSelected by remember { mutableStateOf(false) }
        val startPaddingOfStar = 12.dp

        val painterResource = if (isSelected) {
            painterResource(id = R.drawable.baseline_star_24)
        } else {
            painterResource(id = R.drawable.baseline_star_border_24)
        }

        val contentDescription = if (isSelected) {
            stringResource(R.string.delete_from_favorites)
        } else {
            stringResource(R.string.add_to_favorites)
        }

        Image(
            painter = painterResource,
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(start = startPaddingOfStar)
                .clickable {
                    isSelected = !isSelected
                    viewModel.update(Event.Ui.ClickToFavorites(viewModel.state.value?.data))
                }
        )
    }

    @Composable
    fun LanguageSelection(viewModel: Store) {
        val languageMap = mapOf(
            "English" to Languages.ENGLISH,
            "German" to Languages.GERMAN,
            "Russian" to Languages.RUSSIAN
        )

        val languages = languageMap.keys.toList() // Список доступных языков

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Select a language:", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            languages.forEach { language ->
                LanguageItem(language = language, viewModel, languageMap)
            }
        }
    }

    @Composable
    fun LanguageItem(language: String, viewModel: Store, languageMap: Map<String, Languages>) {
        Text(
            text = language,
            modifier = Modifier
                .clickable {
                    val selectedLanguage = languageMap[language]
                    selectedLanguage?.let {
                        viewModel.update(Event.Ui.ChangeLanguage(it))
                    }
                }
                .padding(8.dp)
        )
    }


    @Composable
    override fun DisplayContent(context: Context) {
        val viewModel: Store = viewModel()
        val effect = viewModel.effect.observeAsState()

        viewModel.update(Event.Ui.Init)
        GeekappTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LanguageSelection(
                    viewModel
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextOfFact(viewModel)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RefreshTheFact(viewModel)
                            StarOfFavorite(viewModel)
                        }
                        TranslateFact(viewModel = viewModel)
                    }
                }

                if (effect.value == Effect.ShowError) {
                    Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}


