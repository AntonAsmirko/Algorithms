import java.lang.Integer.min
import java.util.*

val scanner = Scanner(System.`in`)

val n = scanner.nextInt()

val m = scanner.nextInt()

val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }

var time = 0

val enter = arrayOfNulls<Int>(n)

val ret = arrayOfNulls<Int>(n)

val mark = BooleanArray(n) { false }

val ans = mutableListOf<Int>()

fun main() {
    for (i in 0 until m) {
        val u = scanner.nextInt() - 1
        val v = scanner.nextInt() - 1
        graph[u].add(Pair(v, i + 1))
        graph[v].add(Pair(u, i + 1))
    }
    for (i in 0 until n) if (!mark[i]) dfs(i)
    println(ans.size)
    ans.sort().also { ans.forEach { print("$it ") } }
}

fun dfs(v: Int, parent: Int = -1) {
    time++
    enter[v] = time
    ret[v] = time
    mark[v] = true
    for (u in graph[v]) {
        if (parent == u.first) continue
        if (mark[u.first]) ret[v] = min(ret[v]!!, enter[u.first]!!)
        else {
            dfs(u.first, v)
            ret[v] = min(ret[v]!!, ret[u.first]!!)
            if (ret[u.first]!! > enter[v]!!) ans.add(u.second)
        }
    }
}
