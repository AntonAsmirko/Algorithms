import java.util.Scanner
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

fun countingSort(arr: Array<Int>, string: String): Pair<ArrayList<Int>, Array<Int>> {
    val count = Array<MutableList<Int>>(27) { mutableListOf() }
    arr.forEach { count[string[it] - '`'].add(it) }
    val result = arrayListOf<Int>()
    val classEquiv = Array(arr.size) { -1 }
    count.forEach { letter ->
        letter.forEach { it ->
            when {
                result.isEmpty() -> classEquiv[it] = 0
                string[it] == string[result.last()] -> classEquiv[it] = classEquiv[result.last()]
                else -> classEquiv[it] = classEquiv[result.last()] + 1
            }
            result.add(it)
        }
    }
    return Pair(result, classEquiv)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val s = scanner.next()
    val suffixArray =
        buildSuffixArray(s).first.reversed().filterIndexed { index, i -> index < s.length }.reversed().toTypedArray()
    suffixArray.forEach { print("${it} ") }
    println()
    (0 until s.length - 1).map {
        var p = 0
        var i = 0
        while (suffixArray[it] + i < s.length && suffixArray[it + 1] + i < s.length && s[suffixArray[it] + i] == s[suffixArray[it + 1] + i]) {
            p++
            i++
        }
        p
    }.forEach { print("$it ") }
}

fun buildSuffixArray(s: String): Pair<ArrayList<Int>, Array<Int>> {
    var s = s
    s += '`'
    val sLog = log2(s.length.toDouble()).toInt() + 1
    while (s.length < 2.0.pow(sLog).toInt())
        s += '`'
    var suffixes = Array(s.length) { i -> i }
    val p = countingSort(suffixes, s)
    var c = p.second
    val suffixesS = p.first
    for (i in 1 until ceil(log2(s.length.toDouble())).toInt()) {
        suffixesS.sortWith(Comparator { o1, o2 ->
            return@Comparator when {
                c[o1] > c[o2] -> 1
                c[o1] < c[o2] -> -1
                else -> when {
                    c[(o1 + 2.0.pow(i - 1).toInt()) % c.size] > c[(o2 + 2.0.pow(i - 1).toInt()) % c.size] -> 1
                    c[(o1 + 2.0.pow(i - 1).toInt()) % c.size] < c[(o2 + 2.0.pow(i - 1).toInt()) % c.size] -> -1
                    else -> 0
                }
            }
        })
        val newC = Array(suffixesS.size) { -1 }
        var areAllDifferent = true
        suffixesS.forEachIndexed { index, it ->
            when {
                index == 0 -> newC[it] = 0
                c[(it + 2.0.pow(i - 1).toInt()) % c.size] == c[(suffixesS[index - 1] + 2.0.pow(i - 1)
                    .toInt()) % c.size] -> newC[it] = newC[suffixesS[index - 1]]
                else -> {
                    newC[it] = newC[suffixesS[index - 1]] + 1
                    areAllDifferent = false
                }
            }
        }
        c = newC
        if (areAllDifferent) break
    }
    return Pair(suffixesS, c)
}