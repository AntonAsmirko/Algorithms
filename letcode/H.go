import "math"

func isPalindrome(x int) bool {
    if x < 0{
        return false
    }
    
    var ux int64 = int64(math.Abs(float64(x)))
    
    var lenX int = 0
    for uxClone := ux; uxClone != 0; uxClone /= 10{
        lenX++
    }
    
    var byteArr []byte = make([]byte, lenX)
    
    for i:= 0; i < lenX; i++{
        byteArr[lenX - i - 1] = '0' + byte(ux % 10)
        ux /= 10
    }
    for i, j := 0, lenX -1; i < j; i, j = i + 1, j - 1{
        if byteArr[i] != byteArr[j]{
            return false
        }
    }
    return true
}