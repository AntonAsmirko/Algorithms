class Solution {
    fun reformat(s: String): String {
        val letters = hashMapOf<Char, Int>()
        val nums = hashMapOf<Char, Int>()
        s.forEach{
            if(it.isLetter()){
                letters.merge(it, 1){f, s -> f + s}
            } else {
                nums.merge(it, 1){f, s -> f + s}
            }
        }
        val ll = LinkedList<MaPair>().apply{addAll(letters.map{MaPair(it.key, it.value)})}
        val ln = LinkedList<MaPair>().apply{addAll(nums.map{MaPair(it.key, it.value)})}
        val countLetter = s.count{it.isLetter()}
        val countNums = s.count{it.isDigit()}
        val res = mutableListOf<Char>()
        if(countLetter > countNums){
            res.add(ll.last().f)
            ll.last().s--
            if(ll.last().s == 0){
                ll.removeLast()
            }
        } else {
            res.add(ln.last().f)
            ln.last().s--
            if(ln.last().s == 0){
                ln.removeLast()
            }
        }
        while(ll.isNotEmpty() && ln.isNotEmpty()){
            if(res.last().isDigit()){
                res.add(ll.last().f)
                ll.last().s--
                if(ll.last().s == 0){
                    ll.removeLast()
                }
            } else {
                res.add(ln.last().f)
                ln.last().s--
                if(ln.last().s == 0){
                    ln.removeLast()
                }
            }
        }
        if(ll.size > 1 || ln.size > 1 || ll.size == 1 && res.last().isLetter() || ln.size == 1 && res.last().isDigit()){
            return ""
        }
        if(ll.isNotEmpty()){
            res.add(ll.last().f)
        }
        if(ln.isNotEmpty()){
            res.add(ln.last().f)
        }
        return res.joinToString(separator = "")
    }
}

class MaPair(var f: Char, var s: Int)
