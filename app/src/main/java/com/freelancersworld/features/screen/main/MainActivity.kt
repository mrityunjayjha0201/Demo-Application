package com.freelancersworld.features.screen.main

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.freelancersworld.R
import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.features.base.BaseActivity
import com.freelancersworld.features.component.AppTextField
import com.freelancersworld.features.component.AppTextView
import com.freelancersworld.features.ui.theme.AppTypography
import com.freelancersworld.features.ui.theme.Gallery
import com.freelancersworld.features.ui.theme.White

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    @Composable
    override fun GetSurface() {
        val viewState = viewModel.uiState.collectAsState()

        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {

            if (viewState.value.isLoading) {
                LinearProgressIndicator(modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth())
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(viewState.value.photoList) {
                    RcyItems(photosResponse = it)
                }
            }
        }
    }

    override fun viewCreated() {
        scope {
            //api request
            viewModel.onTriggerEvent(MainViewEvent.PhotoApiRequest)

            //observe error
            viewModel.uiEvent.collect {
                when (it) {
                    is MainViewEvent.ShowError -> {
                        showMsg(it.msg)
                    }

                    else -> {}
                }
            }
        }
    }

    @Composable
    fun RcyItems(photosResponse: PhotosResponse?) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp)
            ) {
                ConstraintLayout {
                    val (imgBackground, tvWelcome, tvExperienceDesc, imgImage) = createRefs()

                    Image(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .constrainAs(imgBackground) {
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            },
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "",
                        painter = rememberAsyncImagePainter(photosResponse?.url)
                    )

                    AppTextView(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .constrainAs(tvExperienceDesc) {
                                top.linkTo(imgBackground.bottom, margin = 10.dp)
                                bottom.linkTo(parent.bottom, margin = 10.dp)
                            }
                            .padding(10.dp, 0.dp),

                        textAlign = TextAlign.Start,
                        fontSize = 15.sp,
                        color = Color.Black,
                        style = AppTypography.headlineLarge,
                        text = photosResponse?.title ?: ""
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preview() {
    MainActivity().GetSurface()
}

