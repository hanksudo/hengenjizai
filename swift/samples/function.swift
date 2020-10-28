func greet(person: String, day: String) -> String {
    return "Hello \(person), today is \(day)."
}

print(greet(person: "Bob", day: "Tuesday"))

// Omit argument label and custom argument label
func greet(_ person: String, on day: String) -> String {
    return "Hello \(person), today is \(day)."
}

print(greet("John", on: "Wednesday"))

// Return multiple values by Tuple
func values() -> (x: Int, y: Int, z: Int) {
    let x = 10
    let y = 20
    let z = 30
    return (x, y, z)
}

let result = values()
print(result.y)
print(result.2)

// Nested function
func returnFifteen() -> Int {
    var y = 10
    func add() {
        y += 5
    }
    add()
    return y
}

print(returnFifteen())

//
// Variadic Parameters
//
func arithmeticMean(_ numbers: Double...) -> Double {
    var total: Double = 0
    for number in numbers {
        total += number
    }
    return total / Double(numbers.count)
}

print(arithmeticMean(1, 2, 3, 4, 5))
print(arithmeticMean(3, 8.25, 18.75))
