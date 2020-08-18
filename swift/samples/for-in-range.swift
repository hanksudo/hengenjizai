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
