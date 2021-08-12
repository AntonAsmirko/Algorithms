class Solution {
    
    var handled = IntArray(10001)
    var squares = IntArray(10001)
    
    
    fun numSquares(n: Int): Int {
        squares = IntArray(10001)
        handled = IntArray(10001).apply{
            for(i in 0 until 10001){
                this[i] = -1
            }
            this[0] = 1
            this[1] = 1
        }
        return split(n)
    }
    
    fun split(n: Int): Int{
        var min = Int.MAX_VALUE
        if(isPerfectSquare(n)){
            return 1
        }
        if(n == 0){
            return 1
        }
        if(n == 1){
            return 1
        }
        for(i in 1 until n){
            val f = i
            val s = n - i
            min = Math.min(min, if(isPerfectSquare(f)){1} else {ifHandled(f) ?: split(f)} + if(isPerfectSquare(s)){1} else {ifHandled(s) ?: split(s)})
        }
        handled[n] = min
        return min
    }
    
    fun isPerfectSquare(n: Int): Boolean{
        if(squares[n] == 1){
            return true
        } else if (squares[n] == -1){
            return false
        }
        val check = Math.sqrt(n.toDouble()).toInt()
        if(check * check == n){
            squares[n] = 1
        } else {
            squares[n] = -1
        }
        return check * check == n
    }
    
    fun ifHandled(n: Int): Int?{
        if(handled[n] == -1) return null
        return handled[n]
    }
}
