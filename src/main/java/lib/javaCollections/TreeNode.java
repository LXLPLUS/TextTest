package lib.javaCollections;


import java.util.ArrayList;
import java.util.Collection;
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


    static public TreeNode TreeNodeCreate(List<Integer> list) {
        return TreeNodeCreate(list, 0, list.size() - 1, 1);
    }

    static TreeNode TreeNodeCreate(List<Integer> list, int left, int right, int step) {
        if (list.isEmpty() || left > right || left >= list.size() || list.get(left) == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(list.get(left));
        treeNode.left = TreeNodeCreate(list, left + 1, left + step, step << 1);
        treeNode.right = TreeNodeCreate(list, left + step + 1, right, step << 1);
        return treeNode;
    }

    static public List<Integer> TreeNodeToList(TreeNode root) {
        List<Integer> integerList = new ArrayList<>();
        TreeNodeDFS(root, integerList, 0, 1);
        return integerList;
    }

    static void TreeNodeDFS(TreeNode node, List<Integer> list, int startIndex, int step) {
        if (node == null) {
            return;
        }
        while (startIndex >= list.size()) {
            list.add(null);
        }
        list.set(startIndex, node.val);
        TreeNodeDFS(node.left, list, startIndex + 1, step << 1);
        TreeNodeDFS(node.right, list, startIndex + step + 1, step << 1);
    }

    @Override
    public String toString() {
        return TreeNodeToList(this).toString();
    }
}