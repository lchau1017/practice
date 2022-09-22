package academy.learnprogramming.binarysearchTrees.traserval

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

    fun traverseInOrder() {
        if (leftChild != null) {
            leftChild!!.traverseInOrder()
        }
        print("$data, ")
        if (rightChild != null) {
            rightChild!!.traverseInOrder()
        }
    }
}