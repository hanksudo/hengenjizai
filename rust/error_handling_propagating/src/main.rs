// https://doc.rust-lang.org/stable/book/ch09-02-recoverable-errors-with-result.html#a-shortcut-for-propagating-errors-the--operator
use std::io;
use std::io::Read;
use std::fs;
use std::fs::File;

fn main() {
    println!("{:?}", read_username_from_file_1());
    println!("{:?}", read_username_from_file_2());
    println!("{:?}", read_username_from_file_3());
    println!("{:?}", read_username_from_file_4());
}

fn read_username_from_file_1() -> Result<String, io::Error> {
    let f = File::open("hello.txt");

    let mut f = match f {
        Ok(file) => file,
        Err(e) => return Err(e),
    };

    let mut s = String::new();
    match f.read_to_string(&mut s) {
        Ok(_) => Ok(s),
        Err(e) => Err(e),
    }
}

// use ? operator to shortcut propagating erros
fn read_username_from_file_2() -> Result<String, io::Error> {
    let mut f = File::open("hello.txt")?;
    let mut s = String::new();
    f.read_to_string(&mut s)?;
    Ok(s)
}

// chainng method calls
fn read_username_from_file_3() -> Result<String, io::Error> {
    let mut s = String::new();

    File::open("hello.txt")?.read_to_string(&mut s)?;

    Ok(s)
}

// convenient fs::read_to_string
// https://doc.rust-lang.org/stable/std/fs/fn.read_to_string.html
fn read_username_from_file_4() -> Result<String, io::Error> {
    fs::read_to_string("hello.txt")
}
    