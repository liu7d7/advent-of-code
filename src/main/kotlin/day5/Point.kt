package day5

import java.lang.Integer.max
import kotlin.math.absoluteValue
import kotlin.math.min

data class Point(val x: Int, val y: Int, var type: PointType = PointType.none) {
    fun checkLineIntersection(line: Line) {
        if (line.lineType == LineType.straight) {
            if (line.x0 == this.x) {
                if ((line.y0..line.y1).contains(y)) {
                    type = type.next()
                    return
                }
            }
            if (line.y0 == this.y) {
                if ((line.x0..line.x1).contains(x)) {
                    type = type.next()
                    return
                }
            }
            return
        }
        // part 2 only
        if (line.lineType == LineType.diagonal) {
            checkLineIntersectionPart2(line)
        }
    }

    fun checkLineIntersectionPart2(line: Line) {
        if ((line.x0 - this.x).absoluteValue == (line.y0 - this.y).absoluteValue) {
            if ((min(line.x0, line.x1)..max(line.x1, line.x0)).contains(x) && (min(line.y0, line.y1)..max(line.y1, line.y0)).contains(y)) {
                type = type.next()
            }
        }
    }
}

enum class PointType {
    none, one, two_or_more;

    fun next(): PointType {
        return when (this) {
            none -> one
            else -> two_or_more
        }
    }
}
