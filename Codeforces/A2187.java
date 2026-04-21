import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A2187 {
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
        while (t-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];
            for(int i=0; i<n; i++){
                a[i] = sc.nextInt();
            }
            int[] b = Arrays.copyOf(a,n);
            Arrays.sort(b);

            int min = b[0];
            int max = b[n-1];
            int k = Integer.MAX_VALUE;
            for(int i=0; i<n; i++){
                if(a[i] != b[i]){
                    k = Math.min(k,Math.max(max-a[i],a[i]-min));
                }
            }
            if(k == Integer.MAX_VALUE) pw.println(-1);
            else pw.println(k);
        }
        pw.flush();
    }
}
