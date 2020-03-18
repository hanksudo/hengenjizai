fn main() {
    let y = {
        let x = 3;
        x + 1 // expression - return
    };
    println!("The value of y is : {}", y);
    
    let x = 2;

    // With type declarations
    let square = |x: i32| -> i32 {
        x * x
    };
    println!("{}", square(x));

    // Without type declarations
    let square = |x| x * x;
    println!("{}", square(x));
}

