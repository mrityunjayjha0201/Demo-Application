package com.freelancersworld.features.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.freelancersworld.features.ui.theme.Black

@Composable
fun BottomRoundedCardBackgroundView(
    modifier: Modifier = Modifier,
    content: @Composable ConstraintLayoutScope.() -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp),
            backgroundColor = Black
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BottomRoundedCardBackgroundView(modifier = Modifier) {}
}