import java.util.*
import kotlin.Comparator

private val scanner = Scanner(System.`in`)

private val n = scanner.nextInt()

private val m = scanner.nextInt()

private var time = 0

private val enter = arrayOfNulls<Int>(n)

private val ret = arrayOfNulls<Int>(n)

private val mark = BooleanArray(n) { false }

private val ans = mutableListOf<Int>()

private val colors = Array(n) { 0 }

private var maxColor = 0

private val graph = Array(n) { mutableListOf<Int>() }

private val dualEdges =
        TreeSet<ComparablePair>(
                Comparator { first: ComparablePair, second: ComparablePair ->
                    when {
                        first.first > second.first -> 1
                        first.first == second.first ->
                                when {
                                    first.second > second.second -> 1
                                    first.second == second.second -> 0
                                    else -> -1
                                }
                        else -> -1
                    }
                })

class ComparablePair(val first: Int, val second: Int) : Comparable<ComparablePair> {
    override fun compareTo(other: ComparablePair): Int {
        return when {
            this.first > other.first -> 1
            this.first == other.first ->
                    when {
                        this.second > other.second -> 1
                        this.second == other.second -> 0
                        else -> -1
                    }
            else -> -1
        }
    }
}

fun main() {
    for (i in 0 until m) {
        val start = scanner.nextInt() - 1
        val end = scanner.nextInt() - 1
        if (graph[start].contains(end))
                dualEdges.add(ComparablePair(start, end)).also {
                    dualEdges.add(ComparablePair(end, start))
                }
        graph[start].add(end)
        graph[end].add(start)
    }
    dfs(0)
    solve()
    println(colors.toSet().size)
    colors.forEach { print("$it ") }
}

private fun dfs(v: Int, parent: Int = -1) {
    time++
    enter[v] = time
    ret[v] = time
    mark[v] = true
    for (u in graph[v]) {
        if (parent == u) continue
        if (mark[u]) ret[v] = Integer.min(ret[v]!!, enter[u]!!)
        else {
            dfs(u, v)
            ret[v] = Integer.min(ret[v]!!, ret[u]!!)
            if (ret[u]!! > enter[v]!!) ans.add(u)
        }
    }
}

private fun paint(v: Int, color: Int) {
    colors[v] = color
    for (u in graph[v]) if (colors[u] == 0)
            if (ret[u]!! > enter[v]!! &&
                    ComparablePair(u, v) !in dualEdges &&
                    ComparablePair(v, u) !in dualEdges)
                    paint(u, ++maxColor)
            else paint(u, color)
}

private fun solve() {
    for (v in graph.indices) {
        colors[v] = 0
        if (enter[v] == null) dfs(v)
    }
    maxColor = 0
    for (v in graph.indices) if (colors[v] == 0) paint(v, ++maxColor)
}
