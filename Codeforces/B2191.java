import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2191 {
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
            int zero = 0;
            int one = 0;
            for(int j=0; j<n; j++){
                int k =sc.nextInt();
                if(k == 0){
                    zero++;
                }
                else if(k == 1){
                    one++;
                }
            }
            if(zero == 0){
                pw.println("NO");
            }
            else if(zero == 1){
                pw.println("YES");
            }
            else if(zero >= 2){
                if(one == 0){
                    pw.println("NO");
                }
                else if(one >= 1){
                    pw.println("YES");
                }
            }
        }
        pw.flush();
    }
}
