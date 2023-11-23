package com.iraskhan.android.chess

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val originX = 20f
    private val originY = 500f
    private val cellSide = 130f
    private val lightColor = Color.argb(1f, .9f, .9f, .9f)
    private val darkColor = Color.argb(1f, .7f, .7f, 7f)
    private val imgResIDs = setOf(
        R.drawable.bishop_black,
        R.drawable.bishop_white,
        R.drawable.king_black,
        R.drawable.king_white,
        R.drawable.queen_white,
        R.drawable.queen_black,
        R.drawable.rook_black,
        R.drawable.rook_white,
        R.drawable.knight_black,
        R.drawable.king_white,
        R.drawable.pawn_black,
        R.drawable.pawn_white
    )
    private val bitmaps = mutableMapOf<Int, Bitmap>()
    private val paint = Paint()

    var chessDelegate: ChessDelegate? = null

    init {
        loadBitmaps()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas?) {
        for (row in 0..7) {
            for (col in 0..7) {
                chessDelegate?.pieceAt(col, row)
                    ?.let { drawPieceAt(canvas, col, row, it.resID) }
            }
        }
    }

    private fun drawPieceAt(canvas: Canvas?, col: Int, row: Int, resID: Int) {
        val bitmap = bitmaps[resID]!!
        canvas?.drawBitmap(
            bitmap,
            null,
            RectF(
                originX + col * cellSide,
                originX + (7 - row) * cellSide,
                originX + (col + 1) * cellSide,
                originX + (7 - row + 1) * cellSide
            ),
            paint
        )
    }

    private fun loadBitmaps() {
        imgResIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun drawChessboard(canvas: Canvas?) {

        for (row in 0..7) {
            for (col in 0..7) {
                drawSquareAt(canvas, col, row, (col + row) % 2 == 1)
            }
        }
    }

    private fun drawSquareAt(canvas: Canvas?, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkColor else lightColor
        canvas?.drawRect(
            originX + col * cellSide,
            originY + row * cellSide,
            originX + (col + 1) * cellSide,
            originY + (row + 1) * cellSide,
            paint
        )
    }
}