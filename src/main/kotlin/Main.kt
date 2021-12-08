import day6.Lanternfish2
import day7.TheTreacheryOfWhales1
import day8.SevenSegmentSearch1
import day8.SevenSegmentSearch2

class Main

fun main(args: Array<String>) {
    val adventOfCodeRunnable = SevenSegmentSearch2()
    adventOfCodeRunnable()
}

interface AdventOfCodeRunnable {
    operator fun invoke()
}

