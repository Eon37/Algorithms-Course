package com.company;

import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int k = sc.nextInt();
        int[] nums = new int[k];
        for (int i = 0; i < k; i++) {
            nums[i] = sc.nextInt();
        }

        for (int num: nums) {
            System.out.print((binarySearch(arr, num) + 1) + " ");
        }

    }

    private static int binarySearch(int[] arr, int num) {
        int l = 0, r = arr.length;

        while (l < r) {
            int m = l + ((r - l)>>1);

            if (arr[m] == num) return m;
            else if (num > arr[m]) l = m + 1;
            else r = m;
        }

        return -2;
    }
}
