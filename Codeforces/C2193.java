import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C2193 {
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
        int t = sc.nextInt();
        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            int q = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for(int j=0; j<n; j++){
                a[j] = sc.nextInt();
            }
            for(int j=0; j<n; j++){
                b[j] = sc.nextInt();
            }
            int[] rightMaxA = new int[n+1];
            int[] rightMaxB = new int[n+1];
            for(int j=n-1; j>=0; j--){
                rightMaxA[j] = Math.max(rightMaxA[j+1],a[j]);
                rightMaxB[j] = Math.max(rightMaxB[j+1],b[j]);
            }
            int[] res = new int[n+1];
            for(int j=1; j<=n; j++){
                res[j] = res[j-1] + Math.max(rightMaxA[j-1],rightMaxB[j-1]);
            }
            for(int j=0; j<q; j++){
                int l = sc.nextInt();
                int r = sc.nextInt();
                if(j != q-1){
                    out.print(res[r]-res[l-1] + " ");
                }
                else{
                    out.println(res[r]-res[l-1]);
                }
            }
        }
        out.flush();
    }
}
