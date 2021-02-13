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
        reEval(B)
        reEval(A)
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
        if (root != null) reEval(root!!)
    }

    fun delete(item: Long) {
        countNodes--
        val lr = split(root, item - 1)
        root = merge(lr.first, split(lr.second, 1).second)
        if (root != null) reEval(root!!)

    }

    fun size(): Int {
        return countNodes
    }

    fun add(pos: Long, `val`: Long) {

        countNodes++
        val lr = split(root, pos)
        root = merge(
            merge(
                lr.first, Node(
                    `val`,
                    Random().nextInt(), null, null, 0
                )
            ),
            lr.second
        )
        if (root != null) reEval(root!!)
    }

    private fun printTree(node: Node?) {
        if (node == null) return
        printTree(node.rCh)
        ans.add(node.value)
        printTree(node.lCh)
    }

    fun printTree(){
        printTree(root)
        ans.reverse()
        for(i in ans){
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

fun main(args: Array<String>) {
    val scanner = FastReader(System.`in`)
    val random = Random()
    val cartesianTree = Cartesian()
    val n = scanner.nextLong()
    val m = scanner.nextLong()
    for (i in 0 until n) {
        cartesianTree.insert(
            Node(
                scanner.nextLong(),
                random.nextInt(), null, null, i
            )
        )
    }
    for (i in 0 until m) {
        when (scanner.next()) {
            "add" -> {
                cartesianTree.add(scanner.nextLong(), scanner.nextLong())
            }
            "del" -> {
                cartesianTree.delete(scanner.nextLong())
            }
        }
    }
    println(cartesianTree.size())
    cartesianTree.printTree()
}