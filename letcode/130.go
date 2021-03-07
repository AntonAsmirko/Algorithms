type Pair struct {
	F, S byte
}

type Queue struct {
	Data             []Pair
	Start, End, Size int
}

func makeQueue(size int) (queue *Queue) {
	queue = new(Queue)
	queue.Data = make([]Pair, size)
	queue.Start, queue.End = 0, 0
	queue.Size = 0
	return
}

func (queue *Queue) PushBack(item Pair) bool {
	queue.Data[queue.End%len(queue.Data)] = item
	queue.End += 1
	queue.Size++
	return true
}

func (queue *Queue) PopFront() (item Pair) {
	item = queue.Data[queue.Start%len(queue.Data)]
	queue.Start += 1
	queue.Size--
	return
}

func (queue *Queue) clear() {
	queue.Size, queue.Start, queue.End = 0, 0, 0
}

func solve(board [][]byte) {
	reverser := makeQueue(200 * 200)
	queue := makeQueue(200 * 200)
	for i := 0; i < len(board); i++ {
		for j := 0; j < len(board[0]); j++ {
			if board[i][j] == 'O' {
				queue.clear()
				reverser.clear()
				reverser.PushBack(Pair{byte(i), byte(j)})
				queue.PushBack(Pair{byte(i), byte(j)})
				needReverse := false
				for queue.Size > 0 {
					item := queue.PopFront()
					reverser.PushBack(item)
					if item.F == 0 || item.F == byte(len(board)-1) || item.S == 0 || item.S == byte(len(board[0])-1) {
						needReverse = true
						break
					}
					board[int(item.F)][int(item.S)] = 'X'
					if board[int(item.F-1)][int(item.S)] == 'O' {
						queue.PushBack(Pair{item.F - 1, item.S})
					}
					if board[int(item.F)][int(item.S-1)] == 'O' {
						queue.PushBack(Pair{item.F, item.S - 1})
					}
					if board[int(item.F+1)][int(item.S)] == 'O' {
						queue.PushBack(Pair{item.F + 1, item.S})
					}
					if board[int(item.F)][int(item.S+1)] == 'O' {
						queue.PushBack(Pair{item.F, item.S + 1})
					}
				}
				if needReverse {
					for reverser.Size > 0 {
						itemBack := reverser.PopFront()
						board[int(itemBack.F)][int(itemBack.S)] = 'O'
					}
				}
			}
		}
	}
}