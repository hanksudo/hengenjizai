use std::env;

fn main() {
    let args: Vec<String> = env::args().collect();
    let command = args[1].clone();
    println!("{:?}", args);
    println!("{}", command);
}
