import distance.Distance

fun main(args: Array<String>) {
    val d = Distance()
    println(d.distanceLevenshtein("Hello","hi"))
}