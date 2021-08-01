import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

val scanner = Scanner(FileInputStream("minimization.in"))
val bufferedReader = BufferedWriter(FileWriter("minimization.out"))
val numStates = scanner.nextInt()
val numEdges = scanner.nextInt()
val numTerminalStates = scanner.nextInt()

fun getDFA(): Pair<Array<Array<Int>>, TreeSet<Int>> {
    val dfa = Pair(Array(numStates + 1) { Array(26) { 0 } }, TreeSet<Int>())
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

lateinit var used: Array<Boolean>
lateinit var dfa: Pair<Array<Array<Int>>, TreeSet<Int>>

fun dfs(vertex: Int) {
    used[vertex] = true
    for (i in dfa.first[vertex].indices)
        if (dfa.first[vertex][i] != 0 && !used[dfa.first[vertex][i]])
            dfs(dfa.first[vertex][i])
}

fun removeStates(states: TreeMap<Int, Array<Int>>): TreeMap<Int, Array<Int>>{
    val toRemove = mutableSetOf<Int>()
    for (i in states) {
        for (j in states) {
            if (!toRemove.contains(i.key) && i.key != j.key && i.value.contentEquals(j.value)) {
                for (k in states) {
                    for (u in k.value.indices) {
                        if (k.value[u] == j.key)
                            k.value[u] = i.key
                    }
                }
                toRemove.add(j.key)
            }
        }
    }
    for (i in toRemove)
        states.remove(i)
    return states
}

fun minimize()
        : Pair<Array<Array<Int>>, MutableSet<Int>> {
    used = Array(dfa.first.size) { false }
    dfs(1)
    val nonTerm = TreeMap<Int, Array<Int>>()
    val term = TreeMap<Int, Array<Int>>()
    for (i in used.indices)
        if (used[i] && i != 0)
            if (i in dfa.second) term[i] = dfa.first[i]
            else nonTerm[i] = dfa.first[i]
    val nonTermProcessed = removeStates(nonTerm)
    val termProcessed = removeStates(term)
    var lastFree = 2
    val associations = TreeMap<Int, Int>()
    val dfaResult = Array(termProcessed.size + nonTermProcessed.size + 1) { Array(26) { 0 } }
    associations[1] = 1
    for (i in nonTerm) {
        if (i.key != 1)
            associations[i.key] = lastFree++
        dfaResult[associations[i.key]!!] = i.value
    }
    for (i in term) {
        if (i.key != 1)
            associations[i.key] = lastFree++
        dfaResult[associations[i.key]!!] = i.value
    }
    for (i in dfaResult)
        i.forEach { item -> associations[item] }
    return Pair(dfaResult, term.keys)
}

fun printDfa(dfa: Pair<Array<Array<Int>>, MutableSet<Int>>) {
    var numEdges = 0
    for (i in dfa.first) i.forEach { item -> if (item != 0) numEdges++ }
    bufferedReader.write("${dfa.first.size - 1} $numEdges ${dfa.second.size}\n")
    dfa.second.toList().sorted().forEach { item -> bufferedReader.write("$item ") }
    bufferedReader.write("\n")
    for (i in 1 until dfa.first.size)
        for (j in dfa.first[i].indices)
            if (dfa.first[i][j] != 0)
                bufferedReader.write("$i ${dfa.first[i][j]} ${'a' + j}\n")
    bufferedReader.close()
}

fun main(args: Array<String>) {
    dfa = getDFA()
    printDfa(minimize())
}