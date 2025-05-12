# SpeedTests

A simple benchmark to compare the performance of different programming languages when counting from 1 to 10,000 with printed output.  
Each language implementation measures how long the full process (including printing) takes.  

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

````

Each folder contains a file named `count.<ext>` for that language.

---

## 🔧 Dependencies

Make sure you have the following compilers and tools installed:

| Language        | Toolchain Needed                  |
|----------------|-----------------------------------|
| Assembly       | `nasm`, `ld`                      |
| C              | `gcc`                             |
| C++            | `g++`                             |
| C#             | `mcs` (Mono)                      |
| Java           | `javac`                           |
| Kotlin/JVM     | `kotlinc`                         |
| Kotlin/Native  | `kotlinc-native`                  |
| Rust           | `rustc`                           |
| Python         | `python3`                         |
| Kotlin Script  | `kotlinc`                         |

---

## 🚀 Compiling All at Once

You can compile all files using the provided Kotlin script:

```bash
kotlinc -script compile.kts
````

This will compile all source files into binaries or `.class`/`.jar` files in their respective folders.

---

## ▶️ Running All Programs

To run all compiled programs:

```bash
kotlinc -script run.kts
```

Make sure you've already compiled the files before running.

---

## ⏱️ Testing with Timing

To compile (if needed), run, and time each program:

```bash
kotlinc -script test.kts
```

This is useful for benchmarking all implementations in one go.

---

## 🔨 Manual Compilation Commands

If you want to compile manually:

```bash
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

# Kotlin/JVM
cd Kotlin && kotlinc count.kt -include-runtime -d count.jar

# Kotlin/Native
cd KotlinNative && kotlinc-native count.kt -o count

# Assembly
cd Assembly && nasm -f elf64 count.asm && ld -o count count.o

# Python
# No compilation needed
cd Python && python3 count.py
```

---

## ▶️ Running Individually

Once compiled, run each binary from its directory:

```bash
./count              # For C, C++, Rust, KotlinNative, Assembly
mono count.exe       # For C#
java Count           # For Java
java -jar count.jar  # For Kotlin (JVM)
python3 count.py     # For Python
```

Each implementation prints numbers from 1 to 10,000 and reports elapsed time.

---

## 📌 Notes

* All tests include printing to reflect realistic I/O overhead.
* Time output format may differ slightly between languages.
* This is not meant to reflect optimal performance or best practices — just a raw comparison of loop + I/O overhead per language.
* To get started you can either download the source code as zip or get the distributable zip from the releases
