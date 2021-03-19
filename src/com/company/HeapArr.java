package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeapArr {

    private static class Heap {
        private final List<Integer> heap = new ArrayList<>();

        public void insert(int x) {
            heap.add(x);
            shiftUp(heap.size() - 1);
        }

        public int extractMax() {
            int tmp = heap.get(0);
            swap(0, heap.size() - 1);
            heap.remove(heap.size() - 1);

            shiftDown(0);
            return tmp;
        }

        private void shiftUp(int index) {
            if (index == 0) return;

            int parentIndex = ((index + 1) >> 1) - 1;

            if (heap.get(parentIndex) < heap.get(index)) {
                swap(index, parentIndex);
                shiftUp(parentIndex);
            }
        }

        private void shiftDown(int index) {

            int leftIndex = ((index + 1) << 1) - 1;

            if (leftIndex >= heap.size()) return;

            if (leftIndex + 1 == heap.size()) {
                if (heap.get(index) < heap.get(leftIndex)) {
                    swap(leftIndex, index);
                }
                return;
            }

            if(heap.get(index) < heap.get(leftIndex) || heap.get(index) < heap.get(leftIndex + 1)) {
                if (heap.get(leftIndex) > heap.get(leftIndex + 1)) {
                    swap(leftIndex, index);
                    shiftDown(leftIndex);
                } else {
                    swap(leftIndex + 1, index);
                    shiftDown(leftIndex + 1);
                }
            }
        }

        private void swap(int index1, int index2) {
            int tmp = heap.get(index1);
            heap.set(index1, heap.get(index2));
            heap.set(index2, tmp);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Heap h = new Heap();

        int n = sc.nextInt();
        sc.nextLine();
        List<String> ins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ins.add(sc.nextLine());
        }

        for (String s: ins) {
            if (s.equals("ExtractMax")) System.out.println(h.extractMax());
            else h.insert(Integer.parseInt(s.substring(7)));
        }
    }
}
