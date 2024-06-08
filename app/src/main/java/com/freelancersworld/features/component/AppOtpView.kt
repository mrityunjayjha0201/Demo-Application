package com.freelancersworld.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freelancersworld.R
import com.freelancersworld.features.ui.theme.Black
import com.freelancersworld.features.ui.theme.Emperor
import com.freelancersworld.features.ui.theme.Gallery

@Composable
fun AppOtpView(
    modifier: Modifier = Modifier,
    otp1: String,
    otp2: String,
    otp3: String,
    otp4: String,
    onValueChange: (String, String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        /*FOCUS REQUESTER*/
        val focusManager = LocalFocusManager.current

        LaunchedEffect(
            key1 = otp1,
        ) {
            if (otp1.isNotEmpty()) {
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next,
                )
            }
        }

        LaunchedEffect(
            key1 = otp2,
        ) {
            if (otp2.isNotEmpty()) {
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next,
                )
            }
        }

        LaunchedEffect(
            key1 = otp3,
        ) {
            if (otp3.isNotEmpty()) {
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next,
                )
            }
        }

        val otpModifier = Modifier
            .height(50.dp)
            .width(50.dp)
        OtpView(
            modifier = otpModifier
                .onKeyEvent {
                    false
                },
            otp = otp1,
            imeAction = ImeAction.Next,
            onValueChange = { onValueChange("1", it) })
        OtpView(
            modifier = otpModifier
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyUp &&
                        event.key == Key.Backspace
                    ) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    false
                },
            otp = otp2,
            imeAction = ImeAction.Next,
            onValueChange = {
                onValueChange("2", it)
            })
        OtpView(
            modifier = otpModifier
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyUp &&
                        event.key == Key.Backspace
                    ) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    false
                },
            otp = otp3,
            imeAction = ImeAction.Next,
            onValueChange = { onValueChange("3", it) })
        OtpView(
            modifier = otpModifier
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyUp &&
                        event.key == Key.Backspace
                    ) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    false
                },
            otp = otp4,
            imeAction = ImeAction.Done,
            onValueChange = { onValueChange("4", it) })
    }
}

@Composable
private fun OtpView(
    modifier: Modifier = Modifier,
    otp: String?,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit,
) {
    AppTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp),
        bgColor = Gallery,
        shapes = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        value = otp.orEmpty(),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            AppTextView(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
                text = "_",
                color = Emperor,
                textAlign = TextAlign.Center,

            )
        },
        textStyle = TextStyle(
            color = Black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOtpView() {
    OtpView(modifier = Modifier
        .height(50.dp)
        .width(50.dp), otp = "1", imeAction = ImeAction.Done, onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppOtpView() {
    AppOtpView(
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth(),
        otp1 = "",
        otp2 = "",
        otp3 = "",
        otp4 = "",
        onValueChange = { _: String, _: String ->

        }
    )
}