import java.util.Scanner

val scanner = Scanner(System.`in`)
val n = scanner.nextInt()
val adjMatrix = Array(n) { Array(n) { scanner.nextInt() } }

fun main() {
    val solution = Array(n) { i: Int -> adjMatrix[i].clone() }
    for (k in 0 until n)
        for (i in 0 until n)
            for (j in 0 until n)
                if (solution[i][k] + solution[k][j] < solution[i][j])
                    solution[i][j] = solution[i][k] + solution[k][j]
    solution.forEach { it.forEach { print("$it ") }; print("\n") }
}