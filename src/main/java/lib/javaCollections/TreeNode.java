package lib.javaCollections;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    /**
     * 层序遍历
     * @param list 输入的集合
     * @return 前序遍历生成的tree
     */
    static public TreeNode TreeNodeCreate(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        TreeNode head = new TreeNode(list.get(0));
        int index = 0;
        Deque<TreeNode> deque = new ArrayDeque<>(List.of(head));
        while (!deque.isEmpty() && index < list.size()) {
            TreeNode treeNode = deque.removeFirst();
            TreeNode left = null;
            TreeNode right = null;
            if (index + 1 < list.size() && list.get(index + 1) != null) {
                left = new TreeNode(list.get(index + 1));
                deque.addLast(left);
            }
            if (index + 2 < list.size() && list.get(index + 2) != null) {
                right = new TreeNode(list.get(index + 2));
                deque.addLast(right);
            }
            treeNode.left = left;
            treeNode.right = right;
            index += 2;
        }
        return head;
    }

    static public List<Integer> treeNodeToList(TreeNode root) {
        List<Integer> integerList = new ArrayList<>();
        if (root == null) {
            return integerList;
        }
        Deque<TreeNode> deque = new ArrayDeque<>(List.of(root));
        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.removeFirst();
            integerList.add(treeNode.left != null ? treeNode.left.val : null);
            integerList.add(treeNode.right != null ? treeNode.right.val : null);
            if (treeNode.left != null) {
                deque.add(treeNode.left);
            }
            if (treeNode.right != null) {
                deque.add(treeNode.right);
            }
        }
        return integerList;
    }

    @Override
    public String toString() {
        return treeNodeToList(this).toString();
    }
}