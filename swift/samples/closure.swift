let numbers = 1 ... 5
let res = numbers.map { (number: Int) -> Int in
    let result = 3 * number
    return result
}

print(res)

// omit type of parameter
let mappedNumbers = numbers.map { number in 3 * number }
print(mappedNumbers)

// refer by number
let sortedNumbers = numbers.sorted { $0 > $1 }
print(sortedNumbers)

/**
  Sort method
 **/
let names = ["Chris", "Alex", "Ewa", "Barry", "Daniella"]

// normal function
func backward(_ s1: String, _ s2: String) -> Bool {
    return s1 > s2
}

var reversedNames = names.sorted(by: backward)
print(reversedNames)

// Closure expression
reversedNames = names.sorted(by: { (s1: String, s2: String) -> Bool in
    s1 > s2
})
print(reversedNames)

// Infer type from context
reversedNames = names.sorted(by: { s1, s2 in s1 > s2 })
print(reversedNames)

// Implicit returns
reversedNames = names.sorted(by: { s1, s2 in s1 > s2 })
print(reversedNames)

// Shorthand argument names
reversedNames = names.sorted(by: { $0 > $1 })
print(reversedNames)

// Operator methods
reversedNames = names.sorted(by: >)
print(reversedNames)

/**
 Multiple closures
 */

struct Server {}

func download(from _: Server) -> String? {
    "something"
}

func loadPicture(from server: Server, completion: (String) -> Void, onFailure: () -> Void) {
    if let picture = download(from: server) {
        completion(picture)
    } else {
        onFailure()
    }
}

loadPicture(from: Server()) { _ in
    print("completion")
} onFailure: {
    print("Could not download picture")
}
