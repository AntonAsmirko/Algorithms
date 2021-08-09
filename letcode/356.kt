import java.util.LinkedList

class Solution {
    fun isReflected(points: Array<IntArray>): Boolean {
        if (points.size == 1) {
            return true
        }
        val pSortet = points.sortedBy { it[1] }.sortedBy { it[0] }
        val pSorted = arrayListOf<IntArray>()
        pSortet.forEach {
            if (pSorted.size == 0 || pSorted.last()[0] != it[0] || pSorted.last()[1] != it[1]) {
                pSorted.add(it)
            }
        }
        var lineX: Double = 0.0
        if (pSorted.size % 2 == 1) {
            lineX = pSorted[pSorted.size / 2][0].toDouble()
        } else {
            lineX =
                pSorted[0][0].toDouble() + (pSorted[pSorted.size - 1][0].toDouble() - pSorted[0][0].toDouble()) / 2.0
        }
        var l = 0
        var r = pSorted.size - 1
        var leftFold = LinkedList<IntArray>()
        val rightFold = LinkedList<IntArray>()
        while (l < r) {
            leftFold.add(pSorted[l])
            rightFold.add(pSorted[r])
            l++
            r--
        }
        leftFold.sortBy { -it[1] }
        leftFold.sortBy { it[0] }
        while (leftFold.isNotEmpty() && rightFold.isNotEmpty()) {
            val li = leftFold.removeFirst()
            val ri = rightFold.removeFirst()
            if (li[1] != ri[1] && !(li[0] == ri[0] && li[0].toDouble() == lineX)) {
                return false
            }
            if (li[0].toDouble() + (ri[0].toDouble() - li[0].toDouble()) / 2 != lineX) {
                return false
            }
        }
        return true
    }
}
