package lib.javaCollections;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 懒得进行反序列化了，都说不安全了，不如手写一个简单的生成规则，然后在parser的时候进行拦截
 */
public class ListNode{
    public int val = 0;
    public ListNode next;

    ListNode() {};
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    static public ListNode NodeListCreate(Collection<Integer> list) {
        ListNode pre = new ListNode(0);
        ListNode node = pre;
        for (Integer i : list) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return pre.next;
    }

    static public List<Integer> NodeListRead(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }

    @Override
    public String toString() {
        return NodeListRead(this).toString();
    }
}
