package com.example.catexplorer.screens.wallpapers.multifab

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MultiFloatingActionButton(
    fabIcon: ImageVector,
    toState: MultiFabState,
    stateChanged: (fabState: MultiFabState) -> Unit
) {

    val transition: Transition<MultiFabState> = updateTransition(
        targetState = toState,
        label = "multi_fab"
    )

    val rotation: Float by transition.animateFloat(label = "multi_fab") { state ->
        if (state == MultiFabState.EXPANDED) 45f else 0f
    }

    FloatingActionButton(onClick = {
        stateChanged(
            if (transition.currentState == MultiFabState.EXPANDED) {
                MultiFabState.COLLAPSED
            } else MultiFabState.EXPANDED
        )
    }, modifier = Modifier.padding(vertical = 100.dp))
    {
        Icon(imageVector = fabIcon, contentDescription = "fab", modifier = Modifier.rotate(rotation))
    }

}