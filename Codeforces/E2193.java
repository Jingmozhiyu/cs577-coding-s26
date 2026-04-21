import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E2193 {
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
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] dp = new int[n + 1];
            Arrays.fill(dp, (int) 1e9);
            for (int i = 0; i < n; i++) {
                dp[sc.nextInt()] = 1;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j += i) {
                    dp[j] = Math.min(dp[j], dp[i] + dp[j / i]);
                }
            }
            for (int i=1; i<=n; i++) {
                pw.print(dp[i]==1e9 ? -1 + " " : dp[i] + " ");
            }
            pw.println();
        }
        pw.flush();
    }
}
