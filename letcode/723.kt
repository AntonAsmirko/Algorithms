class Solution {
    fun candyCrush(board: Array<IntArray>): Array<IntArray> {
        while (true) {
            val arr = mutableListOf<Quadruple>()
            for (i in board.size - 1 downTo 0) {
                for (j in 0 until board[i].size) {
                    canCrushVertical(i, j, board)?.let {
                        arr.add(it)
                    }
                    canCrushHorizontal(i, j, board)?.let {
                        arr.add(it)
                    }
                }
            }
            if (arr.isEmpty()) {
                break
            }
            arr.forEach {
                it.apply {
                    if (fo) {
                        crushVertical(f, s, t, board)
                    } else {
                        crushHorizontal(f, s, t, board)
                    }
                }
            }
            dump(board)
        }
        return board
    }

    fun canCrushVertical(i: Int, j: Int, board: Array<IntArray>): Quadruple? {
        if (board[i][j] == 0) {
            return null
        }
        var ii = i - 1
        var count = 1
        while (ii >= 0 && board[ii][j] == board[i][j]) {
            count++
            ii--
        }
        if (count >= 3) {
            return Quadruple(count, i, j, true)
        }
        return null
    }

    fun canCrushHorizontal(i: Int, j: Int, board: Array<IntArray>): Quadruple? {
        if (board[i][j] == 0) {
            return null
        }
        var jj = j + 1
        var count = 1
        while (jj < board[i].size && board[i][jj] == board[i][j]) {
            count++
            jj++
        }
        if (count >= 3) {
            return Quadruple(count, i, j, false)
        }
        return null
    }

    fun crushVertical(count: Int, i: Int, j: Int, board: Array<IntArray>) {
        for (u in i downTo i - count + 1) {
            board[u][j] = 0
        }
    }

    fun crushHorizontal(count: Int, i: Int, j: Int, board: Array<IntArray>) {
        for (u in j until j + count) {
            board[i][u] = 0
        }
    }

    fun dump(board: Array<IntArray>) {
        val height = board.size
        val width = board[0].size
        for (j in 0 until width) {
            val countZeroes = IntArray(height)
            if (board[height - 1][j] == 0) {
                countZeroes[0] = 1
            }
            for (i in height - 2 downTo 0) {
                if (board[i][j] == 0) {
                    countZeroes[height - 1 - i]++
                }
                countZeroes[height - 1 - i] += countZeroes[height - 2 - i]
            }
            for (i in height - 1 downTo 0) {
                if (board[i][j] != 0) {
                    board[i + countZeroes[height - 1 - i]][j] = board[i][j]
                }
                if (i < countZeroes[countZeroes.size - 1]) {
                    board[i][j] = 0
                }
            }
        }
    }

    class Quadruple(val f: Int, val s: Int, val t: Int, val fo: Boolean)
}
