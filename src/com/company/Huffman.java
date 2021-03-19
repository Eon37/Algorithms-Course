package com.company;

import java.util.*;

public class Huffman {
    //TODO: Tree decode
    static Map<Character, String> huffTable = new HashMap<>();

    private abstract static class Node implements Comparable<Node> {
        final int value;
        String code;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(value, node.value);
        }

        public void buildCode(String code) {
            this.code = code;
        }

        public abstract char search(String code);
    }

    private static class InternalNode extends Node  {
        Node left;
        Node right;

        public InternalNode(Node left, Node right) {
            super(left.value + right.value);
            this.left = left;
            this.right = right;
        }

        @Override
        public void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(code + "0");
            right.buildCode(code + "1");
        }

        @Override
        public char search(String code) {
            if (!code.startsWith("0")) {
                return code.length() == 1 ? left.search(code) : left.search(code.substring(1));
            } else {
                return code.length() == 1 ? right.search(code) : right.search(code.substring(1));
            }
        }
    }

    private static class LeafNode extends Node {
        char symbol;

        public LeafNode(char symbol, int value) {
            super(value);
            this.symbol = symbol;
        }

        @Override
        public void buildCode(String code) {
            super.buildCode(code);
            if ("".equals(code)) {
                System.out.println(symbol + ": 0");
                huffTable.put(symbol, "0");
            } else {
                huffTable.put(symbol, code);
            }
        }

        @Override
        public char search(String code) {
            return symbol;
        }
    }

    public static void main(String[] args){
        decode();
    }

    public static void code() {
        Scanner sc = new Scanner(System.in);

        String in = sc.nextLine();

        Map<Character, Integer> freq = new HashMap<>();

        for (var ch: in.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (var v: freq.entrySet()) {
            pq.add(new LeafNode(v.getKey(), v.getValue()));
        }

        while (pq.size() > 1) {
            pq.add(new InternalNode(pq.poll(), pq.poll()));
        }

        pq.peek().buildCode("");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            sb.append(huffTable.get(in.charAt(i)));
        }
        System.out.println(freq.size() + " " + sb.length());
        huffTable.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(sb);
    }

    public static void decode() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int sLength = sc.nextInt();

        Map<String, Character> huffTable1 = new HashMap<>();
        int maxCodeLen = 0;
        for (int i = 0; i < n; i++) {
            char a = sc.next().charAt(0);
            String b = sc.nextLine().substring(1);
            huffTable1.put(b, a);
            if (b.length() > maxCodeLen) maxCodeLen = b.length();
        }

        String in = sc.nextLine();

        int codeLen = 0;
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < in.length(); i++) {
            if (codeLen < maxCodeLen) {
                String tmp = in.substring(i - codeLen, i + 1);
                if (huffTable1.containsKey(tmp)) {
                    chars.add(huffTable1.get(tmp));
                    codeLen = 0;
                } else codeLen ++;
            }  else codeLen = 0;
        }

        chars.forEach(System.out::print);
    }
}
