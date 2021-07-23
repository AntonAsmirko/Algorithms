class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        return load(s).equals(load(t))
    }
    
    fun load(s: String): HashMap<Char, Int>{
        val map = HashMap<Char, Int>()
        for(i in s){
            if(map.containsKey(i)){
                map[i] = map[i]!! + 1
            } else {
                map[i] = 1
            }
        }
        return map
    }
}
