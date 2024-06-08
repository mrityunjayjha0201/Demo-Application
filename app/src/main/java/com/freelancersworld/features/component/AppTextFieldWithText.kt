package com.freelancersworld.features.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.freelancersworld.R
import com.freelancersworld.features.ui.theme.AppTypography
import com.freelancersworld.features.ui.theme.Black
import com.freelancersworld.features.ui.theme.Emperor
import com.freelancersworld.features.ui.theme.Gallery

@Composable
fun AppTextFieldWithText(
    modifier: Modifier,
    modifierChild: Modifier,
    title: Int,
    placeHolder: Int,
    value: String?,
    keyboardType:KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
    ) {

        AppTextView(
            modifier = Modifier.padding(start = 10.dp),
            text = stringResource(id = title),
            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
            textAlign = TextAlign.Center,
            style = AppTypography.titleMedium,
            color = Black,
            fontSize = 20.sp)

        AppTextField(
            modifier = modifierChild
                .fillMaxWidth()
                .height(50.dp)
                .padding(0.dp),
            bgColor = Gallery,
            singleLine = singleLine,
            maxLines = maxLines,
            shapes = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            value = value.orEmpty(),
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                AppTextView(
                    text = stringResource(id = placeHolder),
                    color = Emperor
                )
            },
            textStyle = TextStyle(
                color = Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppTextFieldWithText() {
    AppTextFieldWithText(
        modifierChild = Modifier.height(200.dp),
        modifier = Modifier,
        title = R.string.welcome,
        placeHolder = R.string.plc_enter_your_mobile_number,
        value = "My name is jha",
        onValueChange = {}
    )
}