package Graph.Shortest_Path_Algorithms;

import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int dis;
    int row;
    int col;

    public Pair(int dis, int row, int col) {
        this.dis=dis;
        this.row=row;
        this.col=col;
    }
}

public class ShortestPathInABinaryMaze {

        public static int shortestPathBinaryMatrix(int[][] grid) {

            int n = grid.length;

            // If start or end cell is blocked, return -1
            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
                return -1;
            }

            int[][] dist = new int[grid.length][grid[0].length];

            for(int i=0;i<dist.length;i++) {
                for(int j=0;j<dist[0].length;j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            // Shortest Path(Dijkstra Algorithm) - using Queue (instead of priority queue)
            Queue<Pair> q = new LinkedList<Pair>();

            dist[0][0] = 0;
            q.offer(new Pair(1,0,0));

            // 8-directions
            int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

            while(!q.isEmpty()) {
                Pair p = q.poll();
                int d = p.dis;
                int r = p.row;
                int c = p.col;

                if (r == n - 1 && c == n - 1) {
                    return d;
                }

                for(int k=0;k<8;k++) {
                    int delrow = r + dRow[k];
                    int delcol = c + dCol[k];

                    if(delrow>=0 && delrow<grid.length && delcol>=0 && delcol<grid[0].length
                            && d+1<dist[delrow][delcol] && grid[delrow][delcol]==0) {

                        dist[delrow][delcol] = d+1;
                        q.offer(new Pair(1+d,delrow, delcol));
                    }
                }
            }

            return -1;
        }

    public static void main(String[] args) {

        int[][] grid = {{0,1}
                     ,{1,0}};

        int res = shortestPathBinaryMatrix(grid);

        System.out.println(res);

    }
}
