package Graph.Shortest_Path_Algorithms;

import java.util.PriorityQueue;

class Pair1{
    int distance;
    int row;
    int col;

    public Pair1(int distance, int row, int col) {
        this.distance=distance;
        this.row=row;
        this.col=col;
    }
}

public class PathWithMinimumEffort {

        public static int minimumEffortPath(int[][] heights) {

            int n = heights.length;   // row
            int m = heights[0].length;   //col

            int[][] dist = new int[n][m];

            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            // Dijkstra Algorithm
            // declare a priority queue
            PriorityQueue<Pair1> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);

            dist[0][0] = 0;
            pq.offer(new Pair1(0, 0,0));

            int[] drow = {-1, 0, 1, 0};
            int[] dcol = {0, 1, 0, -1};

            while(!pq.isEmpty()) {
                Pair1 q = pq.poll();
                int d = q.distance;
                int r = q.row;
                int c = q.col;

                if(r==n-1 && c==m-1) {
                    return d;
                }

                for(int k=0;k<4;k++) {
                    int delrow = r + drow[k];
                    int delcol = c + dcol[k];

                    if(delrow>=0 && delrow<n && delcol>=0 && delcol<m)
                    {
                        int ds = Math.abs(heights[r][c] - heights[delrow][delcol]);
                        int res = Math.max(ds,d);

                        if(res<dist[delrow][delcol])
                        {
                            dist[delrow][delcol] = res;
                            pq.offer(new Pair1(res, delrow, delcol));
                        }
                    }
                }
            }

            return 0;

        }

    public static void main(String[] args) {

            int[][] heights = {{1,2,2},
                              {3,8,2},
                              {5,3,5}};

            int res = minimumEffortPath(heights);

        System.out.println(res);

    }
}
