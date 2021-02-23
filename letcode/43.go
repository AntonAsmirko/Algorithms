func reverse(str string) (res string){
    tmp := make([]byte, len(str))
    for i:=0; i < len(str); i++{
        tmp[len(str) - 1 - i] = str[i]
    }
    res = string(tmp)
    return
}

func multiply(num1 string, num2 string) string {
    if num1 == "0" || num2 == "0"{
        return "0"
    }
    res := make([]int, len(num1) + len(num2) + 2)
    var (
        minN string
        maxN string
    )
    
    if len(num1) > len(num2){
        minN = reverse(num2)
        maxN = reverse(num1)
    } else {
        minN = reverse(num1)
        maxN = reverse(num2)
    }
    
    for i:=0;i < len(minN); i++{
        bloc := make([]int, len(maxN) + 1)
        for j:=0; j < len(maxN); j++{
            bloc[j] += int(minN[i] - '0') * int(maxN[j] - '0')
            if bloc[j] > 9{
                bloc[j+1] += bloc[j]/10
                bloc[j] %= 10
            }
        }
        for j:=0; j < len(maxN) + 1; j++{
            res[i + j] += bloc[j]
            if res[i+j] > 9{
                res[i+j + 1] += res[i + j] /10
                res[i+j] %= 10
            }
        }
    }
    leng := len(res) -1
    for ;res[leng] == 0; leng--{}
    result := make([]byte, leng + 1)
    for i:= 0; i < leng + 1; i++{
        result[i] = byte(res[leng - i]) + '0'
    }
    return string(result)
}