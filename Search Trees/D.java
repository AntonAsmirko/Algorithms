import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class Cartesian {

    private var root: Node? = null

    private fun merge(A: Node?, B: Node?): Node? {
        if (A == null) return B
        if (B == null) return A
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
        return if (A.value < B) {
            val p = split(A.rCh, B)
            A.rCh = p.first
            reEval(A)
            reEval(p.second)
            Pair(A, p.second)
        } else {
            val p = split(A.lCh, B)
            A.lCh = p.second
            reEval(A)
            reEval(p.first)
            Pair(p.first, A)
        }
    }

    fun reEval(T: Node?) {
        T?.innerVal = (T?.rCh?.innerVal ?: 0) + (T?.lCh?.innerVal ?: 0) + (T?.value ?: 0)
    }

    fun insert(a: Node) {
        root = when {
            exists(a.value) -> return
            root != null -> insert(root, a)
            else -> a
        }
    }

    private fun insert(a: Node?, node: Node): Node {
        return when {
            (a == null) -> node
            node.key > a.key -> {
                val p = split(a, node.value)
                node.lCh = p.first
                node.rCh = p.second
                reEval(node)
                node
            }
            node.value < a.value -> {
                a.lCh = insert(a.lCh, node)
                reEval(a)
                a
            }
            else -> {
                a.rCh = insert(a.rCh, node)
                reEval(a)
                a
            }
        }
    }

    private fun find(x: Long, root: Node?): Node? {
        return when {
            root == null || root.value == x -> root
            root.value > x -> find(x, root.lCh)
            else -> find(x, root.rCh)
        }
    }

    private fun find(x: Long) = find(x, root)

    fun exists(x: Long) = find(x) != null

    fun countByPredicate(l: Long, r: Long): Long? {
        val fromL = split(root, l)
        val toR = split(fromL.second, r + 1)
        if (toR.first == null) {
            root = merge(fromL.first, merge(toR.first, toR.second))
            return 0
        }
        reEval(toR.first)
        val res = toR.first?.innerVal ?: 0
        root = merge(fromL.first, merge(toR.first, toR.second))
        return res
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

fun main(args: Array<String>) {
    val random = Random()
    val scanner = FastReader(System.`in`)
    val cartesianTree = Cartesian()
    val numOps = scanner.nextLong()
    var isQuestionPrev = false
    var prevVal = 0L
    for (i in 0 until numOps) {
        when (scanner.next()) {
            "+" -> {
                val inp = if (!isQuestionPrev) scanner.nextLong() else (scanner.nextLong() + prevVal) % 1000000000L
                cartesianTree.insert(
                    Node(
                        inp, random.nextInt(), null, null, inp
                    )
                )
                isQuestionPrev = false
            }
            "?" -> {
                prevVal = cartesianTree.countByPredicate(scanner.nextLong(), scanner.nextLong()) ?: 0
                println(prevVal)
                isQuestionPrev = true
            }
        }
    }
}