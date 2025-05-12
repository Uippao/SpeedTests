#!/usr/bin/env kotlin

import java.io.File
import java.lang.ProcessBuilder
import kotlin.system.measureTimeMillis

fun runWithTime(name: String, cmd: List<String>, dir: File) {
    println("▶ $name")
    val time = measureTimeMillis {
        val process = ProcessBuilder(cmd)
            .directory(dir)
            .inheritIO()
            .start()
        process.waitFor()
    }
    println("⏱️  Took: ${time}ms\n")
}

fun compileIfMissing(cmd: List<String>, outputFile: String, dir: File) {
    if (!File(dir, outputFile).exists()) {
        println("⚙️  Compiling ${dir.name}...")
        val process = ProcessBuilder(cmd)
            .directory(dir)
            .inheritIO()
            .start()
        val exitCode = process.waitFor()
        if (exitCode != 0) {
            println("❌ Compilation failed for ${dir.name}")
        }
    }
}

val base = File(".")

data class Entry(val folder: String, val compile: List<String>?, val output: String?, val run: List<String>)

val tests = listOf(
    Entry("C", listOf("gcc", "count.c", "-o", "count"), "count", listOf("./count")),
    Entry("C++", listOf("g++", "count.cpp", "-o", "count"), "count", listOf("./count")),
    Entry("Rust", listOf("rustc", "count.rs", "-o", "count"), "count", listOf("./count")),
    Entry("C#", listOf("mcs", "count.cs"), "count.exe", listOf("mono", "count.exe")),
    Entry("Java", listOf("javac", "Count.java"), "Count.class", listOf("java", "Count")),
    Entry("Kotlin", listOf("kotlinc", "count.kt", "-include-runtime", "-d", "count.jar"), "count.jar", listOf("java", "-jar", "count.jar")),
    Entry("KotlinNative", listOf("kotlinc-native", "count.kt", "-o", "count",), "count", listOf("./count"))
)
