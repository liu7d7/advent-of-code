package day5

data class Line(val x0: Int, val y0: Int, val x1: Int, val y1: Int) {
    // part 2 only
    lateinit var lineType: LineType
}

enum class LineType {
    straight, diagonal
}
