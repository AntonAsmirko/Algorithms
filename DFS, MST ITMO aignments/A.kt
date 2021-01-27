import java.util.Scanner
import kotlin.system.exitProcess

val scanner = Scanner(System.`in`)
val n = scanner.nextInt()
val m = scanner.nextInt()
var adjMatrix = Array(n) { mutableSetOf<Int>() }
val stack = mutableListOf<Int>()
var visited = Array<Int>(n) { 0 }

fun main() {
    for (i in 0 until m) {
        val u = scanner.nextInt() - 1
        val v = scanner.nextInt() - 1
        adjMatrix[u].add(v)
    }
    for (i in 0 until n) {
        if (visited[i] == 0) {
            topSort(i);
        }
    }
    for (i in stack.reversed())
        print("${i + 1} ")
}

fun topSort(currentVertex: Int) {
    visited[currentVertex] = 1
    for (i in adjMatrix[currentVertex])
        if (visited[i] == 0)
            topSort(i)
        else if (visited[i] == 1) {
            print(-1)
            exitProcess(0)
        }
    visited[currentVertex] = 2
    stack.add(currentVertex)
}