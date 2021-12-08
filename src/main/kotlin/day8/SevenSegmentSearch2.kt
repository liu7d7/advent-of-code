package day8

import AdventOfCodeRunnable
import Main
import java.util.*
import kotlin.math.pow

class SevenSegmentSearch2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("seven_segment_search.txt")!!)
        var wholeSome = 0
        while (sc.hasNextLine()) {
            val theTenInputs: Array<String>
            val theOutput: Array<String>
            /*
                  a (0)
               b (1) c (2)
                  d (3)
               e (4) f (5)
                  g (6)
             */
            val screen = Array(7) { "" }
            run {
                val i = sc.nextLine().split(" | ")
                theTenInputs = i[0].split(" ").toTypedArray()
                theOutput = i[1].split(" ").toTypedArray()
            }
            val seven = theTenInputs.find { it.length == 3 }!!
            val one = theTenInputs.find { it.length == 2 }!!
            val eight = theTenInputs.find { it.length == 7 }!!
            val four = theTenInputs.find { it.length == 4 }!!
            // find inputs matched to screen
            run {
                screen[0] = seven.find { !one.contains(it) }.toString()
                val ab = mutableListOf("a", "b", "c", "d", "e", "f", "g")
                // what are a and b? find them.
                run {
                    arrayOf(seven, one, eight, four).forEach { i ->
                        ab.removeIf { !i.contains(it) }
                    }
                }
                val ef = four.map { it.toString() }.toMutableList()
                // what are e and f? find them.
                run {
                    ef.removeIf { one.contains(it) }
                }
                val cg = eight.map { it.toString() }.toMutableList()
                // what are c and g? find them.
                run {
                    cg.remove(screen[0])
                    cg.removeIf { four.contains(it) }
                }
                val twoThreeFive = theTenInputs.filter { it.length == 5 }
                // set e, f, c in screen array
                run {
                    val fc = mutableListOf("a", "b", "c", "d", "e", "f", "g").filter { i -> twoThreeFive.all { it.contains(i) } && i != screen[0] }
                    // find e, f
                    val efClone = ef.toTypedArray()
                    screen[1] = efClone.filter { !fc.contains(it) }[0]
                    screen[3] = efClone.filter { it != screen[1] }[0]
                    // find c
                    screen[4] = cg.filter { !fc.contains(it) }[0]
                    screen[6] = cg.filter { it != screen[4] }[0]
                }
                run {
                    val five = twoThreeFive.filter { it.contains(screen[1]) }[0]
                    screen[5] = five.filter { one.contains(it.toString()) }[0].toString()
                    screen[2] = ab.filter { it != screen[5] }[0]
                }
            }
            var sum = 0
            // find outputs
            run {
                val zero = { it: String ->
                    (it.length == 6 && it.contains(screen[0]) && it.contains(screen[1]) && it.contains(screen[2]) && it.contains(screen[4]) && it.contains(screen[5]) && it.contains(screen[6])) to 0
                }
                val one = { it: String ->
                    (it.length == 2) to 1
                }
                val two = { it: String ->
                    (it.length == 5 && it.contains(screen[0]) && it.contains(screen[2]) && it.contains(screen[3]) && it.contains(screen[4]) && it.contains(screen[6])) to 2
                }
                val three = { it: String ->
                    (it.length == 5 && it.contains(screen[0]) && it.contains(screen[2]) && it.contains(screen[3]) && it.contains(screen[5]) && it.contains(screen[6])) to 3
                }
                val four = { it: String ->
                    (it.length == 4) to 4
                }
                val five = { it: String ->
                    (it.length == 5 && it.contains(screen[0]) && it.contains(screen[1]) && it.contains(screen[3]) && it.contains(screen[5]) && it.contains(screen[6])) to 5
                }
                val six = { it: String ->
                    (it.length == 6 && it.contains(screen[0]) && it.contains(screen[1]) && it.contains(screen[3]) && it.contains(screen[4]) && it.contains(screen[5]) && it.contains(screen[6])) to 6
                }
                val seven = { it: String ->
                    (it.length == 3) to 7
                }
                val eight = { it: String ->
                    (it.length == 7) to 8
                }
                val nine = { it: String ->
                    (it.length == 6 && !it.contains(screen[4])) to 9
                }
                val checks = arrayOf(zero, one, two, three, four, five, six, seven, eight, nine)
                for (i in theOutput.indices) {
                    for (j in checks) {
                        val e = j(theOutput[i])
                        if (e.first) {
                            sum = sum * 10 + e.second
                        }
                    }
                }
            }
            wholeSome += sum
        }
        println(wholeSome)
    }
}