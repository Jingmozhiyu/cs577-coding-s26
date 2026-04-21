import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D2193 {
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
            int[] arr = new int[n];
            for(int j=0; j<n; j++){
                arr[j] = sc.nextInt();
            }
            Arrays.sort(arr);
            int[] mos = new int[n];
            for(int j=0; j<n; j++){
                mos[j] = sc.nextInt();
            }
            int ptr = n-1;
            int lvl = 0;
            int curr = mos[lvl];
            long res = 0;
            while(ptr >= 0){
                int cnt = 1;
                while(cnt > 0 && lvl < n){
                    if(cnt < curr){
                        curr -= cnt;
                        cnt = 0;
                    }
                    else{
                        cnt -= curr;
                        lvl++;
                        if(lvl < n) curr = mos[lvl];
                    }
                    res = Math.max(res, (long) arr[ptr] *lvl);
                }
                ptr--;
            }
            out.println(res);
        }
        out.flush();
    }
}
