func makeArray<Item>(repeating item: Item, numberOfTimes: Int) -> [Item] {
    var result = [Item]()

    for _ in 0 ..< numberOfTimes {
        result.append(item)
    }
    return result
}

// ["knock", "knock", "knock", "knock"]
print(makeArray(repeating: "knock", numberOfTimes: 4))

// Reimplement the Swift standard Library's optional type
enum OptionalValue<Wrapped> {
    case none
    case some(Wrapped)
}

var possibleInteger: OptionalValue<Int> = .none
possibleInteger = .some(100)
print(possibleInteger)

/**
 Writing <T: Equatable> is the same as writing <T> ... where T: Equatable
 */
func anyCommonElements<T: Sequence, U: Sequence>(_ lhs: T, _ rhs: U) -> Bool where T.Element: Equatable, T.Element == U.Element {
    for lhsItem in lhs {
        for rhsItem in rhs {
            if lhsItem == rhsItem {
                return true
            }
        }
    }
    return false
}

func commonElements<T: Sequence, U: Sequence>(_ lhs: T, _ rhs: U) -> [T.Element] where T.Element: Equatable, T.Element == U.Element {
    var elements = [T.Element]()
    for lhsItem in lhs {
        for rhsItem in rhs {
            if lhsItem == rhsItem {
                elements.append(lhsItem)
            }
        }
    }
    return elements
}

print(anyCommonElements([1, 2, 3], [3]))
print(commonElements([1, 2, 3], [3]))
