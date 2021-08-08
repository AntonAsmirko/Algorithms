class Solution {
    fun removeKdigits(num: String, k: Int): String {
        var k = k
        val numsSpl = num.split("").filter{it.length == 1}.map{it[0] - '0'}
        val stack = LinkedList<Int>()
        for(item in numsSpl){
            stack.add(item)
            loop@while(stack.size >= 2 && k > 0){
                val f = stack.removeLast()
                val s = stack.removeLast()
                if(s > f && k > 0){
                    stack.add(f)
                    k--
                } else {
                    stack.add(s)
                    stack.add(f)
                    break@loop   
                }
            }
        }
        while(k > 0){
            stack.removeLast()
            k--
        }
        while(stack.size > 0 && stack[0] == 0){
            stack.removeFirst()
        }
        val tmp = stack.joinToString(separator = "")
        return if(tmp.length == 0) tmp + "0" else tmp
    }
}
