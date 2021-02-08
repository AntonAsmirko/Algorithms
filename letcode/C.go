func convert(s string, numRows int) string {
	if len(s) <= 1 || numRows <= 1 {
		return s
	}
	var container [][]byte = make([][]byte, numRows)

	var numColumns int

	if (2*numRows - 2) == 0 {
		numColumns = len(s)
	} else {
		numColumns = (numRows - 1) * (len(s)/(2*numRows-2) + 1)
	}
	for i := 0; i < numRows; i++ {
		container[i] = make([]byte, numColumns)
	}

	var (
		placeInRow int  = 0
		curColumn  int  = 0
		lock       bool = false
	)

	for i := 0; i < len(s); i++ {
		if placeInRow < numRows && !lock {
			container[placeInRow][curColumn] = s[i]
			placeInRow += 1
		} else {
			if !lock {
				placeInRow -= 1
			}
			placeInRow -= 1
			curColumn += 1
			lock = true
			container[placeInRow][curColumn] = s[i]
			if placeInRow == 0 {
				placeInRow += 1
				lock = false
			}
		}
	}

	result := make([]byte, len(s))
	lastPosRes := 0

	for i := 0; i < numRows; i++ {
		for j := 0; j < numColumns; j++ {
			if container[i][j] != 0 {
				result[lastPosRes] = container[i][j]
				lastPosRes += 1
			}
		}
	}

	return string(result)
}