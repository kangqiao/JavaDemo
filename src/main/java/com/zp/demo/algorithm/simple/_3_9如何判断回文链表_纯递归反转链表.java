package com.zp.demo.algorithm.simple;

import com.zp.demo.algorithm.model.ListNode;

public class _3_9如何判断回文链表_纯递归反转链表 {

    //返回以s[l]和s[r]为中心的最长回文串
    public String palindrome(String s, int l, int r) {
        while (l >=0 && r <s.length() && s.charAt(l) == s.charAt(r)) {
            System.out.println("l:"+l+", r:"+r+" s[l]:"+s.charAt(l)+"==s[r]:"+s.charAt(l)+":"+(s.charAt(l) == s.charAt(r)));
            //向两边展开
            l--;r++;
        }
        System.out.println(">>>l:"+l+", r:"+r);
        return s.substring(l+1, r-l-1);
    }

    //判断字符串是否是palindrome回文串
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }

    /**
     * 判断回文单链表
     * lg: 1->2->null return false;
     * lg: 1->2->2->1->null return true;
     */
    public boolean isPalindrome(ListNode node) {
        left = node;
        return traverse(node.next);
    }
    private ListNode left;
    private boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        res = res && left.val == right.val;
        left = left.next;
        return res;
    }

    /**
     * 决断回文链表(优化空间复杂度)
     * 奇数: 1->2->3->2->1->null
     * left 1->2->3->2<-1 right
     *               |
     *              null
     * 偶数: 1->2->2->1->null
     * left 1->2->2<-1 right
     *            |
     *           null
     * @param head
     */
    public boolean isPalindrome2(ListNode head) {
        //1. 先通过"双指针技巧"中的快,慢指针来找到链表的中点.
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2. 如果fast指针没有指向null, 说明链表长度为奇数, slow还要再前进一步
        if (fast != null) {
            slow = slow.next;
        }
        //3. 从slow开始反转后面的链表, 现在就可以开始比较回文串.
        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    //反转以slow为起点的链表(1->2->3->null => 3->2->1->null)
    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //递归反转链表.
    private ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 返转链表区间[m, n]的一部分
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //当m==1 时, 就相当于反转链表开头的n(m-n)个元素.
        if (m == 1) return reverseN(head, n);
        //对于head.next来说, 就是反转区间[m-1, n-1]
        //前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }

    //反转以head为起点的n个节点, 返回新的头节点
    // 1<-2<-3   4->5->6>null
    // |         |
    // ----------
    ListNode successor = null; //后驱节点
    ListNode reverseN(ListNode head, int n){
        if (n==1) {
            //记录第n+1个节点, 后面要用
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n-1);
        head.next.next = head;
        //让反转之后的head节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    public static void main(String[] args) {
        _3_9如何判断回文链表_纯递归反转链表 instance = new _3_9如何判断回文链表_纯递归反转链表();
        String str = "abaaba";
        String str2 = "abdcd";
        //System.out.println(instance.palindrome(str, str.length()/2, str.length()/2));
        //System.out.println(instance.palindrome(str2, str2.length()/2, str2.length()/2));
        System.out.println(instance.isPalindrome(str));
        System.out.println(instance.isPalindrome(str2));

        System.out.println("判断是否是回文链表");
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, null))));
        ListNode node2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(1, null))));
        System.out.println(instance.isPalindrome(node));
        System.out.println(instance.isPalindrome(node2));
    }
}
