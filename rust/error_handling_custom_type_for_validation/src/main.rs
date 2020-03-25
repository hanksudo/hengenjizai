// https://doc.rust-lang.org/stable/book/ch09-03-to-panic-or-not-to-panic.html#creating-custom-types-for-validation
pub struct Guess {
    value: i32,
}

impl Guess {
    pub fn new(value: i32) -> Guess {
        if value < 1 || value > 100 {
            panic!("Guess value must be between 1 and 100, got {}.", value);
        }

        Guess {
            value
        }
    }

    pub fn value(&self) -> i32 {
        self.value
    }
}

fn main() {
    let _g = Guess::new(-3);
}
