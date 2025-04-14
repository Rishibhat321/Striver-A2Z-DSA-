package Graph.Shortest_Path_Algorithms;

public class Floyd_Warshall_Algorithm {

    public static void shortestDistance(int[][] mat) {

        int n = mat.length;

        // Initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == -1) {
                    mat[i][j] = Integer.MAX_VALUE;
                }
                if (i == j) {
                    mat[i][j] = 0;
                }
            }
        }

        // Floyd-Warshall Algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }

        //  Replace unreachable values back to -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == Integer.MAX_VALUE) {
                    mat[i][j] = -1;
                }
            }
        }


        // print the resultant shortest path matrix
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {

                System.out.print(mat[i][j] + " ");
            }

            System.out.println();
        }

    }

    public static void main(String[] args) {

        int[][] mat = {{0, 25},
                       {-1, 0}};


        shortestDistance(mat);

    }

}
