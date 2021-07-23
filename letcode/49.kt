class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val preparedArr = Array(strs.size){ i -> CmpPair(getSorted(strs[i]), strs[i]) }.sortedArray()
        
        val result = LinkedList<MutableList<String>>()
        result.add(mutableListOf(preparedArr[0].b))
        for(i in 1 until preparedArr.size){
            if(preparedArr[i].a == preparedArr[i - 1].a){
                result.last().add(preparedArr[i].b)
            } else {
                result.add(mutableListOf(preparedArr[i].b))
            }
        }
        return result
    }
    
    fun getSorted(str: String): String{
        return str.split("").sorted().joinToString(separator = "")
    }
    
    class CmpPair(val a: String, val b: String): Comparable<CmpPair>{
        override fun compareTo(other: Solution.CmpPair): Int{
            return a.compareTo(other.a)
        }
    }
}