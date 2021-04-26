package distance

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class LevensteinDistanceTest {
    @Test
    fun `general Levenstein test`() {
        val d = Distance()
        assertEquals(d.distanceLevenshtein("abc", "abc"), 0)
        assertEquals(d.distanceLevenshtein("I love programming", "I love coding!"), 8)
        assertEquals(d.distanceLevenshtein("C++", "C#"), 2)
        assertEquals(d.distanceLevenshtein("12345 6", "123456 "), 2)
        assertEquals(d.distanceLevenshtein("ABC", "ABE"), 1)
    }
}