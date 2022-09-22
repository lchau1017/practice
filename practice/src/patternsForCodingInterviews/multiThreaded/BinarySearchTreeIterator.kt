package patternsForCodingInterviews.multiThreaded

import java.util.*

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62ba9052bfabbUnit
object BinarySearchTreeIterator {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode(10)
        root.left = TreeNode(4)
        root.left!!.left = TreeNode(1)
        root.right = TreeNode(15)
        root.right!!.left = TreeNode(14)
        root.right!!.right = TreeNode(19)
        root.right!!.right!!.right = TreeNode(20)
        val itr = BSTIteratorMultiThreaded(root)
        println("hasNext() -> " + itr.hasNext())
        println("next() -> " + itr.next())
        println("next() -> " + itr.next())
        println("hasNext() -> " + itr.hasNext())
        println("next() -> " + itr.next())
        println("next() -> " + itr.next())
        println("next() -> " + itr.next())
        println("next() -> " + itr.next())
        println("next() -> " + itr.next())
        println("hasNext() -> " + itr.hasNext())
    }

    internal class BSTIteratorMultiThreaded(root: TreeNode?) {
        private val stack = Stack<TreeNode>()
        private var t1: Thread? = null

        init {
            traverseLeft(root)
        }

        @Synchronized
        operator fun hasNext(): Boolean {
            checkThread()
            return !stack.isEmpty()
        }

        @Synchronized
        operator fun next(): Int {
            checkThread()
            val tmpNode = stack.pop()
            traverseLeft(tmpNode.right) //Different thread
            return tmpNode.`val` //required node available right away
        }

        //If the previous thread is active, wait before it finishes
        private fun checkThread() {
            if (t1 != null && t1!!.isAlive) {
                try {
                    t1!!.join() //Wait for this thread to die
                } catch (e: InterruptedException) {
                    println(e)
                }
            }
        }

        private fun traverseLeft(node: TreeNode?) {
            //spawn new thread to process the left-subtree
            t1 = Thread {
                var tmp = node
                while (tmp != null) {
                    stack.push(tmp)
                    tmp = tmp.left
                }
            }
            t1!!.start()
        }
    }

    internal class BSTIterator(root: TreeNode?) {
        var stack = Stack<TreeNode>()

        init {
            traverseleft(root)
        }

        operator fun hasNext(): Boolean {
            return !stack.isEmpty()
        }

        //Return the smallest number
        //Thread safe, only one thread is allowed to process next() at any time
        @Synchronized
        operator fun next(): Int {
            val tmpNode = stack.pop()
            traverseleft(tmpNode.right)
            return tmpNode.`val`
        }

        //traverse the left subtree to push all the node on the stack
        private fun traverseleft(node: TreeNode?) {
            var node = node
            while (node != null) {
                stack.push(node)
                node = node.left
            }
        }
    }
}