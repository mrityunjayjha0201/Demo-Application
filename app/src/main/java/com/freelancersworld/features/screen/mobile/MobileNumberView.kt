package com.freelancersworld.features.screen.mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.freelancersworld.R
import com.freelancersworld.features.component.AppButton
import com.freelancersworld.features.component.AppScaffold
import com.freelancersworld.features.component.AppTextField
import com.freelancersworld.features.component.AppTextView
import com.freelancersworld.features.component.BottomRoundedCardBackgroundView
import com.freelancersworld.features.ui.theme.AppTypography
import com.freelancersworld.features.ui.theme.Black
import com.freelancersworld.features.ui.theme.Emperor
import com.freelancersworld.features.ui.theme.Gallery
import com.freelancersworld.features.ui.theme.White
import com.freelancersworld.utils.showToast


@Composable
fun MobileNumberView(
    viewModel: MobileNumberViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    /*message*/
    viewState.message?.showToast(context)
    viewState.errMsg?.let {
        stringResource(id = it).showToast(context)
    }
    /*END*/

    /*VIEW IMPLEMENTATION*/
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = White
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
                        bottom.linkTo(cnsBottomCard.top)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f))

                //mobile number input filed with continue button
                BottomView(modifier = Modifier
                    .constrainAs(cnsBottomCard) {
                        top.linkTo(cnsTopCard.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.3F),
                    countryCode = viewState.countryCode,
                    mobileNumber = viewState.mobileNumber,
                    onValueChange = {
                        viewModel.setMobileNumberText(it)
                    },
                    onClick = {
                        viewModel.onTriggerEvent(MobileNumberViewEvent.ValidateAndSendOtp)
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
            text = stringResource(id = R.string.welcome)
        )

        AppTextView(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(tvExperienceDesc) {
                    top.linkTo(tvWelcome.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = White,
            style = AppTypography.headlineLarge,
            text = stringResource(id = R.string.experience_the_best_workplace)
        )

        Image(
            modifier = Modifier
                .constrainAs(imgImage) {
                    top.linkTo(tvExperienceDesc.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                }
                .width(210.dp)
                .height(250.dp),
            painter = painterResource(id = R.drawable.new_business_partners_shaking_hands),
            contentDescription = "")
    }
}

@Composable
private fun BottomView(
    modifier: Modifier,
    countryCode: String?,
    mobileNumber: String?,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (tvContinueWithPhone, rowPhoneNumber, btnContinue) = createRefs()
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
            text = stringResource(id = R.string.txt_continue_with_phone)
        )

        Row(modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp)
            .constrainAs(rowPhoneNumber) {
                top.linkTo(tvContinueWithPhone.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Card(
                modifier = Modifier
                    .width(56.dp)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp),
                backgroundColor = Black,
                elevation = 0.dp
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (tvCountryCode) = createRefs()
                    AppTextView(
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .fillMaxWidth()
                            .constrainAs(tvCountryCode) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .padding(top = 6.dp, bottom = 6.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = White,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        text = countryCode.orEmpty()
                    )
                }
            }
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp),
                bgColor = Gallery,
                shapes = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp),
                value = mobileNumber.orEmpty(),
                onValueChange = {
                    onValueChange(it)
                },
                placeholder = {
                    AppTextView(
                        text = stringResource(id = R.string.plc_enter_your_mobile_number),
                        color = Emperor
                    )
                },
                textStyle = TextStyle(
                    color = Black,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                )
            )
        }

        AppButton(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(btnContinue) {
                    top.linkTo(rowPhoneNumber.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                },
            shapes = 50,
            text = stringResource(id = R.string.txt_continue),
            colors = Black,
            onClick = { onClick.invoke() })
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MobileNumberView(viewModel = hiltViewModel())
}