package com.example.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlist.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    Scaffold(topBar = {
        AppBarView(
            title =
            if (id != 0L) stringResource(id = R.string.update_wish)
            else stringResource(id = R.string.add_wish),
            onBackNavClicked = {navController.navigateUp()}
        )
    },
        scaffoldState = scaffoldState)
    {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                value = viewModel.wishTitleState,
                label = "title",
                onValueChanged = { viewModel.onTitleValueChange(it) })
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                value = viewModel.wishDescriptionState,
                label = "description",
                onValueChanged = { viewModel.onDescriptionChange(it) })
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    if (id != 0L){
                        viewModel.updateWish(Wish(title = viewModel.wishTitleState.trim(), description = viewModel.wishDescriptionState.trim()))
                        snackMessage.value = "Wish updated"
                    }else{
                        viewModel.addWish((Wish(title = viewModel.wishTitleState.trim(), description = viewModel.wishDescriptionState.trim())))
                        snackMessage.value = "Wish created"
                    }
                    scope.launch {
                        //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }
                }else{
                    snackMessage.value = "Enter field to create a wish"
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    }
                }
            }) {
                Text(
                    text = if (id == 0L) "Add Wish" else "Update Wish",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(value: String, label: String, onValueChanged: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}