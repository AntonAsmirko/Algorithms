import java.lang.StringBuilder
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val str = scanner.next()
    val prefixFun = Array(str.length + 1) { 0 }
    prefixFun[0] = -1
    for (i in 1..str.length) {
        var k = prefixFun[i - 1]
        while (k >= 0) {
            if (str[k] == str[i - 1]) break
            k = prefixFun[k]
        }
        prefixFun[i] = k + 1
    }
    var res = StringBuilder()
    prefixFun.forEachIndexed { index, i ->
        if (index != 0) {
            res.append("$i ")
        }
    }
    println(res.toString())
}
