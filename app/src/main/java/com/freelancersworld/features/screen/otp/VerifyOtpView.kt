package com.freelancersworld.features.screen.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.freelancersworld.R
import com.freelancersworld.features.component.AppButton
import com.freelancersworld.features.component.AppOtpView
import com.freelancersworld.features.component.AppScaffold
import com.freelancersworld.features.component.AppTextView
import com.freelancersworld.features.component.BottomRoundedCardBackgroundView
import com.freelancersworld.features.ui.theme.AppTypography
import com.freelancersworld.features.ui.theme.Black
import com.freelancersworld.features.ui.theme.White
import com.freelancersworld.utils.showToast

@Composable
fun VerifyOtpView(viewModel: OtpViewModel) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    viewState.errMsg?.let {
//        val msg = stringResource(id = it)
        val msg = "Otp is 1234!!"
        LaunchedEffect(key1 = viewState.errMsg) {
            msg.showToast(context)
        }
    }


    /*VIEW IMPLEMENTATION*/
    AppScaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        scaffoldState = scaffoldState,
        backgroundColor = White
    ) {
        val scrollState = rememberScrollState()

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (cnsTopCard, cnsBottomCard) = createRefs()
                //top card view
                TopCardView(modifier = Modifier
                    .constrainAs(cnsTopCard) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth())

                //mobile number input filed with continue button
                BottomView(modifier = Modifier
                    .constrainAs(cnsBottomCard) {
                        top.linkTo(cnsTopCard.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                    mobileNumber = viewState.mobileNumber,
                    otp1 = viewState.otp1,
                    otp2 = viewState.otp2,
                    otp3 = viewState.otp3,
                    otp4 = viewState.otp4,
                    onValueChange = { key: String, value: String ->
                        viewModel.otpText(key, value)
                    },
                    onClick = {
                        viewModel.onTriggerEvent(OtpViewEvent.ValidateOtp)
                    })
            }
        }
    }
}

@Composable
private fun TopCardView(modifier: Modifier) {
    BottomRoundedCardBackgroundView(modifier = modifier) {
        val (imgBackground, tvWelcome, tvExperienceDesc, imgImage) = createRefs()

        Image(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .constrainAs(imgBackground) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            painter = painterResource(id = R.drawable.rectangle_44_mobile_number),
            contentDescription = "",
            contentScale = ContentScale.None
        )

        AppTextView(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(tvWelcome) {
                    top.linkTo(parent.top, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 35.dp, end = 35.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = White,
            style = AppTypography.labelSmall,
            text = stringResource(id = R.string.txt_verify_mobile_number)
        )

        AppTextView(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .constrainAs(tvExperienceDesc) {
                    top.linkTo(tvWelcome.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                }
                .padding(start = 10.dp, end = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = White,
            style = AppTypography.headlineLarge,
            text = stringResource(id = R.string.txt_verify_mobile_number_desc)
        )

        Image(
            modifier = Modifier
                .constrainAs(imgImage) {
                    top.linkTo(tvExperienceDesc.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .width(210.dp)
                .height(250.dp),
            painter = painterResource(id = R.drawable.otp),
            contentDescription = "")
    }
}

@Composable
private fun BottomView(
    modifier: Modifier = Modifier,
    mobileNumber: String?,
    otp1: String = "",
    otp2: String = "",
    otp3: String = "",
    otp4: String = "",
    onValueChange: (String, String) -> Unit,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (tvContinueWithPhone, rowOtpView, btnContinue) = createRefs()

        AppTextView(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .constrainAs(tvContinueWithPhone) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Black,
            fontFamily = FontFamily(Font(resId = R.font.poppins_medium)),
            text = stringResource(id = R.string.txt_code_sent_to, mobileNumber.orEmpty())
        )

        AppOtpView(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .constrainAs(rowOtpView) {
                    top.linkTo(tvContinueWithPhone.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, otp1 = otp1, otp2 = otp2, otp3 = otp3, otp4 = otp4, onValueChange = onValueChange
        )

        AppButton(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(btnContinue) {
                    top.linkTo(rowOtpView.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                },
            shapes = 50,
            text = stringResource(id = R.string.txt_verify),
            colors = Black,
            onClick = { onClick.invoke() })
    }
}

@Preview
@Composable
private fun PreviewVerifyOtpView() {
    VerifyOtpView(viewModel = hiltViewModel())
}

/*@Preview
@Composable
private fun PreviewTopCardView() {
    TopCardView(modifier = Modifier)
}*/

/*
@Preview
@Composable
private fun PreviewBottomCardView() {
    BottomView(
        modifier = Modifier,
        mobileNumber = "+91 9999999999",
        otp = otp,
        onValueChange = { _, _ -> },
        onClick = {})
}*/
