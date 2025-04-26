package com.example.composesamples.lists

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyList(
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = viewModel(),
    navigateToDetailScreen: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("List Screen")
                })
        }
    ) { innerPadding ->
        val scrollState = listViewModel.scrollState.value
        val listItems = listViewModel.generateStringList()
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            state = scrollState
        ) {
            items(
                count = listItems.size,
                key = { index -> listItems[index] }
            ) { index ->
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), onClick = {
                    navigateToDetailScreen()
                }, content = {
                    Text(
                        text = listItems[index],
                        modifier = modifier
                    )
                }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

