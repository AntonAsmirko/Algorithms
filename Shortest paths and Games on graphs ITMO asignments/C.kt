import java.lang.Integer.max
import java.util.Scanner
import kotlin.system.exitProcess

val scanner = Scanner(System.`in`)

val n = scanner.nextInt()

val adjMatrix = Array(n) { Array(n) { scanner.nextInt() } }

val inf = 100_000

fun main() {
    val d = ArrayList<Int>(n)
    val p = ArrayList<Int>(n)
    for (i in 1..n) {
        p.add(-1)
        d.add(0)
    }
    var x = -1
    for (i in 0 until n) {
        x = -1
        for (j in 0 until n) for (k in 0 until n) {
            if (adjMatrix[j][k] == inf) continue
            if (d[k] > d[j] + adjMatrix[j][k]) {
                d[k] = max(inf * -100, d[j] + adjMatrix[j][k])
                p[k] = j
                x = k
            }
        }
    }
    if (x == -1) {
        println("NO")
        exitProcess(0)
    }
    println("YES")
    var y = x
    for (i in 0 until n) y = p[y]
    val path = arrayListOf<Int>()
    var cur = y
    while (true) {
        path.add(cur)
        cur = p[cur]
        if (cur == y && path.size > 1) break
    }
    println(path.size)
    path.reversed().forEach { print("${it + 1} ") }
}
