package markov


import java.io.File
import java.util.*

private const val SPACE_STRING = " "
private const val EMPTY_STRING = ""
private const val SPACE = ' '

class MarkovChains(private val contiguousSequenceWords: Int, filePath: String) {
    private val dict: MutableMap<String, String?> = HashMap()
    var words: List<String> = listOf()
    var text: String = EMPTY_STRING

    init {
        text = readFile(filePath = filePath)
        words = text.split(SPACE_STRING)
        val maxWords = words.size - contiguousSequenceWords - 1
        var keyString: String
        var end: Int
        for (j in 0 until maxWords) {
            keyString = EMPTY_STRING
            end = j + contiguousSequenceWords
            for (k in j until end) {
                keyString = keyString + words[k] + SPACE_STRING
            }
            keyString = keyString.trim { it <= SPACE }
            var value: String?
            if (dict.containsKey(keyString)) {
                value = dict[keyString]
                value += SPACE_STRING + words[end]
                dict[keyString] = value
            } else {
                if (end <= maxWords) {
                    dict[keyString] = words[end]
                }
            }
        }
    }

    fun genMarkov(numWords: Int): String {
        var buffer = EMPTY_STRING
        var newWord: String
        var keyString: String
        val randomGenerator = Random()
        val lastWords: MutableList<String> = ArrayList()
        val possible = words.size - contiguousSequenceWords
        val start = randomGenerator.nextInt(possible)
        var k = start
        var l = 0
        while (k < start + contiguousSequenceWords) {
            newWord = words[k]
            lastWords.add(l, newWord)
            buffer += "$newWord "
            k++
            l++
        }

        for (i in contiguousSequenceWords until numWords) {
            keyString = EMPTY_STRING
            for (j in 0 until contiguousSequenceWords) {
                keyString = keyString + lastWords[j] + SPACE_STRING
            }
            keyString = keyString.trim { it <= SPACE }
            if (dict.containsKey(keyString)) {
                val possibleWords: List<String> = dict[keyString]?.split(SPACE_STRING) ?: listOf()
                val possibleWordsSize = possibleWords.size
                val rnd = randomGenerator.nextInt(possibleWordsSize)
                val nextWord = possibleWords[rnd]
                buffer += "$nextWord$SPACE"
                for (j in 0 until contiguousSequenceWords - 1) {
                    lastWords[j] = lastWords[j + 1]
                }
                lastWords[contiguousSequenceWords - 1] = nextWord
            }
        }
        return buffer.trim { it <= SPACE }
    }

    private fun readFile(filePath: String) = File(filePath).readText().trimEnd()

}