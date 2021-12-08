package day6

import AdventOfCodeRunnable
import Main
import java.util.*

class Lanternfish2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("lantern_fish.txt")!!)
        val lfishes = LongArray(9)
        for (i in sc.nextLine().split(",")) {
            lfishes[i.toInt()]++
        }
        for (i in 0 until 256) {
            val oldLfishes = lfishes.clone()
            for (j in 0 until 8) {
                lfishes[j] = oldLfishes[j + 1]
            }
            lfishes[8] = oldLfishes[0]
            lfishes[6] += oldLfishes[0]
        }
        println(lfishes.sum())
    }
}