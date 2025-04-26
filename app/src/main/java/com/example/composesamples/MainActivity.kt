package com.example.composesamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composesamples.lists.DetailScreen
import com.example.composesamples.lists.MyList
import com.example.composesamples.ui.theme.ComposeSamplesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeSamplesTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Main.route) {
                    composable(route = Screen.Main.route) {
                        MainScreen { route ->
                            navController.navigate(route = route)
                        }
                    }
                    composable(route = Screen.EffectOrderScreen.route) {
                        EffectOrder("Launch Effect Key")
                    }

                    composable(route = Screen.DraggablePanelScreen.route) {
                        DraggablePanel()
                    }

                    composable(route = Screen.SnapShotMutationPolicyScreen.route) {
                        UserUi()
                    }

                    composable(route = Screen.AvoidLaunchEffectScreen.route) {
                        ParentScreen()
                    }

                    composable(route = Screen.ListScreen.route) {
                        MyList {
                            navController.navigate(Screen.DetailScreen.route)
                        }
                    }

                    composable(route = Screen.DetailScreen.route) {
                        DetailScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, navigateTo: (String) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Compose Example")
                })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(4) { index ->
                val (route, buttonText) = if (index == 0) {
                    Screen.EffectOrderScreen.route to "Effect Order"
                } else if (index == 1) {
                    Screen.DraggablePanelScreen.route to "Draggable Pane"
                } else if (index == 2) {
                    Screen.SnapShotMutationPolicyScreen.route to "SnapShot Mutation Policy"
                } else if (index == 3) {
                    Screen.ListScreen.route to "List Sample"
                }
                else {
                    Screen.AvoidLaunchEffectScreen.route to "Avoid LaunchEffect"
                }
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    navigateTo(route)
                }, content = {
                    Text(
                        text = buttonText,
                        modifier = modifier
                    )
                }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeSamplesTheme {
        MainScreen {

        }
    }
}