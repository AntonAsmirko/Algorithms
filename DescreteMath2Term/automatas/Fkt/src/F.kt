import java.io.*
import java.util.*
import kotlin.collections.HashSet

val scanner = Scanner(FileInputStream(File("equivalence.in")))
val bufferedReader = BufferedWriter(FileWriter("equivalence.out"))

fun getDFA(): Pair<Array<TreeMap<Int, Int>>, HashSet<Int>> {
    val numStates = scanner.nextInt()
    val numEdges = scanner.nextInt()
    val numTerminalStates = scanner.nextInt()
    val dfa = Pair(Array(numStates + 1) { TreeMap<Int, Int>() }, HashSet<Int>())
    for (i in 1..numTerminalStates)
        dfa.second.add(scanner.nextInt())
    for (i in 1..numEdges) {
        val init = scanner.nextInt()
        val dest = scanner.nextInt()
        val thunk = scanner.next()[0] - 'a'
        dfa.first[init][thunk] = dest
    }
    return dfa
}

fun main(args: Array<String>) {
    val dfa = getDFA()
    bufferedReader.close()
}