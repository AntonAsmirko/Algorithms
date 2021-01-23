import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

fun countingSort(
    arr: List<Int>,
    string: String,
    getCount: (a: List<Int>, s: String) -> Array<MutableList<Int>>
): Pair<ArrayList<Int>, Array<Int>> {
    val count = getCount(arr, string)
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
    val scanner = FastReader(System.`in`)
    val s = scanner.next().toString()
    val suffixArray =
        buildSuffixArray(s).first.reversed().filterIndexed { index, i -> index < s.length }.reversed()
            .toTypedArray()
    suffixArray.forEach { print("${it + 1} ") }
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
    val suffixes = s.indices.toList()
    val p = countingSort(suffixes, s) { a, str ->
        val count = Array<MutableList<Int>>(27) { mutableListOf() }
        a.forEach { count[str[it] - '`'].add(it) }
        count
    }
    var c = p.second
    var suffixesS = p.first
    for (i in 1..ceil(log2(s.length.toDouble())).toInt()) {
        suffixesS = digitalSort(suffixesS, c, i)
        val newC = Array(suffixesS.size) { -1 }
        var areAllDifferent = true
        suffixesS.forEachIndexed { index, it ->
            when {
                index == 0 -> newC[it] = 0
                c[it % c.size] == c[suffixesS[index - 1] % c.size] && c[(it + 2.0.pow(i - 1)
                    .toInt()) % c.size] == c[(suffixesS[index - 1] + 2.0.pow(i - 1)
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

fun digitalSort(p: ArrayList<Int>, c: Array<Int>, k: Int): ArrayList<Int> {
    var p = p
    for (i in listOf(2.0.pow(k - 1).toInt(), 0)) {
        val count = Array(p.size) { mutableListOf<Int>() }
        p.forEach {
            count[c[(it + i) % c.size]].add(it)
        }
        val result = arrayListOf<Int>()
        count.forEach { letter -> letter.forEach { result.add(it) } }
        p = result
    }
    return p
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

    fun nextLong(): Long {
        return next()!!.toLong()
    }

    init {
        br = BufferedReader(InputStreamReader(input))
    }
}