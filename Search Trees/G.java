import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class Cartesian {

    private var root: Node? = null
    private var countNodes = 0
    private val ans = mutableListOf<Long>()

    private fun merge(A: Node?, B: Node?): Node? {
        if (A == null) return B
        if (B == null) return A
        reEval(A)
        reEval(B)
        return if (A.key > B.key) {
            A.rCh = merge(A.rCh, B)
            reEval(A)
            A
        } else {
            B.lCh = merge(A, B.lCh)
            reEval(B)
            B
        }
    }


    private fun split(A: Node?, B: Long): Pair<Node?, Node?> {
        if (A == null) {
            return Pair(null, null)
        }
        reEval(A)
        return if ((A.lCh?.innerVal ?: 0) < B) {
            val p = split(A.rCh, B - (A.lCh?.innerVal ?: 0) - 1)
            A.rCh = p.first
            reEval(A)
            Pair(A, p.second)
        } else {
            val p = split(A.lCh, B)
            A.lCh = p.second
            reEval(A)
            Pair(p.first, A)
        }
    }


    private fun reEval(node: Node) {
        node.innerVal = (node.lCh?.innerVal ?: 0) + (node.rCh?.innerVal ?: 0) + 1
    }

    fun insert(a: Node) {
        countNodes++
        root = merge(root, a)
    }

    fun moveToOrigin(l: Long, r: Long) {
        val p1 = split(root, l)
        val p2 = split(p1.second, r - l)
        root = merge(merge(p2.first, p1.first), p2.second)
        reEval(root!!)
    }

    private fun printTree(node: Node?) {
        if (node == null) return
        reEval(root!!)
        printTree(node.rCh)
        ans.add(node.value)
        printTree(node.lCh)
    }

    fun printTree() {
        printTree(root)
        ans.reverse()
        for (i in ans) {
            print("$i ")
        }
    }
}

private class FastReader internal constructor(input: InputStream?) {
    var br: BufferedReader
    var st: StringTokenizer? = null
    operator fun next(): String? {
        while (st == null || !st!!.hasMoreElements()) {
            st = try {
                StringTokenizer(br.readLine())
            } catch (e: Exception) {
                return null
            }
        }
        return st!!.nextToken()
    }

    fun nextLong(): Long {
        return next()!!.toLong()
    }

    init {
        br = BufferedReader(InputStreamReader(input))
    }
}

data class Node(
    var value: Long,
    var key: Int,
    var lCh: Node?,
    var rCh: Node?,
    var innerVal: Long
)

data class Node2(
    var index: Int,
    var value: Int
)


fun concatenate(vararg lists: List<Int>): MutableList<Int> {
    return mutableListOf(*lists).flatten() as MutableList<Int>
}

fun main(args: Array<String>) {
    val scanner = FastReader(System.`in`)
    val cartesian = Cartesian()
    val random = Random()
    val n = scanner.nextLong()
    val m = scanner.nextLong()
    for (i in 1..n) cartesian.insert(Node(i, random.nextInt(), null, null, 1))
    for (i in 1..m) cartesian.moveToOrigin(scanner.nextLong() - 1, scanner.nextLong())
    cartesian.printTree()
}

fun arInit(a: Int): Int? {
    return null
}