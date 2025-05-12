#!/usr/bin/env kotlin

import java.io.File
import java.lang.ProcessBuilder

fun compile(cmd: List<String>, dir: File) {
    println("Compiling in: ${dir.name}")
    val process = ProcessBuilder(cmd)
        .directory(dir)
        .inheritIO()
        .start()
    val exitCode = process.waitFor()
    if (exitCode != 0) {
        println("❌ Compilation failed in ${dir.name}")
    } else {
        println("✅ Compiled: ${dir.name}")
    }
}

val base = File(".")
val langs = mapOf(
    "C"            to listOf("gcc", "count.c", "-o", "count"),
    "C++"          to listOf("g++", "count.cpp", "-o", "count"),
    "Rust"         to listOf("rustc", "count.rs", "-o", "count"),
    "Java"         to listOf("javac", "Count.java"),
    "Kotlin"       to listOf("kotlinc", "count.kt", "-include-runtime", "-d", "count.jar"),
    "KotlinNative" to listOf("kotlinc-native count.kt -o count -Xcache-directory=${System.getProperty("user.home")}/.cache/kotlin-native"),
    "C#"           to listOf("mcs", "count.cs"),
    "Assembly"     to listOf("sh", "-c", "nasm -f elf64 count.asm && ld -o count count.o")
)

langs.forEach { (folder, command) ->
    val dir = File(base, folder)
    if (!dir.exists()) {
        println("Skipping missing folder: $folder")
        return@forEach
    }
    compile(command, dir)
}
