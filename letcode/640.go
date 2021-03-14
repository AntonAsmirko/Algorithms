import "math"

func isNum(ch byte) bool {
	return ch >= '0' && ch <= '9'
}

func intToByteArray(num int) (res []byte) {
	res = make([]byte, 0)
	if num == 0 {
		res = append(res, '0')
		return
	}
	for num != 0 {
		rem := num % 10
		num /= 10
		res = append(res, byte(rem+'0'))
	}
	for i, j := 0, len(res)-1; i < j; i, j = i+1, j-1 {
		res[i], res[j] = res[j], res[i]
	}
	return
}

func eatNumber(start int, equation string) (res, i int) {
	queue := make([]int, 0)
	for i = start; i < len(equation) && isNum(equation[i]); i++ {
		queue = append(queue, int(equation[i]-'0'))
	}
	for j := 0; j < len(queue); j++ {
		res *= 10
		res += queue[j]
	}
	return
}

func solveEquation(equation string) string {
	xRes, constantsRes := 0, 0
	wasEquator := false
	for i := 0; i < len(equation); i++ {
		var resNum int
		if equation[i] == '=' {
			wasEquator = true
		} else if equation[i] == '-' || equation[i] == '+' {
			if equation[i] == '-' {
				resNum = -1
			} else {
				resNum = 1
			}
			if isNum(equation[i+1]) {
				res, pos := eatNumber(i+1, equation)
				resNum *= res
				i = pos
				if pos < len(equation) && equation[pos] == 'x' {
					if wasEquator {
						xRes += -1 * resNum
					} else {
						xRes += resNum
					}
				} else {
					if wasEquator {
						constantsRes += resNum
					} else {
						constantsRes += -1 * resNum
					}
					i--
				}
			} else {
				if wasEquator {
					xRes += -1 * resNum
				} else {
					xRes += resNum
				}
				i++
			}
		} else if equation[i] == 'x' {
			if wasEquator {
				xRes -= 1
			} else {
				xRes += 1
			}
		} else if isNum(equation[i]) {
			res, pos := eatNumber(i, equation)
			i = pos
			if pos < len(equation) && equation[pos] == 'x' {
				if wasEquator {
					xRes += -1 * res
				} else {
					xRes += res
				}
			} else {
				if wasEquator {
					constantsRes += res
				} else {
					constantsRes += -1 * res
				}
				i--
			}
		}
	}
	if xRes == 0 && constantsRes != 0 {
		return "No solution"
	} else if xRes == 0 && constantsRes == 0 {
		return "Infinite solutions"
	} else {
		isNegative := true
		if constantsRes/xRes >= 0 {
			isNegative = false
		}
		result := int(math.Abs(float64(constantsRes / xRes)))
		firstPart := []byte{'x', '='}
		if isNegative {
			firstPart = append(firstPart, '-')
		}
		resBytes := intToByteArray(result)
		return string(append(firstPart, resBytes...))
	}
}