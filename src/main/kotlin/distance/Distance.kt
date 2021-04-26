package distance

import kotlin.math.min

class Distance {

    fun distanceLevenshtein(text1: String, text2: String): Int {
        var n = text1.length
        var m = text2.length
        var s1 = text1
        var s2 = text2
        if (n > m) {
            n = text2.length
            m = text1.length
            s1 = text2
            s2 = text1
        }
        val currentRow = IntArray(m + 1)
        val previousRow = IntArray(m + 1)
        currentRow.mapIndexed { index, _ -> currentRow[index] = index }
        for (i in 1..m) {
            currentRow.copyInto(previousRow, 0, 0, previousRow.size)
            currentRow[0] = i
            for (j in 1..n) {
                val add = previousRow[j] + 1
                val remove = currentRow[j - 1] + 1
                var change = previousRow[j - 1]
                if (s2[i - 1] != s1[j - 1]) change++
                currentRow[j] = min(min(add, remove), change)
            }
        }
        return currentRow[n]
    }
}