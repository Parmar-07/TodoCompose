package com.example.todocompose.presentation.ui.features.add.composes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.features.add.model.AddNoteUIModel
import kotlinx.coroutines.launch


data class ShowNoteColors(val noteBgAnim: Animatable<Color, AnimationVector4D>) :
    CreateCompose<AddNoteUIModel>() {

    @Composable
    override fun Generate(
        navController: NavController,
        data: AddNoteUIModel
    ) {
        val scope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            data.noteColors.forEach { color ->
                val colorInt = color.toArgb()
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = 3.dp,
                            color = getBorderColor(data, colorInt),
                            shape = CircleShape
                        )
                        .clickable {
                            val selectColor = Color(colorInt)
                            scope.launch {
                                noteBgAnim.animateTo(
                                    targetValue = selectColor,
                                    animationSpec = tween(
                                        durationMillis = 500
                                    )
                                )
                            }
                            data.noteColor.onObserveDataValue(selectColor)
                        }
                ) {

                }
            }
        }
    }

    @Composable
    private fun getBorderColor(data: AddNoteUIModel,color : Int):Color{
        val choosedColor = noteBgAnim.value
        return if (choosedColor.toArgb() == color){
            Color.Black
        } else {
            Color.Transparent
        }
    }


}