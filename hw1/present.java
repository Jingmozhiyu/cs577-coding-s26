import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class present {
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

        int m = sc.nextInt(); int n = sc.nextInt(); int k = sc.nextInt(); int h = sc.nextInt(); int w = sc.nextInt();
        // Record upper-left & lower-right for each #color
        int[] minRow = new int[k]; int[] minCol = new int[k]; int[] maxRow = new int[k]; int[] maxCol = new int[k];
        Arrays.fill(minRow,Integer.MAX_VALUE);
        Arrays.fill(minCol,Integer.MAX_VALUE);
        Arrays.fill(maxRow,Integer.MIN_VALUE);
        Arrays.fill(maxCol,Integer.MIN_VALUE);

        int totalColor = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int currColor = sc.nextInt();
                minRow[currColor-1] = Math.min(minRow[currColor-1],i);
                minCol[currColor-1] = Math.min(minCol[currColor-1],j);
                maxRow[currColor-1] = Math.max(maxRow[currColor-1],i);
                maxCol[currColor-1] = Math.max(maxCol[currColor-1],j);
            }
        }

        for(int i = 0; i < k; i++) {
            if (minRow[i] != Integer.MAX_VALUE) {
                totalColor++;
            }
        }


        for(int i=0; i<m-h+1; i++){
            for(int j=0; j<n-w+1; j++){
                int deducted = 0;
                for(int idx=0; idx<k; idx++){
                    if (minRow[idx] == Integer.MAX_VALUE) continue;
                    if(i<=minRow[idx] && j<=minCol[idx] && i+h-1 >=maxRow[idx] && j+w-1 >= maxCol[idx]){
                        deducted++;
                    }
                }
                out.print((totalColor - deducted));

                if(j != n - w){
                    out.print(" ");
                } else {
                    out.println();
                }

            }
        }

        out.flush();
    }
}
