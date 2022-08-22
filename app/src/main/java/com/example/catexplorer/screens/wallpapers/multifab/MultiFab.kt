package com.example.catexplorer.screens.wallpapers.multifab

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MultiFloatingActionButton(
    item: MultiFabItem,
    fabIcon: ImageVector,
    toState: MultiFabState,
    stateChanged: (fabState: MultiFabState) -> Unit,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    val transition: Transition<MultiFabState> = updateTransition(
        targetState = toState,
        label = "multi_fab"
    )

    val rotation: Float by transition.animateFloat(label = "multi_fab") { state ->
        if (state == MultiFabState.EXPANDED) 45f else 0f
    }


    Column(horizontalAlignment = Alignment.End) {
        if (transition.currentState == MultiFabState.EXPANDED) {
            MiniFabItem(item, onFabItemClicked)
            Spacer(modifier = Modifier.height(20.dp))
        }

        FloatingActionButton(onClick = {
            stateChanged(
                if (transition.currentState == MultiFabState.EXPANDED) {
                    MultiFabState.COLLAPSED
                } else MultiFabState.EXPANDED
            )
        }, modifier = Modifier.padding(vertical = 100.dp))
        {
            Icon(
                imageVector = fabIcon,
                contentDescription = "fab",
                modifier = Modifier.rotate(rotation)
            )
        }

    }
}


@Composable
fun MiniFabItem(
    item: MultiFabItem,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val buttonColor = MaterialTheme.colors.secondary

    Canvas(
        modifier = Modifier
            .size(32.dp)
            .clickable(
                onClick = { onFabItemClicked(item) },
                indication = rememberRipple(
                    bounded = false,
                    radius = 20.dp,
                    color = MaterialTheme.colors.onSecondary
                ),
                interactionSource = interactionSource
            )
    ) {
        drawCircle(color = buttonColor, 56f)

        drawImage(
            item.icon,
            topLeft = Offset(
                (this.center.x) - (item.icon.width / 2),
                (this.center.y) - (item.icon.width / 2)
            )
        )
    }
}