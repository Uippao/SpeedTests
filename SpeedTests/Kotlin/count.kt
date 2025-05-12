fun main() {
    val start = System.nanoTime()
    for (i in 1..10_000) println(i)
    val elapsed = System.nanoTime() - start
    println("Elapsed: %.9f seconds".format(elapsed / 1e9))
}
