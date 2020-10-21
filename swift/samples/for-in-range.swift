var total = 0
for i in 0 ..< 4 {
    total += i
}

print(total) // 6

total = 0
for i in 0 ... 4 {
    total += i
}

print(total) // 10


// Labeled statement
// https://docs.swift.org/swift-book/LanguageGuide/ControlFlow.html#ID135
outLoop: for i in 0 ... 4 {
    for j in 0 ... 4 {
        print(i, j)
        break outLoop
    }
}