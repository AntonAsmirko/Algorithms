import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val scanner = FastReader(FileInputStream("nfc.in"))
    val writer = BufferedWriter(FileWriter(File("nfc.out")))
    val numRules = scanner.nextInt()
    val startSymbol = scanner.next()!!
    val grammar = Grammar(startSymbol)
    for (i in 0 until numRules) {
        val from = scanner.next()
        scanner.next()
        val to = scanner.next()
        grammar.addRule(from!!, to!!)
    }
    writer.write(grammar.accepts(scanner.next()!!).toString())
    writer.close()
}

class Grammar(private val startSymbol: String) {
    private val finalRules = mutableMapOf<String, ArrayList<String>>()
    private val nonFinalRules = mutableMapOf<String, ArrayList<String>>()

    fun addRule(key: String, value: String) {
        if (value == "" || value[0].isLowerCase())
            insertRule(key, value, finalRules)
        else
            insertRule(key, value, nonFinalRules)
    }

    private fun insertRule(
        key: String, value: String,
        rules: MutableMap<String, ArrayList<String>>
    ) {
        if (key in rules) rules[key]!!.add(value)
        else {
            val tmp = ArrayList<String>()
            tmp.add(value)
            rules[key] = tmp
        }
    }

    private fun allIndexes(word: String, letter: Char): MutableList<Int> {
        val res = mutableListOf<Int>()
        for (i in word.indices)
            if (word[i] == letter)
                res.add(i)
        return res
    }

    fun accepts(word: String): Long {
        val mod = 1000000007L
        val n = word.length
        val dp = Array(26) { Array(word.length) { Array(word.length) { 0L } } }

        for (finalRule in finalRules)
            for (term in finalRule.value)
                for (index in allIndexes(word, term[0]))
                    dp[finalRule.key[0] - 'A'][index][index] = 1
        for (i in 1 until n) {
            var j = 0
            while (j + i < n) {
                for (nFR in nonFinalRules)
                    for (leftSide in nFR.value)
                        for (k in j until j + i) {
                            dp[nFR.key[0] - 'A'][j][j + i] =
                                (dp[nFR.key[0] - 'A'][j][j + i] + dp[leftSide[0] - 'A'][j][k] * dp[leftSide[1] - 'A'][k + 1][j + i]) % mod
                        }
                j++
            }
        }
        return dp[startSymbol[0] - 'A'][0][n - 1] % mod
    }
}

private class FastReader internal constructor(input: InputStream?) {
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