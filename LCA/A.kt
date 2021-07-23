import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.ln

val scanner = Scanner(System.`in`)
val numNodes = scanner.nextInt()
val vertexes = Array(numNodes) { 0 }

val up = Array(numNodes + 1) { Array(18) { 0 } }
val tree = Array(numNodes + 1) { ArrayList<Int>() }
val level = Array(numNodes + 1) { 0 }

fun makeTree() {
    for (i in 1..numNodes) tree[vertexes[i - 1]].add(i)
}

fun printKthAncestors(node: Int, root: Int) {
    if (node != root)
        println("$node: ${up[node].filter { i -> i != 0 }.joinToString(" ")}")
    else println("$node:")
}

fun precomputeUp(node: Int, parent: Int) {
    up[node][0] = parent
    for (i in 1..ceil(ln(level[node].toDouble()) / ln(2.0)).toInt())
        up[node][i] = up[up[node][i - 1]][i - 1]
    for (v in tree[node]) {
        if (v != parent) {
            level[v] = level[node] + 1
            precomputeUp(v, node)
        }
    }
}


fun main(args: Array<String>) {
    var root = -1
    for (i in 0 until numNodes) {
        vertexes[i] = scanner.nextInt()
        if (vertexes[i] == 0) root = i + 1
    }
    makeTree()
    precomputeUp(root, root)
    for(i in 1..numNodes)
        printKthAncestors(i, root)
}
