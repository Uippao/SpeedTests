use std::time::Instant;

fn main() {
    let start = Instant::now();
    for i in 1..=10_000 {
        println!("{}", i);
    }
    let elapsed = start.elapsed().as_secs_f64();
    println!("Elapsed: {:.9} seconds", elapsed);
}
