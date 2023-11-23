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
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val originX = 20f
    private val originY = 500f
    private val cellSide = 130f
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

    init {
        loadBitmaps()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas?) {
        val chessModel = ChessModel()
        chessModel.reset()
        for(row in 0..7){
            for(col in 0..7){
                val piece = chessModel.pieceAt(col, row)
                if(piece != null){
                    drawPieceAt(canvas, col, row, piece.resID)
                }
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