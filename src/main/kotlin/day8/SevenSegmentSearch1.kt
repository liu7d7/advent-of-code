package day8

import AdventOfCodeRunnable
import Main
import java.util.*

class SevenSegmentSearch1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("seven_segment_search.txt")!!)
        var sum = 0L
        while (sc.hasNextLine()) {
            val e = sc.nextLine().split("| ")[1].split(" ")
            sum += e.sumOf { if (it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7) 1L else 0L }
        }
        println(sum)
    }
}