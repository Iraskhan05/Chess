package com.iraskhan.android.chess

class ChessModel {
    override fun toString(): String {
        var desc = ""
        for(row in 0..7) {
            desc += (7 - row).toString()
            for (col in 0..7)
                desc += " ."
            desc += "\n"
        }

        desc += " 1 2 3 4 5 6 7"
        return desc
    }
}