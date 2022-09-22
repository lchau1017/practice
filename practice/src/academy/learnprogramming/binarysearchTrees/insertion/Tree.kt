package academy.learnprogramming.binarysearchTrees.insertion

class Tree {
    private var root: TreeNode? = null
    fun insert(value: Int) {
        if (root == null) {
            root = TreeNode(value)
        } else {
            root!!.insert(value)
        }
    }
}