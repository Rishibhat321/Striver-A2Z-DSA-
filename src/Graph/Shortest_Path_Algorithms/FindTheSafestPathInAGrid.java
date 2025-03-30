package Graph.Shortest_Path_Algorithms;

import java.util.*;
import java.util.ArrayList;

class Pair2{
    int distance;
    int row;
    int col;

    public Pair2(int distance, int row, int col) {
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}

public class FindTheSafestPathInAGrid {

        public static int maximumSafenessFactor(List<List<Integer>> grid) {

            // converted list to 2d matrix due to traversal of neighbours
            int n = grid.size();
            int[][] mat = new int[n][n];

            int j=0;

            for(int i=0;i<grid.size();i++) {
                j=0;
                for(int ele: grid.get(i)) {
                    mat[i][j] = ele;
                    j++;
                }
            }

/*
        if(mat[0][0]==1 || mat[n-1][n-1] == 1) {
            return 0;
        }                                                   */


            int[][] dist = new int[n][n];

            for(int i=0;i<n;i++) {
                for(int k=0;k<n;k++) {
                    dist[i][k] = Integer.MAX_VALUE;
                }
            }

            // multi-source bfs (flood algorithm)
            // create a queue
            Queue<Pair2> q = new LinkedList<>();

            for(int i=0;i<mat.length;i++) {
                for(int k=0;k<mat[0].length;k++) {
                    if(mat[i][k] == 1) {
                        q.offer(new Pair2(0, i,k));
                        dist[i][k] = 0;
                    }
                }
            }

            int[] drow = {-1, 0, 1, 0};
            int[] dcol = {0, 1, 0, -1};


            while(!q.isEmpty()) {
                Pair2 p = q.poll();
                int dis = p.distance;
                int r = p.row;
                int c = p.col;

                for(int k=0;k<4;k++) {
                    int delrow = drow[k] + r;
                    int delcol = dcol[k] + c;

                    if(delrow>=0 && delrow<n && delcol>=0 && delcol<n) {
                        if(dist[delrow][delcol]> dis+1) {
                            dist[delrow][delcol] = dis+1;
                            q.offer(new Pair2(dis+1, delrow, delcol));
                        }
                    }
                }
            }


            // dijkstra
            // create a max-heap priority queue
            PriorityQueue<Pair2> pq = new PriorityQueue<>((a, b) ->  b.distance - a.distance);

            pq.offer(new Pair2(dist[0][0], 0,0));

            // create a visited array
            boolean[][] visited = new boolean[n][n];

            visited[0][0] =true;

            while(!pq.isEmpty()) {
                Pair2 p = pq.poll();
                int d = p.distance;
                int r = p.row;
                int c = p.col;

                if(r==n-1 && c==n-1) {
                    return d;
                }

                for(int k=0;k<4;k++) {
                    int delrow = r + drow[k];
                    int delcol = c + dcol[k];

                    if(delrow>=0 && delrow<n && delcol>=0 && delcol<n && !visited[delrow][delcol]) {
                        int safe = Math.min(d, dist[delrow][delcol]);
                        pq.offer(new Pair2(safe, delrow, delcol));
                        visited[delrow][delcol] = true;
                    }
                }
            }

            return 0;

        }

    public static void main(String[] args) {

            List<List<Integer>> grid = new ArrayList<>();
            for(int i=0;i<3;i++)
            {
                grid.add(new ArrayList<Integer>());
            }

            grid.get(0).add(1);
            grid.get(0).add(0);
            grid.get(0).add(0);
            grid.get(1).add(0);
            grid.get(1).add(0);
            grid.get(1).add(0);
            grid.get(2).add(0);
            grid.get(2).add(0);
            grid.get(2).add(1);

            int res = maximumSafenessFactor(grid);

             System.out.println(res);



    }

}
