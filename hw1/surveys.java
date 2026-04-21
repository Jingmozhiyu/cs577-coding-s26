import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class surveys {
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
        PrintWriter out = new PrintWriter(System.out);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m+1][n+1];
        for(int i=1; i<=m;i++){
            String s = sc.next();
            for(int j=1; j<=n; j++){
                grid[i][j] = Integer.parseInt(String.valueOf(s.charAt(j-1)));
            }
        }

        int[][] V = new int[m+1][n+1];
        int[][] E_v = new int[m+1][n+1];
        int[][] E_h = new int[m+1][n+1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(grid[i][j] == 1){
                    V[i][j] = V[i-1][j] + V[i][j-1] - V[i-1][j-1] + 1;
                    E_v[i][j] = E_v[i-1][j] + E_v[i][j-1] - E_v[i-1][j-1]
                            + (grid[i-1][j] == 1 ? 1 : 0);
                    E_h[i][j] = E_h[i-1][j] + E_h[i][j-1] - E_h[i-1][j-1]
                            + (grid[i][j-1] == 1 ? 1 : 0);
                }
                else{
                    V[i][j] = V[i-1][j] + V[i][j-1] - V[i-1][j-1];
                    E_v[i][j] = E_v[i-1][j] + E_v[i][j-1] - E_v[i-1][j-1];
                    E_h[i][j] = E_h[i-1][j] + E_h[i][j-1] - E_h[i-1][j-1];
                }
            }
        }

        int q = sc.nextInt();
        for(int i=1; i<=q; i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int v = V[x2][y2] - V[x2][y1-1] - V[x1-1][y2] + V[x1-1][y1-1];
            int a = E_v[x2][y2] - E_v[x2][y1-1] - E_v[x1-1][y2] + E_v[x1-1][y1-1];
            int b = E_h[x2][y2] - E_h[x2][y1-1] - E_h[x1-1][y2] + E_h[x1-1][y1-1];
            int c = E_v[x1][y2] - E_v[x1][y1-1] - E_v[x1-1][y2] + E_v[x1-1][y1-1];
            int d = E_h[x2][y1] - E_h[x2][y1-1] - E_h[x1-1][y1] + E_h[x1-1][y1-1];
            int e = a+b-c-d;
            out.println(v-e);
        }
        out.flush();
    }
}
