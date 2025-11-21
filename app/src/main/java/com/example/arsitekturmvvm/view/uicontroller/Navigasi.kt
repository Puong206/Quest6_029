package com.example.arsitekturmvvm.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arsitekturmvvm.view.FormIsian
import com.example.arsitekturmvvm.view.TampilData
import com.example.arsitekturmvvm.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@Composable
fun DataApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(isiRuang)){
            composable(route = Navigasi.Formulirku.name){
                FormIsian (
                    onSubmitBtnClick = {
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData {
                    cancelAndBackToFormulir(navController)
                }
            }
        }
    }
}
private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.Formulirku.name, inclusive = false)
}