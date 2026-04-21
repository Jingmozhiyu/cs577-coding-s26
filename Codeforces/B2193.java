import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;

public class B2193 {
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
        while(t-->0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = sc.nextInt();
            }
            int left = -1;
            int right = -1;
            int swap = -1;
            for(int i=0; i<n; i++){
                if(arr[i] != n-i && left == -1){
                    left = i;
                    swap = n-i;
                }
                if(arr[i] == swap){
                    right = i;
                }
            }
            if(left != -1){
                int[] a = new int[right-left+1];
                int j = a.length-1;
                for(int i=left; i<=right; i++){
                    a[j--] = arr[i];
                }
                int c = 0;
                for(int i=0; i<n; i++){
                    if(i>=left && i<=right){
                        arr[i] = a[c++];
                    }
                }
            }
            for(int k: arr){
                pw.print(k+" ");
            }
            pw.println();
        }
        pw.flush();
    }
}
