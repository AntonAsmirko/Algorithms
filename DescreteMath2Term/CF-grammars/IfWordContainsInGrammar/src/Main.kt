import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val scanner = FastReader(FileInputStream("automaton.in"))
    val writer = BufferedWriter(FileWriter(File("automaton.out")))

    val countProducts = scanner.nextInt()
    val startSymbol = scanner.next()!!

    val grammar = Grammar(startSymbol)

    for (i in 0 until countProducts) {
        val origin = scanner.next()!![0]
        scanner.next()
        val dest = scanner.next()
        grammar.insertRule(origin, dest!!)
    }

    for (i in 0 until scanner.nextInt()) {
        writer.write(if (grammar.accepts(scanner.next()!!)) "yes\n" else "no\n")
    }
    writer.close()
}


class Grammar(private val start: String) {
    private val rules = mutableMapOf<Char, ArrayList<String>>();

    fun insertRule(
        key: Char, value: String
    ) {
        if (key in rules) rules[key]!!.add(value)
        else {
            val tmp = ArrayList<String>()
            tmp.add(value)
            rules[key] = tmp
        }
    }

    private fun accepts(word: String, len: Int, term: Char): Boolean {
        if (term.isLowerCase() && word.length == len)
            return true
        if(word.length == len)
            return false
        if (term in rules) {
            var appropriate = false
            for (i in rules[term]!!)
                if (i.length == 2) {
                    if (i[0] != word[len]) continue
                    appropriate = appropriate || accepts(word, len + 1, i[1])
                } else
                    appropriate = appropriate || accepts(word, len + 1, i[0])
            return appropriate
        }
        return false
    }

    fun accepts(word: String): Boolean {
        return try {
            accepts(word, 0, start[0])
        } catch (e: java.lang.Exception) {
            false
        }
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
