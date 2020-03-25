let numbers = 1...5
let res = numbers.map({ (number: Int) -> Int in
    let result = 3 * number
    return result
})
print(res)

// omit type of parameter
let mappedNumbers = numbers.map({ number in 3 * number })
print(mappedNumbers)

// refer by number
let sortedNumbers = numbers.sorted { $0 > $1 }
print(sortedNumbers)
