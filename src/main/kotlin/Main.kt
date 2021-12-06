import day6.Lanternfish2

class Main

fun main(args: Array<String>) {
    val adventOfCodeRunnable = Lanternfish2()
    adventOfCodeRunnable()
}

interface AdventOfCodeRunnable {
    operator fun invoke()
}

