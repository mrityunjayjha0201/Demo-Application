package com.freelancersworld.features.screen.register

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.freelancersworld.features.base.BaseActivity

class RegisterActivity : BaseActivity() {
    
    private val viewModel by viewModels<RegisterViewModel>()
    
    @Composable
    override fun GetSurface() {
        RegisterView(viewModel = viewModel)
    }

    override fun viewCreated() {

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSurface() {
    RegisterView(viewModel = hiltViewModel())
}

