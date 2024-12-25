package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.moviejetpackcompose.model.netWork.ResponseState
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel
import com.example.moviejetpackcompose.ui.theme.view.MovieSectionSearch

@Composable
fun SearchScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val searchState by viewModel.search.collectAsState()
    var query by remember { mutableStateOf("") }

    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            viewModel.getSearch(query)
        }
    }

    val filteredMovies = remember(searchState) {
        when (val result = searchState) {
            is ResponseState.Success -> {
                result.data.results.filter { it.title?.startsWith(query, ignoreCase = true) == true }
            }
            else -> emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search Movies",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
            },
            label = { Text("Enter movie name") },
            placeholder = { Text("Search for a movie...") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (query.isNotEmpty()) {
                        viewModel.getSearch(query)
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Show loading indicator while fetching data
        if (searchState is ResponseState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        // Display search results or empty state message
        when (val result = searchState) {
            is ResponseState.Loading -> {
                // Do nothing, wait for data
            }
            is ResponseState.Success -> {
                if (filteredMovies.isNotEmpty()) {
                    MovieSectionSearch(filteredMovies, navController = navController)
                } else {
                    Text(
                        text = "No movies found. Try searching for something else.",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            is ResponseState.Error -> {
                Text(
                    text = "Error: ${result.message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
