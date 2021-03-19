package com.company;

import java.util.*;

public class Bagpack {

    private static class Thing implements Comparable<Thing> {
        private final double value;
        private final double weight;
        private final double ratio;

        Thing (double value, double weight) {
            this.value = value;
            this.weight = weight;
            ratio = value / weight;
        }

        public double getValue() {
            return value;
        }

        public double getWeight() {
            return weight;
        }

        public double getRatio() {
            return ratio;
        }

        @Override
        public int compareTo(Thing other) {
            if (ratio < other.ratio) return 1;
            else if (ratio > other.ratio) return -1;
            else if (value > other.value) return -1;
                 else if (value < other.value) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "v:" + value + "-w:" + weight + "-r:" + ratio;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        List<Thing> things = new ArrayList<>();

        int n = sc.nextInt();
        double W = sc.nextDouble();
        for (int i = 0; i < n; i++) {
            things.add(new Thing(sc.nextDouble(), sc.nextDouble()));
        }

        System.out.printf("%.3f", put(things, W));
    }

    private static double put(List<Thing> list, double W) {
        Collections.sort(list);
        Thing item;

        double value = 0;
        double weight = 0;

        while (!list.isEmpty() && (item = list.get(0)).weight + weight < W) {
            if (item.weight < W - weight) {
                value += item.value;
                weight += item.weight;
            }
            list.remove(0);
        }

        if (!list.isEmpty()) value += list.get(0).ratio * (W - weight);

        return value;
    }
}
