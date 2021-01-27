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
    var k = prefixFun[str.length]
    var period = str.length - k
    var firstPart: String? = null
    var secondPart: String? = null
    try {
        firstPart = str.substring(0, period)
        secondPart = str.substring(period, 2 * period)
    } catch (e: Exception) {}

    if (firstPart == null ||
            secondPart == null ||
            str.substring(0, period) != str.substring(period, 2 * period))
            println(str.length)
    else println(str.length - k)
}
