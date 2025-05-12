# SpeedTests

A simple benchmark to compare the performance of different programming languages when counting from 1 to 10,000 with printed output.
Each implementation prints numbers 1–10,000 and times how long it takes — including output time.

📦 Distributed as: `SpeedTests.zip`
🖥️ Platform: Linux only

---

## 📁 Structure

Each language has its own folder:

```
SpeedTests/
├── Assembly/
├── C/
├── C#/
├── C++/
├── Java/
├── Kotlin/
├── KotlinNative/
├── Python/
├── Rust/
├── compile.kts
├── run.kts
├── test.kts
```

Each folder contains a file named `count.<ext>` for that language.

---

## 🔧 Dependencies

Ensure you have the following compilers and tools installed:

| Language      | Toolchain Needed  |
| ------------- | ----------------- |
| Assembly      | `nasm`, `gcc`     |
| C             | `gcc`             |
| C++           | `g++`             |
| C#            | `mcs` (Mono)      |
| Java          | `javac`           |
| Kotlin (JVM)  | `kotlinc`, `java` |
| Kotlin/Native | `kotlinc-native`  |
| Rust          | `rustc`           |
| Python        | `python3`         |
| Kotlin Script | `kotlinc`         |

---

## 🚀 Compiling All at Once

To compile all implementations:

```bash
kotlinc -script compile.kts
```

This will compile each language's file into its respective output file in-place.

---

## ▶️ Running All Programs

To run all compiled programs:

```bash
kotlinc -script run.kts
```

Make sure to compile first using `compile.kts`.

---

## ⏱️ Benchmarking with Timing

To compile (if needed), run, and measure execution time:

```bash
kotlinc -script test.kts
```

This shows the total time taken by each implementation.

---

## 🔨 Manual Compilation Commands

If you prefer compiling manually:

```bash
# Assembly
cd Assembly
nasm -f elf64 count.asm -o count.o
gcc -nostartfiles -no-pie -o count count.o

# C
cd C && gcc count.c -o count

# C++
cd C++ && g++ count.cpp -o count

# Rust
cd Rust && rustc count.rs -o count

# C#
cd C# && mcs count.cs

# Java
cd Java && javac Count.java

# Kotlin (JVM)
cd Kotlin && kotlinc count.kt -include-runtime -d count.jar

# Kotlin/Native
cd KotlinNative && kotlinc-native count.kt -o count

# Python
# No compilation required
```

---

## ▶️ Manual Execution

After compiling, run each with:

```bash
# Native binaries (C, C++, Rust, Kotlin Native, Assembly)
./count

# Kotlin/Native (might generate count.kexe, renamed as count by script)
./count

# Java
java Count

# Kotlin (JVM)
java -jar count.jar

# C#
mono count.exe

# Python
python3 count.py
```

---

## 📌 Notes

* All programs print numbers 1–10,000 and time themselves.
* Output includes timing data inside each implementation.
* Purpose: raw comparison of loop + I/O cost across languages.
* No external libraries used; standard I/O only.
* Not optimized — intentionally simple, realistic benchmarks.
