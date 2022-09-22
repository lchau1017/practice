package academy.learnprogramming.binarysearchTrees.traserval

class Tree {
    private var root: TreeNode? = null
    fun insert(value: Int) {
        if (root == null) {
            root = TreeNode(value)
        } else {
            root!!.insert(value)
        }
    }

    fun traverseInOrder() {
        if (root != null) {
            root!!.traverseInOrder()
        }
    }
}