import "strings"

func intToRoman(num int) string {
	var result []string = make([]string, 0)
	for i := 0; num != 0; i++ {
		switch rem := num % 10; rem {
		case 0:
		case 1:
			switch i {
			case 0:
				result = append(result, "I")
			case 1:
				result = append(result, "X")
			case 2:
				result = append(result, "C")
			case 3:
				result = append(result, "M")
			}
		case 2:
			switch i {
			case 0:
				result = append(result, "II")
			case 1:
				result = append(result, "XX")
			case 2:
				result = append(result, "CC")
			case 3:
				result = append(result, "MM")
			}
		case 3:
			switch i {
			case 0:
				result = append(result, "III")
			case 1:
				result = append(result, "XXX")
			case 2:
				result = append(result, "CCC")
			case 3:
				result = append(result, "MMM")
			}
		case 4:
			switch i {
			case 0:
				result = append(result, "IV")
			case 1:
				result = append(result, "XL")
			case 2:
				result = append(result, "CD")
			}
		case 5:
			switch i {
			case 0:
				result = append(result, "V")
			case 1:
				result = append(result, "L")
			case 2:
				result = append(result, "D")
			}
		case 6:
			switch i {
			case 0:
				result = append(result, "VI")
			case 1:
				result = append(result, "LX")
			case 2:
				result = append(result, "DC")
			}
		case 7:
			switch i {
			case 0:
				result = append(result, "VII")
			case 1:
				result = append(result, "LXX")
			case 2:
				result = append(result, "DCC")
			}
		case 8:
			switch i {
			case 0:
				result = append(result, "VIII")
			case 1:
				result = append(result, "LXXX")
			case 2:
				result = append(result, "DCCC")
			}
		default:
			switch i {
			case 0:
				result = append(result, "IX")
			case 1:
				result = append(result, "XC")
			case 2:
				result = append(result, "CM")
			}
		}
		num /= 10
	}
	var sb strings.Builder
	for i := len(result) - 1; i >= 0; i-- {
		sb.WriteString(result[i])
	}
	return sb.String()
}