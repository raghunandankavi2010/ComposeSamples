package com.example.composesamples

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Avoid launch effect for making api calls initially when
 * you enter composition
 * Whenever a compose screen enters composition LaunchEffect triggers
 */
@Composable
fun ParentScreen() {
    var key by remember { mutableStateOf("initial") }

    // the state change is done her through button click
    // in real use case there might be other states which causes composition
    // of MyScreen and whenever it is composed LaunchEffect gets launched triggering api call
    // So if you want to make api call initially when user enters the screen do it in viewmode
    // Collect the flow in ui this triggers the up stream flow
    Column {
        Button(onClick = { key = "new_${System.currentTimeMillis()}" }) {
            Text("Revisit MyScreen")
        }

        MyScreen(dataKey = key)
    }
}

@Composable
fun MyScreen(dataKey: String, viewModel: MyViewModel = viewModel()) {
    val data by viewModel.data.collectAsStateWithLifecycle()

    // This will trigger `LaunchedEffect` whenever `dataKey` changes
    LaunchedEffect(dataKey) {
        viewModel.fetchData()
    }

    if (data != null) {
        Text(text = data ?: "")
    } else {
        Text(text = "Loading...")
    }
}

class MyViewModel : ViewModel() {
    private val _data = MutableStateFlow<String?>(null)
    val data: StateFlow<String?> = _data


    fun fetchData() {
        viewModelScope.launch {
            delay(2000)
            _data.value = "Fetched data from API with StateFlow!"
        }
    }
}