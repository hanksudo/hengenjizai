# Rust

## Installation

```bash
# install
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

# Update Rust toolchains and rustup
rustup update

# install component rustfmt
rustup component add rustfmt --toolchain stable-x86_64-apple-darwin

# check version
rustc --version
cargo --version
```

## Execute

```bash
# single file
rustc hello_world.rs
./hello_world

cargo new project_name
cargo build

# Building for Release
cargo build --release

# Auto build if source code modified before run
cargo run

# Check binary
cargo check

# 
cargo update

# documentation
cargo doc --open
```

## Syntax

```rust
let foo = 5; // immutable
let mut bar = 5; // mutable

let x = 5;
let y = 6;

println!("x = {} and y = {}", x, y);
```

## References

- [Rust programming language](https://www.rust-lang.org/)
- [Learning Rust](https://learning-rust.github.io/)
- [Rust By Example](https://doc.rust-lang.org/stable/rust-by-example/)
- [Small exercises to get you used to reading and writing Rust code!](https://github.com/rust-lang/rustlings)
