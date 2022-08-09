package patternsForCodingInterviews.treeDFS;

public class BinaryTreePathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        //System.out.println("Tree has path: " + hasPath(root, 23));
        System.out.println("Tree has path: " + hasPath(root, 16));
    }

    //Recursive
    /*
    Time complexity: O(N), where N is the total number of nodes in the tree.
    Space complexity: O(N), space used to store the recursion stack.
     */
    public static boolean hasPath(TreeNode root, int  sum) {
        if(root == null)
            return false;
        if(root.val == sum && root.left == null && root.right == null)
            return true ;
        return hasPath(root.left, sum - root.val)
                || hasPath(root.right, sum - root.val);
    }


}
