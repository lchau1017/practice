package academy.learnprogramming.binarysearchTrees.insertion

class TreeNode(var data: Int) {
    var leftChild: TreeNode? = null
    var rightChild: TreeNode? = null
    fun insert(value: Int) {
        if (value == data) {
            return
        }
        if (value < data) {
            if (leftChild == null) {
                leftChild = TreeNode(value)
            } else {
                leftChild!!.insert(value)
            }
        } else {
            if (rightChild == null) {
                rightChild = TreeNode(value)
            } else {
                rightChild!!.insert(value)
            }
        }
    }
}