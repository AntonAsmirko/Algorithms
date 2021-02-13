import java.util.*

class Cartesian {

    private var root: Node? = null

    private fun merge(A: Node?, B: Node?): Node? {
        if (A == null) return B
        if (B == null) return A
        return if (A.key > B.key) {
            A.rCh = merge(A.rCh, B)
            A
        } else {
            B.lCh = merge(A, B.lCh)
            B
        }
    }

    private fun split(A: Node?, `val`: Int): Pair<Node?, Node?> {
        if (A == null) {
            return Pair(null, null)
        }
        return if (A.value < `val`) {
            val p = split(A.rCh, `val`)
            A.rCh = p.first
            Pair(A, p.second)
        } else {
            val p = split(A.lCh, `val`)
            A.lCh = p.second
            Pair(p.first, A)
        }
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
                node
            }
            node.value < a.value -> {
                a.lCh = insert(a.lCh, node)
                a
            }
            else -> {
                a.rCh = insert(a.rCh, node)
                a
            }
        }
    }

    private fun find(x: Int, root: Node?): Node? {
        return when {
            root == null || root.value == x -> root
            root.value > x -> find(x, root.lCh)
            else -> find(x, root.rCh)
        }
    }

    private fun find(x: Int) = find(x, root)

    fun exists(x: Int) = find(x) != null

    private fun remove(r: Node?, x: Int): Node? {
        val p0 = split(r, x)
        val p1 = split(p0.second, x + 1)
        return merge(p0.first, p1.second)
    }

    fun remove(x: Int) {
        root = remove(root, x)
    }

    private fun next(x: Int, r: Node?): Int? {
        return when {
            r == null -> null
            r.value <= x -> next(x, r.rCh)
            else -> next(x, r.lCh) ?: r.value
        }
    }

    private fun prev(x: Int, r: Node?): Int? {
        return when {
            r == null -> null
            r.value >= x -> prev(x, r.lCh)
            else -> prev(x, r.rCh) ?: r.value
        }
    }

    fun prev(x: Int) = prev(x, root)
    fun next(x: Int) = next(x, root)

}

data class Node(
    var value: Int,
    var key: Int,
    var lCh: Node?,
    var rCh: Node?
)

fun main(args: Array<String>) {
    val random = Random(System.currentTimeMillis())
    val scanner = Scanner(System.`in`)
    val cartesianTree = Cartesian()
    while (scanner.hasNext()) {
        when (scanner.next()) {
            "insert" -> cartesianTree.insert(
                Node(
                    scanner.nextInt(),
                    random.nextInt(),
                    null, null
                )
            )
            "delete" -> cartesianTree.remove(scanner.nextInt())
            "exists" -> println(cartesianTree.exists(scanner.nextInt()))
            "next" -> println(cartesianTree.next(scanner.nextInt()) ?: "none")
            "prev" -> println(cartesianTree.prev(scanner.nextInt()) ?: "none")
        }
    }
}