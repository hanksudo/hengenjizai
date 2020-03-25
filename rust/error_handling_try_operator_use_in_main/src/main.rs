use std::error::Error;
use std::fs::File;

// Allow use ? operator in main function
// Box<dyn Error> means any kind of error.
fn main() -> Result<(), Box<dyn Error>> {
    let f = File::open("hello.txt")?;

    Ok(())
}
