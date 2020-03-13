fn main() {
    for i in 0..10 {
        println!("fib({}) = {}", i, fib(i));
    }
}

fn fib(number: i64) -> i64 {
    if number <= 0 {
        return 0;
    } else if number <= 2 {
        return 1;
    }
    fib(number - 1) + fib(number - 2)
}
