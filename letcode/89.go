func bitToInt(bits []int) (result int){
    for i:= 0; i < len(bits); i++{
        result += bits[i] * (1<<i)
    }
    return
}

func graycode(n int) (result [][]int){
    if n == 1{
        return [][]int{[]int{0}, []int{1}}
    }
    prevCode := graycode(n-1)
    code := make([][]int, len(prevCode) * 2)
    for i:= 0; i < len(prevCode); i++{
        code[i] = make([]int, n)
        for j:= n - 2; j >=0; j--{
            code[i][j+1] = prevCode[i][j]
        }
        code[i][0] = 0
    }
    for i, u := len(prevCode) - 1,  len(prevCode); i >=0; i, u = i - 1, u + 1{
        code[u] = make([]int, n)
        for j:= n - 2; j >=0; j--{
            code[u][j+1] = prevCode[i][j]
        }
        code[u][0] = 1
    }
    return code
}

func grayCode(n int) (result []int) {
    result = make([]int, 0)
    for _, i := range graycode(n) {
        result = append(result, bitToInt(i))
    }
    return
}