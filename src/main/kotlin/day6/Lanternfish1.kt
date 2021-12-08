package day6

import AdventOfCodeRunnable
import Main
import java.util.*

class Lanternfish1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("lantern_fish.txt")!!)
        var lanternfishes = sc.nextLine().split(",").map { it.toInt() }.toMutableList()
        for (i in 0 until 80) {
            var numLanternfishes = 0
            for (j in lanternfishes) {
                if (j == 0) {
                    numLanternfishes++
                }
            }
            lanternfishes = lanternfishes.map { it - 1 }.toMutableList()
            for (k in 0 until numLanternfishes) {
                lanternfishes.add(8)
                lanternfishes.add(6)
            }
            lanternfishes.removeIf { it == -1 }
            println("done with $i")
        }
        println(lanternfishes.size)
    }
}