#!/usr/bin/env kotlin

import java.io.File
import java.lang.ProcessBuilder

fun run(cmd: List<String>, dir: File) {
    println("▶ Running ${dir.name}")
    val process = ProcessBuilder(cmd)
        .directory(dir)
        .inheritIO()
        .start()
    val exitCode = process.waitFor()
    if (exitCode != 0) {
        println("❌ Failed to run ${dir.name}")
    }
}

val base = File(".")

val runners = mapOf(
    "C"            to listOf("./count"),
    "C++"          to listOf("./count"),
    "Rust"         to listOf("./count"),
    "C#"           to listOf("mono", "count.exe"),
    "Java"         to listOf("java", "Count"),
    "Kotlin"       to listOf("java", "-jar", "count.jar"),
    "KotlinNative" to listOf("./count"),
    "Assembly"     to listOf("./count"),
    "Python"       to listOf("python3", "count.py")
)

runners.forEach { (folder, cmd) ->
    val dir = File(base, folder)
    if (!dir.exists()) return@forEach
    run(cmd, dir)
}
