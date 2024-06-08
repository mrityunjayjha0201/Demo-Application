package com.freelancersworld.features.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.freelancersworld.features.ui.theme.AppTypography
import com.freelancersworld.features.ui.theme.Black
import com.freelancersworld.features.ui.theme.White

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: Color = White,
    borderColor: Color? = null,
    enabled: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    shapes: Int = 20
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(50.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = colors),
        shape = RoundedCornerShape(shapes),
        border = if (borderColor != null) BorderStroke(
            width = 1.dp,
            color = borderColor
        ) else null
        ,
        content = {
            AppTextView(
                modifier = Modifier.padding(paddingValues = paddingValues),
                text = text,
                style = AppTypography.titleLarge,
                color = Color.White
            )
        },
        elevation = ButtonDefaults.elevation(0.dp)
    )
}

@Preview
@Composable
private fun BodyPreview() {
    AppButton(modifier = Modifier.height(40.dp), text = "Button",
        colors = Black,
        shapes = 50,
        onClick = {
    })
}