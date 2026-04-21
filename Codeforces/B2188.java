import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2188 {
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
            String s = sc.next();
            int left = -1;
            int right = -1;
            int o = 0;
            for(int j=0; j<n; j++){
                if(s.charAt(j)=='1'){
                    o++;
                    if(left == -1){
                        left = j;
                    }
                    right = j;
                }
            }
            if(o == 0){
                pw.println((int) Math.ceil((double) n/3));
            }
            else {
                int l = (left+1)/3;
                int r = (n-right-1+1)/3;
                int cnt = 0;
                int k = 0;
                for(int j=left;j<=right;j++){
                    if(s.charAt(j) == '0'){
                        cnt++;
                    }
                    else{
                        k += cnt/3;
                        cnt = 0;
                    }
                }
                pw.println(k+l+r+o);
            }
        }
        pw.flush();
    }
}
