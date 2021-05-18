// Variables are immutable by default

pub fn run() {
    // default
    let default_integer = 7; // `i32`
    let default_float = 3.6; // `f64`
    println!("{:?}", (default_float, default_integer));

    // Find max size
    println!("Max i32: {}", std::i32::MAX);
    println!("Max i8: {}", std::i8::MAX);

    let a_float: f64 = 1.0; // Regular annotation
    let an_integer = 5i32; // Suffix annotation
    let a = "Hello, world."; //a: &'static str
    let b: &str = "こんにちは、世界！"; // &str - unicode string slices

    println!("{:?}", (a_float, an_integer, a, b));

    // Boolean
    let t = true;
    let f: bool = false;
    println!("{} {}", t, f);

    let (i, j) = (2, 4);
    println!("{} {}", i, j);

    // A type can also be inferred from context
    let mut _inferred_type = 12; // i64
    _inferred_type = 4294967296i64;

    // Mutable
    let mut x = 5;
    println!("The value of x is: {}", x);
    x = 6;
    println!("The value of x is: {}", x);

    // Contstants
    const MATH_POINT: u32 = 100_000;
    println!("{}", MATH_POINT);

    // Shadowing
    let x = 5;
    let x = x + 1;
    let x = x * 2;
    println!("The value of x is: {}", x); // 12

    // Ok
    let spaces = "    ";
    let spaces = spaces.len();
    println!("Spaces: {}", spaces);
    // Err
    // let mut spaces2 = "    ";
    // spaces2 = spaces2.len();

    // Bitwise operations
    println!("0011 AND 0101 is {:04b}", 0b0011u32 & 0b0101);
    println!("0011 OR 0101 is {:04b}", 0b0011u32 | 0b0101);
    println!("0011 XOR 0101 is {:04b}", 0b0011u32 ^ 0b0101);
    println!("1 << 5 is {}", 1u32 << 5);
    println!("0x80 >> 2 is 0x{:x}", 0x80u32 >> 2);

    // Use underscores to improve readability!
    println!("One million is written as {}", 1_000_000u32);
}
