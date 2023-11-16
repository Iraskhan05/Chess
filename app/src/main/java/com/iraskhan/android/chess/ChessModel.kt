package com.iraskhan.android.chess

class ChessModel {
    var piecesBox = mutableSetOf<ChessPiece>()

    init {
        piecesBox.add(ChessPiece(0,0, ChessPlayer.WHITE, ChessRank.ROOK))
    }
    override fun toString(): String {
        var desc = ""
        for(row in 7 downTo 0) {
            desc += "$row"
            for (col in 0..7)
                desc += " ."
            desc += "\n"
        }

        desc += "  0 1 2 3 4 5 6 7"
        return desc
    }
}