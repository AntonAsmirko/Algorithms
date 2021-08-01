import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val scanner = Scanner(FileInputStream("useless.in"))
    val writer = BufferedWriter(FileWriter(File("useless.out")))
    val numRules = scanner.nextInt()
    val startSymbol = scanner.next()
    val grammar = Grammar(startSymbol)
    scanner.nextLine()
    for (i in 0 until numRules) {
        val strSplit = scanner.nextLine().split(" ").filter { i -> i != "" }
        val from = strSplit[0]
        val to = if (strSplit.size == 3) strSplit[2] else "_"
        grammar.addRule(from, to)
    }
    for (uselessNonTerminal in grammar.getAllUselessNonTerminals())
        writer.write("$uselessNonTerminal ")
    writer.close()
}

class Grammar(private val startSymbol: String) {
    private val rules = mutableMapOf<String, MutableSet<String>>()
    private val meaningfulNonTerminals = mutableSetOf<Char>()
    private val allNonTerminals = mutableSetOf<Char>()
    private val visited = mutableSetOf<Char>()
    fun addRule(key: String, value: String) {
        var isTerminal = true
        value.forEach { i -> if (i.isUpperCase()) isTerminal = false }
        if (isTerminal)
            meaningfulNonTerminals.add(key[0])
        allNonTerminals.add(key[0])
        value.forEach { i -> if (i.isUpperCase()) allNonTerminals.add(i) }
        insertRule(key, value, rules)
    }

    private fun dfs(nonTerm: Char) {
        if (nonTerm.toString() in rules)
            for (term in rules[nonTerm.toString()]!!)
                for (ch in term)
                    if (ch.isUpperCase() && ch !in visited) {
                        visited.add(ch)
                        dfs(ch)
                    }
    }

    private fun insertRule(
        key: String, value: String,
        rules: MutableMap<String, MutableSet<String>>
    ) {
        if (key in rules) rules[key]!!.add(value)
        else {
            val tmp = mutableSetOf<String>()
            tmp.add(value)
            rules[key] = tmp
        }
    }

    private fun removeNotMeaningful(notMeaningful: TreeSet<Char>): TreeSet<Char> {
        for (nm in notMeaningful) {
            rules.remove(nm.toString())
        }
        for (survived in rules) {
            val toRemove = mutableSetOf<String>()
            for (right in survived.value)
                for (nm in notMeaningful)
                    if (right.indexOf(nm) >= 0)
                        toRemove.add(right)
            for (rm in toRemove)
                survived.value.remove(rm)
        }
        val emptyRules = mutableListOf<String>()
        if (startSymbol !in rules)
            emptyRules.add(startSymbol)
        for (rule in rules)
            if (rule.value.size == 0)
                emptyRules.add(rule.key)
        for (emptyRule in emptyRules) {
            notMeaningful.add(emptyRule[0])
            rules.remove(emptyRule)
        }
        return notMeaningful
    }

    fun getAllUselessNonTerminals(): TreeSet<Char> {
        do {
            var meaningfulSetSize = meaningfulNonTerminals.size
            for (rule in rules) {
                var willAdd = false
                loop@ for (term in rule.value) {
                    var isOk = true
                    for (ch in term) {
                        if (ch.isUpperCase() && ch !in meaningfulNonTerminals) {
                            isOk = false
                        }
                    }
                    if (isOk) {
                        willAdd = true
                        break@loop
                    }
                }
                if (willAdd) {
                    meaningfulNonTerminals.add(rule.key[0])
                }
            }
        } while (meaningfulSetSize != meaningfulNonTerminals.size)
        val diff = TreeSet<Char>()
        for (nonTerm in allNonTerminals)
            if (nonTerm !in meaningfulNonTerminals)
                diff.add(nonTerm)
        visited.add(startSymbol[0])
        val diff2 = removeNotMeaningful(diff)
        dfs(startSymbol[0])
        for (item in allNonTerminals)
            if (item !in visited)
                diff2.add(item)
        return diff2
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