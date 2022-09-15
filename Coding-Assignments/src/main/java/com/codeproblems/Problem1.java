package com.codeproblems;

import java.util.*;

/***You are given an array of n unique integers a = a[0], a[1], ... , a[n - 1] and an integer value k.
 Find and print the number of pairs (a[i], a[j]) where i < j and a[i] + a[j] = k.
 ***/

public class Problem1 {

    private static int targetedSum;
    private static int matchingPairsCount;
    private static String line;

    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        Scanner stdin = new Scanner(System.in);
        List<Integer> items = new ArrayList<>();

        while (stdin.hasNextLine()) {
            line = stdin.nextLine();
            if (line.isEmpty()) {
                break;
            }
            items.add(Integer.parseInt(line));
        }

        targetedSum = items.get(0);

        for (int i = 1; i < items.size(); i++) {
            if (map.containsKey(targetedSum - items.get(i))) {
                matchingPairsCount++;
            }
            map.put(items.get(i), items.get(i));
        }
        System.out.println(matchingPairsCount);
        stdin.close();
    }
}
