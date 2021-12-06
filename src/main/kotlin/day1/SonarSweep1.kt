package day1

import AdventOfCodeRunnable
import Main
import java.util.*

class SonarSweep1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("sonar_sweep.txt")!!)
        var increases = 0
        var prev = sc.nextInt()
        while (sc.hasNext()) {
            val cur = sc.nextInt()
            if (cur - prev > 0) {
                increases++
            }
            prev = cur
        }
        println(increases)
    }
}