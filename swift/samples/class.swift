class Shape {
    var numberOfSides = 0
    func simpleDescription() -> String {
        "A shape with \(numberOfSides) sides."
    }
}

var shape = Shape()
shape.numberOfSides = 7
print(shape.simpleDescription())

// Super class and sub class
class NamedShape {
    var numberOfSides = 0
    var name: String

    init(name: String) {
        self.name = name
    }

    func simpleDescription() -> String {
        "A shape with \(numberOfSides) sides."
    }
}

class Square: NamedShape {
    var sideLength: Double

    init(sideLength: Double, name: String) {
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 4
    }

    func area() -> Double {
        sideLength * sideLength
    }

    override func simpleDescription() -> String {
        "A square with sides of length \(sideLength)."
    }
}

let square = Square(sideLength: 5.2, name: "Square")
print(square.area())
print(square.simpleDescription())
print(square.numberOfSides)

// getter and setter
class EquilateralTriangle: NamedShape {
    var sideLength: Double = 0.0

    init(sideLength: Double, name: String) {
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 3
    }

    var perimeter: Double {
        get {
            3.0 * sideLength
        }
        set {
            sideLength = newValue / 3.0
        }
    }

    override func simpleDescription() -> String {
        "A Equilateral Triangle with sides of length \(sideLength), perimeter \(perimeter)."
    }
}

var triangle = EquilateralTriangle(sideLength: 3.1, name: "a triangle")
print(triangle.simpleDescription())
triangle.perimeter = 12
print(triangle.simpleDescription())

// willSet
class TriangleAndSquare {
    var triangle: EquilateralTriangle {
        willSet {
            square.sideLength = newValue.sideLength
        }
    }

    var square: Square {
        willSet {
            triangle.sideLength = newValue.sideLength
        }
    }

    init(size: Double, name: String) {
        square = Square(sideLength: size, name: name)
        triangle = EquilateralTriangle(sideLength: size, name: name)
    }
}

var triangleAndSquare = TriangleAndSquare(size: 10, name: "another test shape")
print(triangleAndSquare.square.sideLength)
print(triangleAndSquare.triangle.sideLength)
triangleAndSquare.square = Square(sideLength: 50, name: "large square")
print(triangleAndSquare.square.sideLength)
print(triangleAndSquare.triangle.sideLength)

// optional value
var emptySquare: Square?
// if value before ? is nil, everything after the ? is ignored
// like methods and properties and subscripting
var sideLength = emptySquare?.sideLength
print(sideLength) // nil
