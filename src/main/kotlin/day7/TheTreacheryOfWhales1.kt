package day7

import AdventOfCodeRunnable
import Main
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.pow

class TheTreacheryOfWhales1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("the_treachery_of_whales.txt")!!)
        val positions: IntArray
        run {
            val posList = sc.nextLine().split(",").map { it.toInt() }
            positions = IntArray(posList.maxOf { it } + 1)
            for (i in posList) {
                positions[i]++
            }
        }
        var lowestSum = Long.MAX_VALUE
        for (i in 0..positions.size) {
            var sum = 0L
            for (j in positions.indices) {
                val e = (j - i).absoluteValue
                // for part 1 just replace ((e.toDouble().pow(2.0).toLong() + e) / 2L) with e
                sum += positions[j] * ((e.toDouble().pow(2.0).toLong() + e) / 2L)
            }
            if (sum < lowestSum) {
                lowestSum = sum
            }
        }
        println(lowestSum)
    }
}