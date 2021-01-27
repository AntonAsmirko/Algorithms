import java.lang.StringBuilder
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val s = scanner.next()
    val t = scanner.next()
    val str = "$s$$t"
    val prefixFun = Array(str.length + 1) { 0 }
    prefixFun[0] = -1
    var count = 0
    for (i in 1..str.length) {
        var k = prefixFun[i - 1]
        while (k >= 0) {
            if (str[k] == str[i - 1]) break
            k = prefixFun[k]
        }
        prefixFun[i] = k + 1
        if (prefixFun[i] == s.length) count++
    }
    var res = StringBuilder()
    println(count)
    prefixFun.forEachIndexed { index, i ->
        if (index != 0 && i == s.length) {
            res.append("${index - s.length * 2} ")
        }
    }
    println(res.toString())
}
