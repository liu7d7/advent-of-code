import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.pow

fun main(args: Array<String>) {
    val adventOfCodeRunnable = BinaryDiagnostic2()
    adventOfCodeRunnable()
}

interface AdventOfCodeRunnable {
    operator fun invoke()
}

class SonarSweep1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(SonarSweep1::class.java.getResourceAsStream("sonar_sweep.txt")!!)
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

class SonarSweep2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(SonarSweep2::class.java.getResourceAsStream("sonar_sweep.txt")!!)
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

class Dive1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Dive1::class.java.getResourceAsStream("dive_!.txt")!!)
        var hPos = 0
        var depth = 0
        while (sc.hasNext()) {
            when (sc.next()) {
                "forward" -> hPos += sc.nextInt()
                "down" -> depth += sc.nextInt()
                "up" -> depth -= sc.nextInt()
            }
        }
        println(hPos * depth)
    }
}

class Dive2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Dive2::class.java.getResourceAsStream("dive_!.txt")!!)
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

class BinaryDiagnostic1: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(BinaryDiagnostic1::class.java.getResourceAsStream("binary_diagnostic.txt")!!)
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

class BinaryDiagnostic2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(BinaryDiagnostic2::class.java.getResourceAsStream("binary_diagnostic.txt")!!)
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