/*
    block comment
*/

pub fn run() {
    // println is a Rust macro
    println!("Hello, world!");
    println!("{}, {}!", "Hello", "world"); // Hello, world!
    println!("{0}, {1}! {1} {0}", "Hello", "world"); // Hello, world!
    println!("{greeting}, {name}!", greeting = "Hello", name = "world"); // Hello, world!

    println!("{:?}", [1, 2, 3]); // [1, 2, 3]
    println!("{:#?}", [1, 2, 3]);
    /*
        [
            1,
            2,
            3
        ]
    */

    // The format! marco is used to store the formatted string.
    let x = format!("{}, {}!", "Hello", "world");
    println!("{}", x); // Hello, world!

    // Special formatting
    println!("{} {:b} binary", 1, 15);
    println!("Binary: {:b} Hex {:x} Octal: {:o}", 10, 10, 10);

    // right align
    println!("{number:>width$}", number = 1, width = 6);

    // pad number
    println!("{number:>0width$}", number = 1, width = 6);

    const PI: f64 = 3.1415926;
    println!("Pi is roughly {:.3}", PI);

    // Print custom type
    #[derive(Debug)]
    struct Structure(i32);
    println!("print struct {:?}", Structure(3));
}
