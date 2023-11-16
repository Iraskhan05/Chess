package com.iraskhan.android.chess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val originX = 20f
    private val originY = 500f
    private val cellSide = 130f
    override fun onDrawForeground(canvas: Canvas?) {
        val paint = Paint()

        for (i in 0..7) {
            for (j in 0..7) {
                paint.color = if ((i + j) % 2 == 1) Color.DKGRAY else Color.LTGRAY
                canvas?.drawRect(
                    originX + i * cellSide,
                    originY + j * cellSide,
                    originX + (i + 1) * cellSide,
                    originY + (j + 1) * cellSide,
                    paint
                )
            }
        }
    }
}