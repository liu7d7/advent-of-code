package day5

import AdventOfCodeRunnable
import Main
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

class HydrothermalVenture2: AdventOfCodeRunnable {
    override fun invoke() {
        val sc = Scanner(Main::class.java.getResourceAsStream("hydrothermal_venture1.txt")!!)
        val lines = ArrayList<Line>()
        val points = ArrayList<Point>()
        while (sc.hasNextLine()) {
            val x0: Int
            val x1: Int
            val y0: Int
            val y1: Int
            val arr = sc.nextLine().split(",")
            x0 = arr[0].toInt()
            val str1 = arr[1].split(" -> ")
            y0 = str1[0].toInt()
            x1 = str1[1].toInt()
            y1 = arr[2].toInt()
            if ((x0 == x1 || y0 == y1)) {
                lines += Line(min(x0, x1), min(y0, y1), max(x1, x0), max(y1, y0)).also { it.lineType =
                    LineType.straight
                }
            } else if ((x0 - x1).absoluteValue == (y0 - y1).absoluteValue) {
                lines += Line(x0, y0, x1, y1).also { it.lineType = LineType.diagonal }
            }
        }
        run {
            val minX = lines.minOf { it.x0 }
            val minY = lines.minOf { it.y0 }
            val maxX = lines.maxOf { it.x1 }
            val maxY = lines.maxOf { it.y1 }
            for (i in minX..maxX) {
                for (j in minY..maxY) {
                    points.add(Point(i, j))
                }
            }
        }
        for (i in lines) {
            for (j in points) {
                j.checkLineIntersection(i)
            }
        }
        println(points.sumOf { if (it.type == PointType.two_or_more) 1L else 0L })
    }
}