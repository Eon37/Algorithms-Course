package com.company;

import java.util.*;

public class Doski_Gvozdi {

    private static class Segment implements Comparable<Segment> {
        private int left;
        private int right;

        Segment(int l, int r) {
            left = l;
            right = r;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getLeft() {
            return left;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getRight() {
            return right;
        }

        @Override
        public int compareTo(Segment seg) {
            if (right > seg.right) return 1;
            else if (right < seg.right) return -1;
            else if (left != seg.left) return -1;
            return 0;
        }

        @Override
        public String toString() {
            return left + " - " + right;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeSet<Segment> segments = new TreeSet<>();

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            segments.add(new Segment(sc.nextInt(), sc.nextInt()));
        }

        List<Integer> rez = findDots(segments);
        System.out.println(rez.size());
        rez.forEach(x -> System.out.print(x + " "));
    }

    public static List<Integer> findDots(TreeSet<Segment> segments) {
        List<Integer> rightBorders = new ArrayList<>();
        rightBorders.add(segments.first().right);
        segments.remove(segments.first());

        while (!segments.isEmpty()) {
            Segment first = segments.first();
            if (rightBorders.stream().noneMatch(x -> first.left <= x)) {
                rightBorders.add(first.right);
            }
            segments.remove(first);
        }

        return rightBorders;
    }
}
