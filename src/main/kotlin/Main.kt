import day6.Lanternfish2
import day7.TheTreacheryOfWhales1
import day8.SevenSegmentSearch1
import day8.SevenSegmentSearch2
import day9.SmokeBasin1
import day9.SmokeBasin2

class Main

fun main(args: Array<String>) {
    val adventOfCodeRunnable = SmokeBasin2()
    adventOfCodeRunnable()
}

interface AdventOfCodeRunnable {
    operator fun invoke()
}

