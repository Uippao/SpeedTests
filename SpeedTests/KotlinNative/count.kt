import kotlin.system.measureTimeMillis
import kotlin.time.TimeSource

fun main() {
    val start = TimeSource.Monotonic.markNow()  // Using TimeSource.Monotonic.markNow()
    for (i in 1..10_000) println(i)
    val elapsed = start.elapsedNow()  // Get the elapsed time

    // Using String.format
    println("Elapsed: %.9f seconds".format(elapsed.inWholeNanoseconds / 1e9))
}
