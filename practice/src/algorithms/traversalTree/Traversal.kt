package algorithms.traversalTree

object Traversal {
    @JvmStatic
    fun main(args: Array<String>) {
        val one = TreeNode(1)
        one.left = TreeNode(2)
        one.right = TreeNode(3)
        var res: MutableList<Int> = ArrayList()
        PreOrder.preOrderTraversal(one, res)
        println("\n Post Order Traversal")
        for (i in res) {
            print("$i, ")
        }
        InOrder.inOrderTraversal(one, ArrayList<Int>().also { res = it })
        println("\n In Order Traversal")
        for (i in res) {
            print("$i, ")
        }
        PostOrder.postOrderTraversal(one, ArrayList<Int>().also { res = it })
        println("\n Post-Order Traversal")
        for (i in res) {
            print("$i, ")
        }
    }

    internal object PreOrder {
        /*
        - Visit the root
        - Traverse left subtree
        - Traverse right subtree
         */
        fun preOrderTraversal(
            root: TreeNode?,
            answer: MutableList<Int>
        ) {
            if (root == null) return
            answer.add(root.`val`)
            preOrderTraversal(root.left, answer)
            preOrderTraversal(root.right, answer)
        }
    }

    /*
    - Traverse left subtree
    - Visit root
    - Traverse right subtree
     */
    internal object InOrder {
        fun inOrderTraversal(
            root: TreeNode?,
            answer: MutableList<Int>
        ) {
            if (root == null) return
            inOrderTraversal(root.left, answer)
            answer.add(root.`val`)
            inOrderTraversal(root.right, answer)
        }
    }

    internal object PostOrder {
        /*
        - Traverse left subtree
        - Traverse right subtree
        - visit the node
         */
        fun postOrderTraversal(
            root: TreeNode?,
            answer: MutableList<Int>
        ) {
            if (root == null) return
            postOrderTraversal(root.left, answer)
            postOrderTraversal(root.right, answer)
            answer.add(root.`val`)
        }
    }
}

internal class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}