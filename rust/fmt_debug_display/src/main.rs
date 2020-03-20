// https://doc.rust-lang.org/rust-by-example/hello/print/print_debug.html
// https://doc.rust-lang.org/rust-by-example/hello/print/print_display.html
use std::fmt;

// Can't print with fmt::Debug or fmt::Display
#[allow(dead_code)]
struct UnPrintable(i32);

// The `derive` attribute automatically create implementation required
// to make this `struct` printable with fmt::Debug
#[derive(Debug)]
struct Printable(i32);

#[derive(Debug)]
struct Person<'a> {
    name: &'a str,
    age: u8
}

struct Structure(i32);

fn main() {
    println!("{:?}", Printable(3));
    println!("{:#?}", Printable(3));

    let person = Person { name: "Hank", age: 35 };
    println!("{:#?}", person);

    println!("{}", Structure(1));
}

impl fmt::Display for Structure {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "Value of structure: {}", self.0)
    }
}