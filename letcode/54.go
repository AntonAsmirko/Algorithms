func spiralOrder(matrix [][]int) (result []int) {
    result = make([]int, 0)
    passed := 0
    iter := 0
    count := len(matrix) * len(matrix[0])
    for passed < count{
        i, j := iter, iter
        for ;j < len(matrix[0]) - iter; j++{
            result = append(result, matrix[i][j])
            passed++
        }
        if passed == count{
            break
        }
        j--
        i++
        for ;i < len(matrix) - iter; i++{
            result = append(result, matrix[i][j])
            passed++
        }
        if passed == count{
            break
        }
        i--
        j--
        for ;j >= iter; j--{
            result = append(result, matrix[i][j])
            passed++
        }
        if passed == count{
            break
        }
        j++
        i--
        for ;i > iter; i--{
            result = append(result, matrix[i][j])
            passed++
        }
        if passed == count{
            break
        }
        iter++
    }
    return
}