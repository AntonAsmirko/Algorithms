class Solution {
    fun reverseWords(s: String): String {
        val ll = mutableListOf<String>()
        var tmp = ""
        for(ch in s){
            if(ch.isWhitespace() && tmp.length > 0){
                ll.add(tmp)
                tmp = ""
                continue
            } else if (ch.isWhitespace() && tmp.length == 0){
                continue
            }
            tmp += ch
        }
        if(tmp.length > 0)
            ll.add(tmp)
        return ll.reversed().joinToString(separator = " ")
    }
}
