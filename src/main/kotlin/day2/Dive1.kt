package day2

import AdventOfCodeRunnable
import Main
import java.util.*

class Dive1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("dive_!.txt")!!)
        var hPos = 0
        var depth = 0
        while (sc.hasNext()) {
            when (sc.next()) {
                "forward" -> hPos += sc.nextInt()
                "down" -> depth += sc.nextInt()
                "up" -> depth -= sc.nextInt()
            }
        }
        println(hPos * depth)
    }
}