package day1

import AdventOfCodeRunnable
import Main
import java.util.*

class SonarSweep2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("sonar_sweep.txt")!!)
        var increases = 0
        var prev3 = listOf(sc.nextInt(), sc.nextInt(), sc.nextInt())
        while (sc.hasNext()) {
            val cur = listOf(prev3[1], prev3[2], sc.nextInt())
            if (cur.sum() > prev3.sum()) {
                increases++
            }
            prev3 = cur
        }
        println(increases)
    }
}