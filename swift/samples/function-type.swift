//
// Function Types
//
func addTwoInts(_ a: Int, _ b: Int) -> Int {
    a + b
}

func multiplyTwoInts(_ a: Int, _ b: Int) -> Int {
    a * b
}

var mathFunction: (Int, Int) -> Int = addTwoInts
print(mathFunction(5, 10)) // 5 + 10

var mathFunction2: (Int, Int) -> Int = multiplyTwoInts
print(mathFunction2(5, 10)) // 5 * 10

// Function Types as Return Types
func makeIncrementer() -> ((Int) -> Int) {
    func addOne(number: Int) -> Int {
        return 1 + number
    }
    return addOne
}

var increment = makeIncrementer()
print(increment(7))

//
// Function Types as Parameter Type
//
func hasAnyMatches(list: [Int], condition: (Int) -> Bool) -> Bool {
    for item in list {
        if condition(item) {
            return true
        }
    }
    return false
}

func lessThanTen(number: Int) -> Bool {
    number < 10
}

var numbers = [20, 19, 7, 12]
print(hasAnyMatches(list: numbers, condition: lessThanTen))
