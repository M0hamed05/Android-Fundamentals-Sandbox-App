package com.depi.testapp.ui.features.profile_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depi.testapp.R
import com.depi.testapp.constraint.enProfile
import com.depi.testapp.ui.components.BackButton

@Composable
fun ProfileScreen(profile: enProfile, onBack:()->Unit) {
    BackHandler {
        onBack()
    }
    Column(
        modifier = Modifier.padding(12.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BackButton(onClick = { onBack()  })
        when (profile) {
            enProfile.geust -> {
                Text(stringResource(R.string.YouAreAguest), fontSize = 20.sp, modifier = Modifier.padding(13.dp))
            }
            enProfile.user -> {
                Text(("You are a user"), fontSize = 20.sp,modifier = Modifier.padding(13.dp))
            }
        }
    }
}