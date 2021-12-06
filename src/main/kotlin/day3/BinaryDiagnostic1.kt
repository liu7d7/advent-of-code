package day3

import AdventOfCodeRunnable
import Main
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

class BinaryDiagnostic1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("binary_diagnostic.txt")!!)
        val ints = ArrayList<ArrayList<Int>>()
        while (sc.hasNext()) {
            val cur = sc.next()
            if (ints.isEmpty()) {
                for (i in cur.indices) {
                    ints.add(ArrayList())
                }
            }
            for (i in cur.indices) {
                ints[i].add(Integer.parseInt(cur[i].toString()))
            }
        }
        var gamma /* most common */ = 0
        var epsilon /* least common */ = 0
        for (i in ints.size - 1 downTo 0) {
            val multBy = 2.0.pow((ints.size - 1) - i)
            if (ints[i].count { it == 0 } > ints[i].count { it == 1 }) {
                epsilon += multBy.toInt()
            } else {
                gamma += multBy.toInt()
            }
        }
        println(gamma * epsilon)
    }
}