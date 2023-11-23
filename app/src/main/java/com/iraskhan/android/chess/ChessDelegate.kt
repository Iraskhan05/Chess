package com.iraskhan.android.chess

interface ChessDelegate {
    fun pieceAt(col: Int, row: Int):ChessPiece?
}