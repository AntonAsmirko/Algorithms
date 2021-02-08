func checkBounds(s string, isPositive bool) bool {
	const (
		dummyPositive string = "2147483647"
		dummyNegative string = "2147483648"
	)
	if len(s) > 10 {
		return false
	} else if len(s) < 10 {
		return true
	} else if isPositive {
		for i := 0; i < len(s); i++ {
			if s[i] < dummyPositive[i] {
				return true
			} else if s[i] > dummyPositive[i] {
				return false
			}
		}
	} else {
		for i := 0; i < len(s); i++ {
			if s[i] < dummyNegative[i] {
				return true
			} else if s[i] > dummyPositive[i] {
				return false
			}
		}
	}
	return true
}

func removeLeadingWhitespaces(s string) string {
	i := 0
	for i < len(s) && s[i] == ' ' {
		i += 1
	}
	if i == len(s) {
		return ""
	}
	return s[i:]
}

func extractDigits(s string) string {
	i := 0
	for i < len(s) && s[i] >= '0' && s[i] <= '9' {
		i += 1
	}
	if i == 0 {
		return ""
	}
	return s[:i]
}

func myAtoi(s string) int {

	const (
		intMax int = 2147483647
		intMin int = -2147483648
	)

	tmpRes := removeLeadingWhitespaces(s)
	if len(tmpRes) == 0 {
		return 0
	}

	if tmpRes[0] != '+' && tmpRes[0] != '-' && (tmpRes[0] > '9' || tmpRes[0] < '0') {
		return 0
	}

	var isPositive bool = tmpRes[0] != '-'
	if tmpRes[0] == '-' || tmpRes[0] == '+' {
		tmpRes = tmpRes[1:]
	}

	i := 0
	for i < len(tmpRes) && tmpRes[i] == '0' {
		i += 1
	}

	if i == len(tmpRes) || i != 0 && (tmpRes[i] < '0' || tmpRes[i] > '9') {
		return 0
	}

	tmpRes = tmpRes[i:]

	if tmpRes[0] != '+' && tmpRes[0] != '-' && (tmpRes[0] > '9' || tmpRes[0] < '0') {
		return 0
	}

	tmpRes = extractDigits(tmpRes)

	if len(tmpRes) == 0 {
		return 0
	}

	if !checkBounds(tmpRes, isPositive) {
		if isPositive {
			return intMax
		} else {
			return intMin
		}
	}

	var result int = int(tmpRes[0] - '0')
	for i := 1; i < len(tmpRes); i++ {
		result *= 10
		result += int(tmpRes[i] - '0')
	}

	if !isPositive {
		result *= -1
	}

	return result
}