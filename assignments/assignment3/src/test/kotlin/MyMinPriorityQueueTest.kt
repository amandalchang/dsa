import org.example.MyMinPriorityQueue
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MyMinPriorityQueueTest {

    @Test
    operator fun next() {
        // Testing an empty priority queue
        val empty = MyMinPriorityQueue<String>()
        assert(empty.isEmpty())
        assert(empty.next() == null)
        // adding something and testing that next works
        empty.addWithPriority("Amanda", 1.0)
        assert(empty.next() == "Amanda")
        // now it should be null
        assert(empty.next() == null)
    }

    @Test
    fun adjustPriority() {
        // Tests adding elements in the general case
        val queue = MyMinPriorityQueue<Int>()
        queue.addWithPriority(7, 1.0)
        queue.addWithPriority(9, 2.0)
        queue.addWithPriority(5, 19.0)

        // 5 should now be at the top of the priority queue
        queue.adjustPriority(5, 0.5)
        assertEquals(5, queue.next())
        assertEquals(7, queue.next())
        assertEquals(9, queue.next())
        assertTrue(queue.isEmpty())

    }

    @Test
    fun addWithPriority() {
        // Tests adding elements in the general case
        val queue = MyMinPriorityQueue<Int>()
        queue.addWithPriority(7, 1.0)
        queue.addWithPriority(9, 2.0)
        queue.addWithPriority(5, 19.0)

        // 5 should now be at the top of the priority queue
        assertEquals(7, queue.next())
        assertEquals(9, queue.next())
        assertEquals(5, queue.next())
        assertTrue(queue.isEmpty())

    }
}