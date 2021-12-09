package day9

import AdventOfCodeRunnable
import Main
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

class SmokeBasin2: AdventOfCodeRunnable {
    override fun invoke() {
        val scnr = Scanner(Main::class.java.getResourceAsStream("smoke_basin.txt")!!)
        val heightMap = ArrayList<String>()
        run {
            while (scnr.hasNextLine()) {
                heightMap += scnr.nextLine()
            }
        }
        // Pair of <Basin Value, X Y Coordinate>
        val subBasins = ArrayList<Pair<String, Pair<Int, Int>>>()
        for (i in heightMap.indices) {
            var x = 0
            for (j in heightMap[i].split("9")) {
                if (j.isNotEmpty()) {
                    subBasins.add(Pair(j, Pair(x, i)))
                }
                x += if (j.isEmpty()) 1 else j.length + 1
            }
        }
        var basins = ArrayList<ArrayList<Pair<String, Pair<Int, Int>>>>()
        for (i in subBasins) {
            val newBasins = ArrayList<ArrayList<Pair<String, Pair<Int, Int>>>>()
            val basin = arrayListOf(i)
            for (j in basins) {
                if (j.any { i1 -> basin.any { i2 -> subBasinsConnect(i1, i2) } }) {
                    basin.addAll(j)
                } else {
                    newBasins.add(j)
                }
            }
            newBasins.add(basin)
            basins = newBasins
        }
        val bSizes = basins.map { it.sumOf { it.first.length } }.sortedByDescending { it }
        var product = 1L
        product *= bSizes[0]
        product *= bSizes[1]
        product *= bSizes[2]
        println(product)
    }

    private fun subBasinsConnect(a: Pair<String, Pair<Int, Int>>, b: Pair<String, Pair<Int, Int>>): Boolean {
        val yMatches = (a.second.second - b.second.second).absoluteValue == 1 // if they are on the same y level that makes no sense
        val xMatches = (a.second.first until a.second.first + a.first.length).intersect(b.second.first until b.second.first + b.first.length).isNotEmpty()
        return xMatches && yMatches
    }
}