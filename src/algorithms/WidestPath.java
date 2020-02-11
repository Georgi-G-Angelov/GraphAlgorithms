package algorithms;



public class WidestPath {
  public static void main(String[] args) {
    int n = 4; // number of nodes
    int[][] pathLength = new int[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            pathLength[j][i] = Integer.MAX_VALUE / 2;
        }
    }
    pathLength[0][0] = 0;
    pathLength[1][0] = 4;
    pathLength[1][1] = 0;
    pathLength[3][1] =-1;
    pathLength[0][2] =-2;
    pathLength[1][2] = 3;
    pathLength[2][2] = 0;
    pathLength[2][3] = 2;
    pathLength[3][3] = 0;

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                if (pathLength[i][j] > pathLength[i][k] + pathLength[k][j]) {
                    pathLength[i][j] = pathLength[i][k] + pathLength[k][j];
                }
            }
        }
    }
    System.out.println("Shortest paths:");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
          System.out.print(pathLength[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();


    // Widest path
      int[][] bandwidth = new int[n][n];
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              pathLength[j][i] = Integer.MIN_VALUE / 2;
          }
      }
      pathLength[0][0] = 0;
      pathLength[1][0] = 4;
      pathLength[1][1] = 0;
      pathLength[3][1] =-1;
      pathLength[0][2] =-2;
      pathLength[1][2] = 3;
      pathLength[2][2] = 0;
      pathLength[2][3] = 2;
      pathLength[3][3] = 0;

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              bandwidth[j][i] = pathLength[j][i];
          }
      }

      for (int k = 0; k < n; k++) {
          for (int i = 0; i < n; i++) {
              for (int j = 0; j <n; j++) {
                  if (bandwidth[i][j] < Math.min(bandwidth[i][k], bandwidth[k][j])) {
                      pathLength[i][j] = pathLength[i][k] + pathLength[k][j];
                      bandwidth[i][j] = Math.min(bandwidth[i][k], bandwidth[k][j]);
                  }
              }
          }
      }
      System.out.println("Widest paths:");
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              System.out.print(pathLength[i][j] + " ");
          }
          System.out.println();
      }
      System.out.println();
      System.out.println("Bandwiths of widest paths:");
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              System.out.print(bandwidth[i][j] + " ");
          }
          System.out.println();
      }
  }
}
