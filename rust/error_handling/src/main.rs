use std::fs::File;
use std::io::ErrorKind;

fn main() {
    // method 1: with match
    let filename = String::from("hello1.txt");
    let f = File::open(&filename);

    let f = match f {
        Ok(file) => file,
        Err(error) => match error.kind() {
            ErrorKind::NotFound => match File::create(&filename) {
                Ok(fc) => fc,
                Err(e) => panic!("Problem creating the file: {:?}", e),
            },
            other_error => panic!("Problem opening the file: {:?}", other_error),
        },
    };
    println!("{:?}", f);

    // method 2: with unwrap_or_else
    // https://doc.rust-lang.org/stable/std/result/enum.Result.html#method.unwrap_or_else
    let filename = String::from("hello2.txt");
    let f = File::open(&filename).unwrap_or_else(|error| {
        if error.kind() == ErrorKind::NotFound {
            File::create(&filename).unwrap_or_else(|error| {
                panic!("Problem creating the file: {:?}", error);
            })
        } else {
            panic!("Problem opening the file: {:?}", error);
        }
    });
    println!("{:?}", f);

    // method 3: unwrap
    // https://doc.rust-lang.org/stable/std/result/enum.Result.html#method.unwrap
    let f = File::open("hello2.txt").unwrap();
    println!("{:?}", f);

    // method 4: except
    // https://doc.rust-lang.org/stable/std/result/enum.Result.html#method.expect
    let f = File::open("hello4.txt").expect("Failed to open hello.txt");
    println!("{:?}", f)
}
