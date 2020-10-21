let finalSquare = 25
var board = [Int](repeating: 0, count: finalSquare + 1)

// Ladders
board[03] = +08; board[06] = +11; board[09] = +09; board[10] = +02

// Snakes
board[14] = -10; board[19] = -11; board[22] = -02; board[24] = -08

var square = 0
var diceRoll = {
    Int.random(in: 1 ... 6)
}

repeat {
    // if still on the board, move up or down for a snake or a ladder
    if board[square] > 0 {
        print("Encounterd Ladder \(board[square])")
    } else if board[square] < 0 {
        print("Encounterd Snake \(board[square])")
    }
    square += board[square]

    let pointOfDiceRoll = diceRoll()
    print("Square: \(square), Dice: \(pointOfDiceRoll)")
    // move by the rolled amount
    square += pointOfDiceRoll
} while square < finalSquare
print("Game over!")
