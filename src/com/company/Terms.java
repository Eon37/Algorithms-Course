package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terms {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<Integer> res = findTerms(n);
        System.out.println(res.size());
        res.forEach(x -> System.out.print(x + " "));
    }

    private static List<Integer> findTerms(int n) {
        List<Integer> terms = new ArrayList<>();

        for (int i = 1; n - i >= i + 1; i++) {
            terms.add(i);
            n -= i;
        }

        terms.add(n);
        return terms;
    }
}
