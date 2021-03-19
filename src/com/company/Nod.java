package com.company;

import java.util.Scanner;

public class Nod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(nod(a, b));
    }

    private static int nod(int a, int b) {
        if (a == b) return a;

        while (a != 0 && b != 0) {
            if (a > b) a %= b;
            else b %= a;
        }

        return a == 0? b : a;
    }
}
