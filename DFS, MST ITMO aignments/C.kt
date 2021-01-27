import java.util.*

val scanner = Scanner(System.`in`)

val n = scanner.nextInt()

val m = scanner.nextInt()

val graph = Array(n) { mutableListOf<Int>() }

val used = BooleanArray(n)

val timeIn = Array(n) { Int.MAX_VALUE }

val up = Array(n) { Int.MAX_VALUE }

var time = 0

val ans = TreeSet<Int>()

fun main() {
    for (i in 0 until m) {
        val u = scanner.nextInt() - 1
        val v = scanner.nextInt() - 1
        graph[u].add(v)
        graph[v].add(u)
    }
    for (i in 0 until n) if (!used[i]) dfs(i)
    println(ans.size).also { ans.forEach { print("${it + 1} ") } }
}

fun dfs(v: Int, p: Int = -1) {
    used[v] = true
    time++
    timeIn[v] = time
    up[v] = time
    var children = 0
    for (u in graph[v]) {
        if (u == p) continue
        if (used[u]) up[v] = minOf(up[v], timeIn[u])
        else {
            dfs(u, v)
            up[v] = minOf(up[v], up[u])
            if (up[u] >= timeIn[v] && p != -1) ans.add(v)
            children++
        }
    }
    if (p == -1 && children > 1) ans.add(v)
}
