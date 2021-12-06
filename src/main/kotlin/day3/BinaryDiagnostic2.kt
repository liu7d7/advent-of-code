package day3

import AdventOfCodeRunnable
import Main
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

class BinaryDiagnostic2: AdventOfCodeRunnable {
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
        /* find # o2 gen rating */
        var o2GenRating = 0
        run {
            val o2ValidIndices = mutableListOf<Int>()
            o2ValidIndices.addAll(ints[0].indices)
            for (i in ints) {
                if (o2ValidIndices.size == 1) {
                    break
                }
                val new = ArrayList<Int>()
                i.forEachIndexed { index, it ->
                    if (o2ValidIndices.contains(index)) {
                        new += it
                    }
                }
                val c0 = new.count { it == 0 }
                val c1 = new.count { it == 1 }
                if (c0 > c1) {
                    o2ValidIndices.removeIf {
                        i[it] == 1
                    }
                } else if (c0 < c1) {
                    o2ValidIndices.removeIf {
                        i[it] == 0
                    }
                } else if (c0 == c1) {
                    o2ValidIndices.removeIf {
                        i[it] == 0
                    }
                }
            }
            val validIndex = o2ValidIndices[0]
            for (i in ints.size - 1 downTo 0) {
                val multBy = 2.0.pow((ints.size - 1) - i)
                o2GenRating += ints[i][validIndex] * multBy.toInt()
            }
        }
        /* find # co2 gen rating */
        var co2GenRating = 0
        run {
            val co2ValidIndices = mutableListOf<Int>()
            co2ValidIndices.addAll(ints[0].indices)
            for (i in ints) {
                if (co2ValidIndices.size == 1) {
                    break
                }
                val new = ArrayList<Int>()
                i.forEachIndexed { index, it ->
                    if (co2ValidIndices.contains(index)) {
                        new += it
                    }
                }
                val c0 = new.count { it == 0 }
                val c1 = new.count { it == 1 }
                if (c0 < c1) {
                    co2ValidIndices.removeIf {
                        i[it] == 1
                    }
                } else if (c0 > c1) {
                    co2ValidIndices.removeIf {
                        i[it] == 0
                    }
                } else if (c0 == c1) {
                    co2ValidIndices.removeIf {
                        i[it] == 1
                    }
                }
            }
            val validIndex = co2ValidIndices[0]
            for (i in ints.size - 1 downTo 0) {
                val multBy = 2.0.pow((ints.size - 1) - i)
                co2GenRating += ints[i][validIndex] * multBy.toInt()
            }
        }
        println(o2GenRating * co2GenRating)
    }
}