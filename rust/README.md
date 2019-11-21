# Rust

## Installation

```bash
# install
curl https://sh.rustup.rs -sSf | sh

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
cargo build --release

# Auto build if source code modified before run
cargo run

# Check binary
cargo check
```

## References

- [Rust programming language](https://www.rust-lang.org/)
- [Learning Rust](https://learning-rust.github.io/)
- [Rust By Example](https://doc.rust-lang.org/stable/rust-by-example/)
