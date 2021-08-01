import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(FileReader(File("epsilon.in")))
    val writer = BufferedWriter(FileWriter(File("epsilon.out")))

    var tmpInputString = scanner.nextLine()
    val trueTmp = tmpInputString.split(" ")
    val numRules = trueTmp[0].toInt()
    val startNonTerminal = trueTmp[1]
    val grammar = Grammar()
    for (i in 0 until numRules) {
        tmpInputString = scanner.nextLine()
        val splited = tmpInputString.split(" ")
        if (splited.size == 2) {
            grammar.addRule(splited[0][0], "")
        } else if (splited.size == 3) {
            grammar.addRule(splited[0][0], splited[2])
        }
    }
    for (i in grammar.epsProducers())
        writer.write("$i ")
    writer.close()
}

class Grammar() {
    private val rules = mutableListOf<Pair<Char, String>>()

    fun addRule(nonTerminal: Char, product: String) {
        rules.add(Pair(nonTerminal, product))
    }

    private fun isUpperCaseString(str: String): Boolean {
        var wasLowerCase = false
        str.forEach { i -> if (i.isLowerCase()) wasLowerCase = true }
        return !wasLowerCase
    }

    private fun areAllCharsInSet(str: String, set: MutableSet<Char>): Boolean {
        var initCond = true
        str.forEach { i -> if (!set.contains(i)) initCond = false }
        return initCond
    }

    fun epsProducers(): MutableSet<Char> {
        val epsProducers = TreeSet<Char>()
        do {
            val epsProducersCount = epsProducers.size
            for (rule in rules) {
                if (rule.second == ""
                    || isUpperCaseString(rule.second)
                    && areAllCharsInSet(rule.second, epsProducers)
                ) {
                    epsProducers.add(rule.first)
                }
            }
        } while (epsProducers.size != epsProducersCount)
        return epsProducers
    }
    
}