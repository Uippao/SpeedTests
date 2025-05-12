import kotlin.time.DurationUnit
import kotlin.time.TimeSource
import platform.posix.printf

fun main() {
    val mark = TimeSource.Monotonic.markNow()
    for (i in 1..10_000) println(i)
    val elapsed = mark.elapsedNow()
    val seconds = elapsed.toDouble(DurationUnit.NANOSECONDS) / 1e9
    printf("Elapsed: %.9f seconds\n", seconds)
}
