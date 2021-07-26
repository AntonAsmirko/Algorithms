class Solution {
    func mySqrt(_ x: Int) -> Int {
        var lastSquare = 0
        for i in 0...x {
            if(i * i > x){
                break
            }
            lastSquare = i
        }
        return lastSquare
    }
}
