package com.example.composesamples

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

data class User(val name: String, val age: Int)

object MyStatePolicy : SnapshotMutationPolicy<User> {
    override fun equivalent(a: User, b: User): Boolean {
        return a.name == b.name
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserUi() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("SnapShot Mutation Policy")
                })
        }
    ) { innerPadding ->
        var myState by remember {
            mutableStateOf(
                value = User(
                    name = "Raghu",
                    age = 30
                ),
                policy = MyStatePolicy
            )
        }

        Column(modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center) {
            Text("${myState.name} ${myState.age}")
            Log.i("User Ui","MyState = $myState")
            LogCompositions("User Ui", "CustomText function")
        }

        LaunchedEffect(Unit) {
            myState = myState.copy(name = "Raghunandan")
            delay(100)
            myState = myState.copy(age = 34)
        }
    }
}