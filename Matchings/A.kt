import java.util.*

val scanner = Scanner(System.`in`)
val n = scanner.nextInt()
val m = scanner.nextInt()
lateinit var graph: Array<ArrayList<Int>>
lateinit var used: BooleanArray
val mt = Array(m) { -1 }

fun tryKuhn(v: Int): Boolean {
    if (used[v]) return false
    used[v] = true
    graph[v].forEach {
        if (mt[it] == -1 || tryKuhn(mt[it])) {
            mt[it] = v
            return true
        }
    }
    return false
}

fun main() {
    graph = Array(n) { arrayListOf() }
    for (i in 0 until n) {
        var tmp = scanner.nextInt()
        while (tmp != 0) {
            graph[i].add(tmp - 1)
            tmp = scanner.nextInt()
        }
    }
    for (i in 0 until n) {
        used = BooleanArray(n)
        tryKuhn(i)
    }
    println(mt.count { it != -1 })
    mt.forEachIndexed { index, i -> if (i != -1) println("${i + 1} ${index + 1}") }
}
