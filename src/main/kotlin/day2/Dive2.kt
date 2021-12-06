package day2

import AdventOfCodeRunnable
import Main
import java.util.*

class Dive2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("dive_!.txt")!!)
        var horiz = 0
        var depth = 0
        var aim = 0
        while (sc.hasNext()) {
            when (sc.next()) {
                "forward" -> {
                    val int = sc.nextInt()
                    horiz += int
                    depth += int * aim
                }
                "down" -> aim += sc.nextInt()
                "up" -> aim -= sc.nextInt()
            }
        }
        println(horiz * depth)
    }
}