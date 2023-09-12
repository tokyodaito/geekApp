package com.bogsnebes.geekapp.ui.screens.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.R
import com.bogsnebes.geekapp.ui.screens.BaseScreen
import com.bogsnebes.geekapp.ui.screens.settings.elm.Effect
import com.bogsnebes.geekapp.ui.screens.settings.elm.Event
import com.bogsnebes.geekapp.ui.screens.settings.elm.Store
import com.bogsnebes.geekapp.ui.theme.GeekappTheme

class SettingsScreen : BaseScreen {
    @Composable
    fun LanguageSelection(viewModel: Store) {
        val languageMap = mapOf(
            "English" to Languages.ENGLISH,
            "German" to Languages.GERMAN,
            "Russian" to Languages.RUSSIAN
        )

        val languages = languageMap.keys.toList()

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(R.string.select_a_language), fontWeight = FontWeight.Bold)
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
        if (viewModel.state.value == null)
            viewModel.update(Event.Ui.Init)

        GeekappTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LanguageSelection(
                    viewModel
                )

                if (effect.value == Effect.ShowError) {
                    Toast.makeText(context,
                        stringResource(R.string.error_of_change_language), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}