package com.zp.demo.algorithm;

import java.util.ArrayList;

public class DeleteEvenByArrayList {

    //不保证原数组奇数有序, 删除所有的偶数.
    public static void deleteEven(ArrayList<Integer> list) {
        if (null == list || list.isEmpty()) return;
        int i = 0;
        int j = list.size() - 1;
        while (i <= j) {
            if (list.get(i) % 2 != 0 ) {
                i++;
                continue;
            }
            if (list.get(j) % 2 == 0) {
                list.remove(j--);
                continue;
            }
            list.set(i++, list.get(j));
            list.remove(j--);
        }
    }

    //保证原数奇数有序, 删除所有的偶数.
    public static void deleteEven2(ArrayList<Integer> list) {
        if (null == list || list.isEmpty()) return;

        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 1) {
                list.set(index++, list.get(i));
            }
        }
        for (int i = list.size() - 1; i >= index; i--) {
            list.remove(i);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);list.add(9);list.add(3);list.add(2);list.add(4);list.add(5);
        list.add(2);list.add(7);
        System.out.println(list);
        delEven3(list);
        System.out.println(list);
    }

    private static void delEven3(ArrayList<Integer> list) {
        if (null == list || list.isEmpty()) return;

        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 1) {
                list.set(n++, list.get(i));
            }
        }

        for (int i = list.size() - 1; i >= n; i--) {
            list.remove(i);
        }
    }















}
