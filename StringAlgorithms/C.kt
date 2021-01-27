import java.lang.Math.max
import java.lang.Math.min
import java.lang.StringBuilder
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val str = scanner.next()
    val zFun = Array(str.length) { 0 }
    var l = 0
    var r = 0
    for (i in 1 until str.length) {
        zFun[i] = kotlin.math.max(0, min(r - i, zFun[i - l]))
        while (i + zFun[i] < str.length && str[zFun[i]] == str[i + zFun[i]]) {
            zFun[i]++
            if (i + zFun[i] > r) {
                l = i
                r = i + zFun[i]
            }
        }
    }
    val sb = StringBuilder()
    zFun.forEachIndexed { index, i ->
        if (index != 0) {
            sb.append("$i ")
        }
    }
    println(sb.toString())
}
