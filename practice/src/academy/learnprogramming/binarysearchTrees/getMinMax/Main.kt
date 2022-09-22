package academy.learnprogramming.binarysearchTrees.getMinMax

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intTree = Tree()
        intTree.insert(25)
        intTree.insert(20)
        intTree.insert(15)
        intTree.insert(27)
        intTree.insert(30)
        intTree.insert(29)
        intTree.insert(26)
        intTree.insert(22)
        intTree.insert(32)
        intTree.insert(17)

//        intTree.traverseInOrder();
//        System.out.println();
//
//        System.out.println(intTree.get(27));
//        System.out.println(intTree.get(17));
//        System.out.println(intTree.get(8888));
        println(intTree.min())
        println(intTree.max())
    }
}