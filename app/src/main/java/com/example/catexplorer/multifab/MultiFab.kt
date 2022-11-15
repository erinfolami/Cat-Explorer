package com.example.catexplorer.multifab

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

@Composable
fun MultiFloatingActionButton(
    items: List<MultiFabItem>,
    showLabels: Boolean = true,
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
            items.forEach { item ->
                MiniFabItem(item, showLabels, onFabItemClicked)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        FloatingActionButton(
            onClick = {
                stateChanged(
                    if (transition.currentState == MultiFabState.EXPANDED) {
                        MultiFabState.COLLAPSED
                    } else MultiFabState.EXPANDED
                )
            },
            modifier = Modifier.paddingFromBaseline(bottom = 150.dp),
            backgroundColor = MaterialTheme.colors.primary
        )
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
    showLabels: Boolean,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val buttonColor = MaterialTheme.colors.primary

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        if (showLabels) {
            Text(
                item.label, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(start = 5.dp, end = 5.dp, top = 3.dp, bottom = 3.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

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

}