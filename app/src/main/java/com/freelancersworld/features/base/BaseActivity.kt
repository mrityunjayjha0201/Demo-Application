package com.freelancersworld.features.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.freelancersworld.FreelancersWorldApp
import com.freelancersworld.data.datastore.StoreData
import com.freelancersworld.features.ui.theme.EditorsWorldTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {

    @Inject
    protected lateinit var application:FreelancersWorldApp

    @Inject
    protected lateinit var storeData: StoreData

    @Composable
    abstract fun GetSurface()

    abstract fun viewCreated()

    fun scope(state: Lifecycle.State = Lifecycle.State.CREATED,scope: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(state) {
                scope(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EditorsWorldTheme(darkTheme = application.isDark.value) {
                // A surface container using the 'background' color from the theme
                GetSurface()
            }
        }

        viewCreated()
    }

    protected fun showMsg(msg:String?) {
        msg?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}
