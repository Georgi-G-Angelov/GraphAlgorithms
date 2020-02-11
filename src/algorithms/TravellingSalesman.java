package algorithms;

import java.util.*;

public class TravellingSalesman {
  private static List<List<Integer>> nonEmptySubsets(List<Integer> set) {
    List<List<Integer>> subsets = new ArrayList<>();
    for (int i = 1; i < Math.pow(2, set.size()); i++) {
      List<Integer> current = new ArrayList<>();
      String binary = Integer.toBinaryString(i);
      StringBuilder str = new StringBuilder();
      for (int j = 0; j < set.size() - binary.length(); j++) {
        str.append(0);
      }
      binary = str.toString() + binary;
      //System.out.println("Binary of " + i + "is " + binary);
      for (int j = 0; j < binary.length(); j++) {
        if (binary.charAt(j) == '1') {
          current.add(set.get(j));
        }
      }
      subsets.add(current);
    }
    subsets.sort(Comparator.comparingInt(List::size));
    return subsets;
  }

  private static List<Integer> excluding(List<Integer> set1, List<Integer> set2) {
    List<Integer> newSet = new ArrayList<>();
    for (int n : set1) {
      if (!set2.contains(n)) {
        newSet.add(n);
      }
    }
    return newSet;
  }

  public static void main(String[] args) {
    int n = 4;
    List<Integer> nodes = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      nodes.add(i);
    }
    int start = 1;
    List<Integer> nodesWithoutStart = nodes.subList(1, nodes.size());


    for (List<Integer> subset : nonEmptySubsets(nodesWithoutStart)) {
      for (Integer i : subset) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
    boolean[][] arc = new boolean[n+1][n+1];
    arc[1][1] = true;
    arc[2][1] = true;
    arc[1][2] = true;
    arc[2][3] = true;
    arc[3][2] = true;
    arc[3][4] = true;
    arc[4][3] = true;
    boolean[][] H = new boolean[nonEmptySubsets(nodesWithoutStart).size() + 1][nodes.size() + 1];
    for (int x : nodesWithoutStart) {
      H[0][x] = arc[start][x];
    }
    List<List<Integer>> sets = nonEmptySubsets(nodesWithoutStart);
    for (int i = 0; i < sets.size(); i++) {
      for (int x : excluding(nodesWithoutStart, sets.get(i))) {
        H[i][x] = false;
        for (int y  : sets.get(i)) {
          H[i][x] = false; // wrong
        }
      }
    }

  }
}
