
import markov.MarkovChains
import kotlin.random.Random

fun main(args: Array<String>) {

    val m = MarkovChains(2,"ru_.txt")
    for (i in 0..10) {
        println(m.genMarkov(Random.nextInt(20)))
    }
}