package Graph.Shortest_Path_Algorithms;

import java.util.*;

public class BellmanFord {

    public static int[] bellmanFord(int V, int[][] edges, int src) {

        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // relaxation of V-1 edges
        for(int i=0;i<V-1;i++)
        {
            for(int j=0;j<edges.length;j++)
            {
                int u = edges[j][0];
                int v = edges[j][1];
                int wt = edges[j][2];

                if( dist[u]+wt < dist[v] && dist[u]!=Integer.MAX_VALUE) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // N-th relaxation to check negative cycle
        for(int j=0;j<edges.length;j++)
        {
            int u = edges[j][0];
            int v = edges[j][1];
            int wt = edges[j][2];

            if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt < dist[v]) {
                int[] temp = new int[1];
                temp[0] = -1;
                return temp;
            }
        }


        for(int i=0;i<dist.length;i++) {
            if(dist[i]==Integer.MAX_VALUE) {
                dist[i] = 100000000;
            }
        }


        return dist;
    }

    public static void main(String[] args) {

       int[][] edges = {{0,1,5}, {1,0,3}, {1,2,-1}, {2,0,1}};
       int src = 2;
       int size = 3;

       int[] res = bellmanFord(size, edges, src);

       for(int ele: res) {
           System.out.println(ele + " ");
       }


    }
}
