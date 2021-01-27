import java.util.Scanner

val scanner = Scanner(System.`in`)

val n = scanner.nextInt()

val m = scanner.nextInt()

val graph = Array(n) { mutableListOf<Int>() }

val used = BooleanArray(n) { false }

val grandi = Array(n) { -1 }

fun main() {
    for (i in 0 until m) graph[scanner.nextInt() - 1].add(scanner.nextInt() - 1)
    for (i in 0 until n) dfs(i)
    grandi.forEach { println(it) }
}

fun dfs(vertex: Int) {
    used[vertex] = true
    if (graph[vertex].size == 0) {
        grandi[vertex] = 0
        return
    }
    val nextGrandis = mutableSetOf<Int>()
    for (child in graph[vertex]) {
        if (!used[child]) dfs(child)
        nextGrandis.add(grandi[child])
    }
    if (0 !in nextGrandis) {
        grandi[vertex] = 0
    } else {
        var i = 0
        while (i in nextGrandis) i++
        grandi[vertex] = i
    }
}
