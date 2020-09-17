//: [Previous](@previous)

import Foundation
import RxSwift

example(of: "toArray") {
    let disposeBag = DisposeBag()
    
    Observable.of("A", "B", "C")
    .toArray()
        .subscribe(onSuccess: {
            print($0)
        })
        .disposed(by: disposeBag)
}

example(of: "map") {
    let disposeBag = DisposeBag()
    
    let formatter = NumberFormatter()
    formatter.numberStyle = .spellOut
    
    Observable<Int>.of(123, 4, 56)
        .map {
            formatter.string(for: $0) ?? ""
        }
        .subscribe(onNext: {
            print($0)
        })
        .disposed(by: disposeBag)
}

example(of: "enumerated and map") {
    let disposeBag = DisposeBag()
    
    Observable.of(1, 2, 3, 4, 5, 6)
        .enumerated()
        .map { index, integer in
            index > 2 ? integer * 2 : integer
        }
        .subscribe(onNext: {
            print($0)
        })
        .disposed(by: disposeBag)
}

example(of: "compactMap") {
    let disposeBag = DisposeBag()
    
    Observable.of("To", "be", nil, "or", "not", "to", "be", nil)
        .compactMap { $0 }
        .toArray()
        .map { $0.joined(separator: " ") }
        .subscribe(onSuccess: {
            print($0)
        })
        .disposed(by: disposeBag)
}


example(of: "") {
    let disposeBag = DisposeBag()
    Observable.of()
    .subscribe(onNext: {
        print($0)
    })
    .disposed(by: disposeBag)
}
//: [Next](@next)
