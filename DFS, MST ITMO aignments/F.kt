import java.util.Scanner

val scanner = Scanner(System.`in`)

val n = scanner.nextInt()

val m = scanner.nextInt()

val graph = Array(n) { Vertex() }

val list = mutableListOf<Int>()

class Vertex(
        var used: Int = 0,
        var color: Int = 0,
        val edges: MutableList<Int> = mutableListOf(),
        val edgesT: MutableList<Int> = mutableListOf()
)

fun main() {
    var color = 0
    val r = Array(10_000) { mutableSetOf<Int>() }
    for (i in 1..m) {
        val u = scanner.nextInt() - 1
        val v = scanner.nextInt() - 1
        graph[u].edges.add(v)
        graph[v].edgesT.add(u)
    }
    for (i in 0 until n) if (graph[i].used != 1) dfsG(i)
    for (i in list.reversed()) if (graph[i].used != 2) dfsTG(i, color++)
    for (i in 0 until n) for (j in graph[i].edges) if (graph[i].color != graph[j].color)
            r[graph[i].color].add(graph[j].color)
    var ans = 0
    r.forEach { ans += it.size }
    println(ans)
}

fun dfsG(v: Int) {
    graph[v].used = 1
    for (i in graph[v].edges) if (graph[i].used != 1) dfsG(i)
    list.add(v)
}

fun dfsTG(v: Int, color: Int) {
    graph[v].used = 2
    graph[v].color = color
    for (i in graph[v].edgesT) if (graph[i].used != 2) dfsTG(i, color)
}
