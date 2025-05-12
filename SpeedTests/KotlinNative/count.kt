import kotlin.system.getTimeNanos

fun main() {
    val start = getTimeNanos()
    for (i in 1..10_000) println(i)
    val elapsed = getTimeNanos() - start
    println("Elapsed: %.9f seconds".format(elapsed / 1e9))
}
