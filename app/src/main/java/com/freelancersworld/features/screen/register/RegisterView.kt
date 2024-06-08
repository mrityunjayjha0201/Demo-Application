package com.freelancersworld.features.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.freelancersworld.R
import com.freelancersworld.domain.viewstate.register.RegisterViewState
import com.freelancersworld.features.component.AppButton
import com.freelancersworld.features.component.AppScaffold
import com.freelancersworld.features.component.AppTextFieldWithText
import com.freelancersworld.features.component.AppTextView
import com.freelancersworld.features.component.BottomRoundedCardBackgroundView
import com.freelancersworld.features.ui.theme.AppTypography
import com.freelancersworld.features.ui.theme.Black
import com.freelancersworld.features.ui.theme.White
import com.freelancersworld.utils.showToast

@Composable
fun RegisterView(viewModel: RegisterViewModel) {
    val viewState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    /*message*/
    viewState.message?.showToast(context)
    viewState.errMsg?.let {
        val msg = stringResource(id = it)
        LaunchedEffect(key1 = viewState.errMsg) {
            msg.showToast(context)
        }
    }/*END*/

    /*VIEW IMPLEMENTATION*/
    val scaffoldState = rememberScaffoldState()
    AppScaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        backgroundColor = White,
        scaffoldState = scaffoldState,
        topBar = {
            HeaderView(modifier = Modifier)
        },
        content = {
            Content(viewModel, viewState)
        }
    )
}

@Composable
private fun HeaderView(modifier: Modifier) {
    BottomRoundedCardBackgroundView(
        modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        val (tvHeader) = createRefs()
        AppTextView(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .constrainAs(tvHeader) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 20.dp)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                },
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            textAlign = TextAlign.Center,
            style = AppTypography.titleMedium,
            color = White,
            fontSize = 25.sp,
            text = stringResource(id = R.string.txt_register_yourself)
        )
    }
}

@Composable
private fun Content(viewModel: RegisterViewModel, viewState: RegisterViewState) {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppTextFieldWithText(
            modifier = Modifier,
            modifierChild = Modifier
                .height(50.dp),
            title = R.string.tv_first_name,
            placeHolder = R.string.plc_first_name,
            value = viewState.firstName,
            onValueChange = {
                viewModel.firstNameText(it)
            },
            keyboardType = KeyboardType.Text
        )

        AppTextFieldWithText(
            modifier = Modifier,
            modifierChild = Modifier
                .height(50.dp),
            title = R.string.tv_last_name,
            placeHolder = R.string.plc_last_name,
            value = viewState.lastName,
            onValueChange = {
                viewModel.lastNameText(it)
            },
            keyboardType = KeyboardType.Text
        )

        AppTextFieldWithText(
            modifier = Modifier,
            modifierChild = Modifier
                .height(50.dp),
            title = R.string.tv_email,
            placeHolder = R.string.plc_email,
            value = viewState.email,
            onValueChange = {
                viewModel.emailText(it)
            },
            keyboardType = KeyboardType.Email
        )

        AppTextFieldWithText(
            modifier = Modifier,
            modifierChild = Modifier
                .height(50.dp),
            title = R.string.tv_skill,
            placeHolder = R.string.plc_skill,
            value = viewState.skills,
            onValueChange = {
                viewModel.skillsText(it)
            },
            keyboardType = KeyboardType.Text
        )

        AppTextFieldWithText(
            modifier = Modifier,
            modifierChild = Modifier
                .height(200.dp),
            title = R.string.tv_bio,
            placeHolder = R.string.plc_bio,
            singleLine = false,
            value = viewState.bio,
            maxLines = 10,
            onValueChange = {
                viewModel.bioText(it)
            },
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(30.dp))

        AppButton(
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 50.dp, end = 50.dp),
            shapes = 50,
            text = stringResource(id = R.string.tv_save),
            colors = Black,
            paddingValues = PaddingValues(horizontal = 40.dp),
            onClick = {
                viewModel.onTriggerEvent(RegisterViewEvent.ValidateAndSaveData)
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
//    HeaderView(modifier = Modifier)
    RegisterView(viewModel = hiltViewModel())
}