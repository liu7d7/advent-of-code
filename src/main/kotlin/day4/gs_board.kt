package day4

import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

class gs_board(line1: IntArray, line2: IntArray, line3: IntArray, line4: IntArray, line5: IntArray) {
    val lines = ArrayList<LinkedHashMap<Int, Boolean>>()

    // GS2 ONLY
    var done = false

    fun putInt(int: Int) {
        for (i in lines) {
            if (i.containsKey(int)) {
                i.replace(int, true)
            }
        }
    }

    fun isComplete(): Int {
        for (i in lines) {
            if (i.values.all { it }) {
                var sum = 0
                for (j in lines.filter { it != i }) {
                    for (k in j.keys) {
                        if (j[k] == false) {
                            sum += k
                        }
                    }
                }
                return sum
            }
        }
        for (i in 0 until 5) {
            var bl = true
            var sum = 0
            for (j in lines) {
                j.values.forEachIndexed { index, b ->
                    if (index == i && !b) {
                        bl = false
                    }
                }
                j.keys.forEachIndexed { idxInside, int1 ->
                    if (idxInside != i && j[int1] == false) {
                        sum += int1
                    }
                }
                if (!bl) {
                    break
                }
            }
            if (bl) {
                return sum
            }
        }
        return -1
    }

    init {
        lines.add(linkedMapOf())
        lines.add(linkedMapOf())
        lines.add(linkedMapOf())
        lines.add(linkedMapOf())
        lines.add(linkedMapOf())
        for (i in line1) {
            lines[0][i] = false
        }
        for (i in line2) {
            lines[1][i] = false
        }
        for (i in line3) {
            lines[2][i] = false
        }
        for (i in line4) {
            lines[3][i] = false
        }
        for (i in line5) {
            lines[4][i] = false
        }
    }
}