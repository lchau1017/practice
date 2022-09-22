package academy.learnprogramming.binarysearchTrees.getMinMax

class Tree {
    private var root: TreeNode? = null
    fun insert(value: Int) {
        if (root == null) {
            root = TreeNode(value)
        } else {
            root!!.insert(value)
        }
    }

    operator fun get(value: Int): TreeNode? {
        return if (root != null) {
            root!![value]
        } else null
    }

    fun min(): Int {
        return if (root == null) {
            Int.MIN_VALUE
        } else {
            root!!.min()
        }
    }

    fun max(): Int {
        return if (root == null) {
            Int.MAX_VALUE
        } else {
            root!!.max()
        }
    }

    fun traverseInOrder() {
        if (root != null) {
            root!!.traverseInOrder()
        }
    }
}