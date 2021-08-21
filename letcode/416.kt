class Solution {
    fun addStrings(num1: String, num2: String): String {
        var (l, s) = if (num1.length >= num2.length) {
            Pair(num1.toCharArray(), num2.toCharArray())
        } else {
            Pair(num2.toCharArray(), num1.toCharArray())
        }
        var rem = false
        var li = l.size - 1
        var si = s.size - 1
        while (si >= 0) {
            var (ch, b) = addTwoChars(l[li], s[si])
            if (rem) {
                val tmp = addTwoChars(ch, '1')
                b = b || tmp.second
                ch = tmp.first
            }
            l[li] = ch
            rem = b
            si--
            li--
        }
        while (li >= 0 && rem) {
            var (ch, b) = addTwoChars(l[li], '1')
            l[li] = ch
            rem = b
            li--
        }
        var res = l.joinToString(separator = "")
        if (rem) {
            res = "1$res"
        }
        return res
    }

    private fun addTwoChars(ch1: Char, ch2: Char): Pair<Char, Boolean> {
        val intRes = (ch1 - '0') + (ch2 - '0')
        return Pair((intRes % 10).toChar() + '0'.toInt(), intRes >= 10)
    }
}
