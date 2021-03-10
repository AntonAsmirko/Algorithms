import java.util.*
import kotlin.collections.ArrayList

val scanner = Scanner(System.`in`)
val n = scanner.nextInt()
val m = scanner.nextInt()
val a = scanner.nextInt()
val b = scanner.nextInt()
var empty = 0

val graph = Array<ArrayList<Int>?>(n * m) { null }

val desk = Array(n) { scanner.next().toCharArray() }

var used: BooleanArray? = null
val mt = IntArray(n*m){-1}

fun dfs(v: Int): Boolean {
    if (!used!![v]) {
        used!![v] = true
        for (i in (graph[v]?.indices ?: return false)) {
            val to = graph[v]!![i]
            if (mt[to] == -1 || dfs(mt[to])) {
                mt[to] = v
                return true
            }
        }
    }
    return false
}

fun main() {
    for (i in desk.indices) {
        for (j in desk[i].indices) {
            if (desk[i][j] != '.') {
                empty++
                if ((i + j) % 2 == 0) {
                    val tmp = arrayListOf<Int>()
                    if (j > 0 && desk[i][j-1] == '*')
                        tmp.add(i * m + j - 1)
                    if (j < m - 1 && desk[i][j+1] == '*')
                        tmp.add(i * m + j + 1)
                    if (i > 0 && desk[i - 1][j] == '*')
                        tmp.add((i - 1) * m + j)
                    if (i < n - 1 && desk[i + 1][j] == '*')
                        tmp.add((i + 1) * m + j)
                    graph[i * m + j] = tmp
                }
            }
        }
    }
    if (2 * b <= a) {
        println(empty * b)
        return
    }
    for (i in 0 until n){
        for (j in 0 until m){
            if((i+j) % 2 ==0){
                used = BooleanArray(n*m)
                dfs(i * m + j)
            }
        }
    }
    var i = 0
    var c = 0
    while (i < n * m){
        if(mt[i] != -1)
            c++
        i++
    }
    println(c * a + b * (empty - 2 * c))
}