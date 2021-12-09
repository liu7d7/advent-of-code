package day9

import AdventOfCodeRunnable
import Main
import java.util.*
import kotlin.collections.ArrayList

class SmokeBasin1: AdventOfCodeRunnable {
    override fun invoke() {
        val scnr = Scanner(Main::class.java.getResourceAsStream("smoke_basin.txt")!!)
        val heightMap = ArrayList<ArrayList<Int>>()
        run {
            var idx = 0
            while (scnr.hasNextLine()) {
                heightMap.add(ArrayList())
                for (i in scnr.nextLine()) {
                    heightMap[idx] += i.toString().toInt()
                }
                idx++
            }
        }
        var sum = 0
        for (i in heightMap.indices) {
            for (j in heightMap[i].indices) {
                if (heightMap.indices.contains(i - 1)) {
                    if (heightMap[i - 1][j] <= heightMap[i][j]) {
                        continue
                    }
                }
                if (heightMap.indices.contains(i + 1)) {
                    if (heightMap[i + 1][j] <= heightMap[i][j]) {
                        continue
                    }
                }
                if (heightMap[i].indices.contains(j - 1)) {
                    if (heightMap[i][j - 1] <= heightMap[i][j]) {
                        continue
                    }
                }
                if (heightMap[i].indices.contains(j + 1)) {
                    if (heightMap[i][j + 1] <= heightMap[i][j]) {
                        continue
                    }
                }
                sum += (1 + heightMap[i][j])
            }
        }
        println(sum)
    }
}