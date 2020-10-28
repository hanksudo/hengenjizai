func swap(_ x: inout Int, _ y: inout Int) {
    let z = x
    x = y
    y = z
}

var num1 = 50
var num2 = 95

print(num1, num2)

swap(&num1, &num2)

print(num1, num2)