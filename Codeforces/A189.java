import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A189 {
    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int[] dp = new int[n+1];

        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for(int i=1; i<=n; i++){
            if (i >= a && dp[i - a] != Integer.MIN_VALUE) {
                dp[i] = Math.max(dp[i], dp[i - a] + 1);
            }
            if (i >= b && dp[i - b] != Integer.MIN_VALUE) {
                dp[i] = Math.max(dp[i], dp[i - b] + 1);
            }
            if (i >= c && dp[i - c] != Integer.MIN_VALUE) {
                dp[i] = Math.max(dp[i], dp[i - c] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
