package com.depi.testapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depi.testapp.R
import com.depi.testapp.ui.theme.mainBlackColor

@Composable
fun MainButton(text:String,fontsize: Int = 18,padding: Int = 16,CornerRadius:Int = 14,
               containerColor: Color = mainBlackColor,contentColor:Color = Color.White,
               onClick: () -> Unit
) {
    Button(
        onClick = onClick,//should be like this
        modifier = Modifier.fillMaxWidth().padding(padding.dp),
        shape = RoundedCornerShape(CornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp,mainBlackColor)
    ) {
        Text(text = text, fontSize = fontsize.sp, modifier = Modifier.padding(vertical = 4.dp))
    }
}

@Composable
fun HeadlineWord(text: String,textAlign: TextAlign = TextAlign.Center,paddingVertical: Int = 20){
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = mainBlackColor,
        modifier = Modifier.padding(vertical = paddingVertical.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun MainTextField(text: String, placeholder: String, OnValueChanged :(String) ->Unit, padding: Int = 4){
    OutlinedTextField(
        value = text,
        onValueChange = { OnValueChanged(it) },
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth().padding(padding.dp),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF7F8F9),
            unfocusedContainerColor = Color(0xFFF7F8F9),
            focusedBorderColor = Color(0xFFE8ECF4),
            unfocusedBorderColor = Color(0xFFE8ECF4)
        ),
    )
}

@Composable
fun MainTextFieldPassword(passwordValue: String,passwordVisible: Boolean,
                          onPasswordChange :(String) ->Unit,onToggleVisible: ()->Unit,padding: Int = 4) {
    Column(

    ) {
        val isError = passwordValue.isNotEmpty() && passwordValue.length < 8
        OutlinedTextField(
            value = passwordValue,
            onValueChange = { onPasswordChange(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.EnterYourPasswordSentence),
                    color = Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth().padding(padding.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF7F8F9),
                unfocusedContainerColor = Color(0xFFF7F8F9),
                focusedBorderColor = Color(0xFFE8ECF4),
                unfocusedBorderColor = Color(0xFFE8ECF4)
            ),

            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(
                    onClick = { onToggleVisible() }
                ) {
                    Icon(imageVector = image, contentDescription = "eye image", tint = Color.Gray)
                }
            }
        )
        if (isError) {
            Text(
                text = stringResource(R.string.PasswordErrorSentence),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 24.dp, top = 4.dp)
            )
        }
        Text(
            stringResource(R.string.ForgetPasswordSentence),
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            color = mainBlackColor,
            fontSize = 15.sp,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun MainTextFieldEmail(EmailValue: String, OnEmailChanged :(String) ->Unit,padding: Int = 4) {
    val isError = EmailValue.isNotEmpty() && !EmailValue.contains('@')
    OutlinedTextField(
        value = EmailValue,
        onValueChange = { OnEmailChanged(it) },
        placeholder = { Text(text = stringResource(R.string.EnterYourEmailSentence), color = Color.Gray) },
        modifier = Modifier.fillMaxWidth().padding(padding.dp),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF7F8F9),
            unfocusedContainerColor = Color(0xFFF7F8F9),
            focusedBorderColor = Color(0xFFE8ECF4),
            unfocusedBorderColor = Color(0xFFE8ECF4)
        ),
    )
    if (isError) {
        Text(
            text = stringResource(R.string.EmailErrorSentence),
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 24.dp, top = 4.dp)
        )
    }
}

@Composable
fun SocialLoginDivider(text: String,padding: Int = 16) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = padding.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp),
            color = mainBlackColor,
            fontSize = 14.sp
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.size(40.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, mainBlackColor),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}



