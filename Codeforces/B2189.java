import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2189 {
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
        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            long x = sc.nextLong();
            long k = 0;
            long step = 0;
            for(int j=0; j<n; j++){
                long a = sc.nextLong();
                long b = sc.nextLong();
                long c = sc.nextLong();
                k += a*(b-1);
                step = Math.max(step,a*b-c);
            }
            if(k >= x){
                pw.println(0);
            }
            else if(step == 0){
                pw.println(-1);
            }
            else{
                pw.println((x-k+step-1)/step);
            }
        }
        pw.flush();
    }
}
