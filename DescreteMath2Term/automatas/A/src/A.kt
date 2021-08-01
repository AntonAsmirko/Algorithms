import java.util.*
import java.io.*

lateinit var DFA: Array<TreeMap<Char, MutableList<Int>>>
val passStates = mutableSetOf<Int>()
val scanner = FastReader(FileInputStream(File("problem2.in")))
val word: String? = scanner.next()
val numStates = scanner.nextInt()
val numEges = scanner.nextInt()
val numPassStates = scanner.nextInt()
val bufferedWriter = BufferedWriter(FileWriter("problem2.out"))

fun checkWithInitState(initState: Int, wordIndex: Int): Boolean {
    var isOK = false
    if (word!!.length == wordIndex && initState in passStates)
        return true
    else if (wordIndex == word.length) return false
    if (DFA[initState - 1].containsKey(word[wordIndex]))
        for (j in DFA[initState - 1][word[wordIndex]]!!) {
            isOK = isOK or checkWithInitState(j, wordIndex + 1)
            if (isOK) return isOK
        }
    return isOK
}

fun makeDFA() {
    for (i in 1..numPassStates) {
        passStates.add(scanner.nextInt())
    }
    DFA = Array(numStates) { TreeMap<Char, MutableList<Int>>() }
    for (i in 1..numEges) {
        val state = scanner.nextInt()
        val nextState = scanner.nextInt()
        val charPath = scanner.next()!![0]
        if (DFA[state - 1].containsKey(charPath))
            DFA[state - 1][charPath]!!.add(nextState)
        else DFA[state - 1][charPath] = mutableListOf(nextState)
    }
}

fun main(args: Array<String>) {
    makeDFA()
    if (checkWithInitState(1, 0)) {
        bufferedWriter.write("Accepts")
        bufferedWriter.close()
        return
    }
    bufferedWriter.write("Rejects")
    bufferedWriter.close()
}

class FastReader internal constructor(input: InputStream?) {
    var br: BufferedReader
    var st: StringTokenizer? = null
    operator fun next(): String? {
        while (st == null || !st!!.hasMoreElements()) {
            st = try {
                StringTokenizer(br.readLine())
            } catch (e: Exception) {
                return null
            }
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next()!!.toInt()
    }

    init {
        br = BufferedReader(InputStreamReader(input))
    }
}
