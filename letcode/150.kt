class Solution {
    fun evalRPN(tokens: Array<String>): Int {
        if(tokens.size == 1){
            return tokens[0].toInt()
        }
        val symbols = setOf("+", "-", "/", "*")
        val ll = LinkedList<String>()
        for(i in tokens){
            if(i in symbols){
                val s = ll.removeLast().toInt()
                val f = ll.removeLast().toInt()
                ll.add(
                    when(i){
                        "*" -> (f * s).toString()
                        "+" -> (f + s).toString()
                        "-" -> (f - s).toString()
                        else -> (f / s).toString()
                    }
                )
            } else {
                ll.add(i)
            }
        }
        return ll.removeFirst().toInt()
    }
}
