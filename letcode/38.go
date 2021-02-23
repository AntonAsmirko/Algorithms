func cas(n int) string{
    if n == 1 {
        return "1"
    } else {
        casp := cas(n-1)
        digitCount := make(map[int]int)
        order := []int{0}
        digitCount[0] = 1
        for i:=1; i < len(casp); i++{
            if casp[i-1] != casp[i]{
                order = append(order, i)
                digitCount[i] = 1
            } else {
                digitCount[order[len(order) - 1]] = digitCount[order[len(order) - 1]] + 1
            }
        }
        result := make([]byte, 0)
        for _, item := range order{
            result = append(result, '0' + byte(digitCount[item]))
            result = append(result, casp[item])
        }
        return string(result)
    }
}

func countAndSay(n int) string {
    return cas(n)
}