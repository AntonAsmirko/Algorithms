class Solution {
    fun removeDuplicates(s: String, k: Int): String {
        val ll = LinkedList<MaPair>();
        for(i in s){
            if(ll.isEmpty()){
                ll.add(MaPair(i, 1))
            } else {
                ll.add(MaPair(i, if(ll.last().f == i){ll.last().count+1} else {1}))
                if(ll.last().count == k){
                    while(ll.size > 0 && ll.last().f == i){
                        ll.removeLast()
                    }
                }
            }
        }
        return ll.map{it.f}.joinToString(separator = "")
    }
    
    class MaPair(val f: Char, var count: Int)
}
