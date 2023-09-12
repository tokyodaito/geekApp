package com.bogsnebes.geekapp.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable

interface BaseScreen {
    @Composable
    fun DisplayContent(context: Context)
}