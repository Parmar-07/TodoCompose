package com.example.todocompose.presentation.ui.features.list.composes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import com.example.todocompose.domain.model.NoteItemModel
import com.example.todocompose.presentation.ui.composes.CreateCompose
import com.example.todocompose.presentation.ui.composes.LogoIconButton
import com.example.todocompose.presentation.ui.features.list.NoteListViewModel
import com.example.todocompose.presentation.ui.navigators.AppRoute


data class NoteItem(val noteListViewModel: NoteListViewModel) : CreateCompose<NoteItemModel>() {

    @Composable
    override fun Generate(navController: NavController, data: NoteItemModel) {
        val cutCornerSize = 30.dp
        val cornerRadius: Dp = 10.dp
        val bgColor = if (data.color == -1) Color.White.toArgb() else data.color
        val textColor = if (data.color == -1) Color.Black else MaterialTheme.colors.onSurface
        val tintColor = if (data.color == -1) Color.Black else Color.White

        Box(Modifier.fillMaxWidth()) {

            Canvas(modifier = Modifier
                .matchParentSize()
                .clickable {
                    val routeArgs =
                        String.format(AppRoute.EditNoteRoute.argsRoute, data.noteId, data.color)
                    navController.navigate(routeArgs)
                }) {

                val clipPath = Path().apply {
                    lineTo(size.width - cutCornerSize.toPx(), 0f)
                    lineTo(size.width, cutCornerSize.toPx())
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }

                clipPath(clipPath) {
                    drawRoundRect(
                        color = Color(bgColor),
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                    drawRoundRect(
                        color = Color(
                            ColorUtils.blendARGB(
                                bgColor, 0x000000, 0.2f
                            )
                        ),
                        topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                        size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                }


            }
            NoteItemTexts(textColor).Generate(navController, data)
            LogoIconButton(
                iconModifier = Modifier
                    .align(Alignment.BottomEnd),
                iconTintColor = tintColor,
                onClick = {
                    noteListViewModel.deleteNote(data)
                }).Generate(navController, Icons.Default.Delete)


        }

    }


}