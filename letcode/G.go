import "math"

func reverse(x int) int {
    const (
        intMax int = ((1<<31) - 1) / 10
        intMin int = (1<<31) / 10
    )
    var (
        result int = 0
        numLen int = 0
        isNegative bool = x < 0
    )
    
    if x == int(-1 * (1<<31)){
        return 0
    }
    
    var ux uint32 = uint32(math.Abs(float64(x)))
    
    for ux > 0{
        rem := int(ux % 10)
        if numLen == 9{
            if result > intMax{
                return 0
            } else if result == intMax{
                if !isNegative {
                    if rem <= 7{
                        return 10 * result + rem
                    } else {
                        return 0
                    }
                } else {
                    if rem <= 8 {
                        return -10 * result - int(rem)
                    } else {
                        return 0
                    }
                }
            }
        }
        result *= 10
        result += int(rem)
        ux /=10
        numLen++
    }
    if isNegative{
        return -1 * result
    } else {
        return result
    }
}