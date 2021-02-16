var result []string

func makeAll(rem []string, str string, mapping [][]string) {
	for i := 0; i < len(mapping[rem[0][0]-'0']); i++ {
		if len(rem) > 1 {
			makeAll(rem[1:], str+mapping[rem[0][0]-'0'][i], mapping)
		} else {
			result = append(result, str+mapping[rem[0][0]-'0'][i])
		}
	}
}

func letterCombinations(digits string) []string {
	result = make([]string, 0)
	if len(digits) == 0 {
		return result
	}
	mapping := make([][]string, 10)
	mapping[2] = make([]string, 3)
	mapping[2][0] = "a"
	mapping[2][1] = "b"
	mapping[2][2] = "c"
	mapping[3] = make([]string, 3)
	mapping[3][0] = "d"
	mapping[3][1] = "e"
	mapping[3][2] = "f"
	mapping[4] = make([]string, 3)
	mapping[4][0] = "g"
	mapping[4][1] = "h"
	mapping[4][2] = "i"
	mapping[5] = make([]string, 3)
	mapping[5][0] = "j"
	mapping[5][1] = "k"
	mapping[5][2] = "l"
	mapping[6] = make([]string, 3)
	mapping[6][0] = "m"
	mapping[6][1] = "n"
	mapping[6][2] = "o"
	mapping[7] = make([]string, 4)
	mapping[7][0] = "p"
	mapping[7][1] = "q"
	mapping[7][2] = "r"
	mapping[7][3] = "s"
	mapping[8] = make([]string, 3)
	mapping[8][0] = "t"
	mapping[8][1] = "u"
	mapping[8][2] = "v"
	mapping[9] = make([]string, 4)
	mapping[9][0] = "w"
	mapping[9][1] = "x"
	mapping[9][2] = "y"
	mapping[9][3] = "z"
	strr := make([]string, len(digits))
	for i := 0; i < len(digits); i++ {
		strr[i] = string(digits[i])
	}
	makeAll(strr, "", mapping)
	return result
}