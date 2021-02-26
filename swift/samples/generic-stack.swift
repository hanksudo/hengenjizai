// https://docs.swift.org/swift-book/LanguageGuide/Generics.html#ID184

// nongeneric version
struct IntStack {
    var items = [Int]()
    mutating func push(_ item: Int) {
        items.append(item)
    }

    mutating func pop() -> Int {
        items.removeLast()
    }
}

var stack = IntStack()
stack.push(1)
print(stack)
_ = stack.pop()
print(stack)

// generic version
struct Stack<Element> {
    var items = [Element]()
    mutating func push(_ item: Element) {
        items.append(item)
    }

    mutating func pop() -> Element {
        items.removeLast()
    }
}

extension Stack {
    var topItem: Element? {
        return items.isEmpty ? nil : items[items.count - 1]
    }
}

var stack2 = Stack<Double>()
stack2.push(1.0)
print(stack2)
print(stack2.topItem)

_ = stack2.pop()
print(stack2)
