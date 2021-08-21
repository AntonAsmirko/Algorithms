class Solution {
    fun maxScore(cardPoints: IntArray, k: Int): Int {
        var maxScore = 0
        for(i in cardPoints.size - k until cardPoints.size){
            maxScore+=cardPoints[i]
        }
        val results = mutableListOf<Int>(maxScore)
        var l = cardPoints.size - k
        var r = cardPoints.size - 1
        while(r != k - 1){
            var new = results.last() - cardPoints[l]
            l++
            l%=cardPoints.size
            r++
            r%=cardPoints.size
            new+=cardPoints[r]
            maxScore = Math.max(maxScore, new)
            results.add(new)
        }
        return maxScore
    }
}
