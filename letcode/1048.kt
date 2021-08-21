class Solution {
        
    fun longestStrChain(words: Array<String>): Int {
        val dp = HashMap<String, Int>()
        var max = 1
        for(i in words.indices){
            max = Math.max(bruteForce(i, words, BooleanArray(words.size).apply{this[i] = true}, 1, dp), max)
        }
        return max
    }
    
    
    fun bruteForce(i: Int, words: Array<String>, visited: BooleanArray, count: Int, dp: HashMap<String, Int>): Int{
        if(visited.all{it == true}){
            dp.merge(words[i], count){f,s -> Math.max(f, s)}
            return count
        }
        if(dp.contains(words[i])){
            return count + dp[words[i]]!! - 1
        }
        var max = count
        for(index in words.indices){
            if(!visited[index] && isPredecessor(words[index], words[i])){
                visited[index] = true
                max = Math.max(bruteForce(index, words, visited, count + 1, dp), max)
                visited[index] = false
            }
        }
        dp.merge(words[i], max - count + 1){f, s -> Math.max(f,s)}
        return max
    }
    
    fun isPredecessor(a: String, b: String): Boolean{
        if(b.length - a.length >= 2){
            return false
        }
        if(a.length >= b.length){
            return false
        }
        var diffCount = 0
        var i = 0
        var j = 0
        while(i < a.length){
            if(a[i] != b[j]){
                diffCount++
                if(diffCount >= 2){
                    return false
                }
                i--
            }
            i++
            j++
        }
        return true
    }
}
