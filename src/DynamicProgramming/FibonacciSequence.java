package DynamicProgramming;

import java.util.Arrays;

public class FibonacciSequence {

    // Memoization
    public static int fibonacci(int n, int[] dp) {

        if(n<=1) {
            return n;
        }

        if(dp[n]!=-1) {
            return dp[n];
        }

        return dp[n] = fibonacci(n-1, dp) + fibonacci(n-2, dp);

    }


    // Recursion
    public static int fibo(int n) {

        if(n<=1) {
            return n;
        }

        return fibo(n-1) + fibo(n-2);
    }


    // Tabulation
    public static int[] fib(int n) {

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2;i<=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp;
    }

    // Space-optimization
    public static void fibo1(int n) {

        int prev = 1;
        int prev2 = 0;
        int curr = 0;

        for(int i=2;i<=n;i++) {
            curr = prev + prev2;
            prev2=prev;
            prev = curr;
        }

        System.out.println(prev);

    }

    public static void main(String[] args) {

        int n = 5;
        int[] dp = new int[n+1];

        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        int res = 0;

         res = fibonacci(n, dp);

        System.out.println(res);

        res = fibo(n);
        System.out.println(res);

        int[] arr = fib(n);
        for(int ele: arr) {
            System.out.print(ele + " ");
        }

        System.out.println();

        fibo1(n);


    }
}
