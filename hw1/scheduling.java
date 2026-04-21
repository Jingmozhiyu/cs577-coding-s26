import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class scheduling {
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
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        if(n < 3){
            System.out.println(0);
            return;
        }

        long[] prefixSum = new long[n+1];

        for(int i=1; i<=n; i++){
            prefixSum[i] = prefixSum[i-1] + sc.nextInt();
        }

        if(prefixSum[n] % 3 != 0){
            System.out.println(0);
            return;
        }

        List<Integer> indexLeft = new ArrayList<>();
        long[] prefixRight = new long[n+1];
        for(int i=1; i<=n; i++){
            if(prefixSum[i] == prefixSum[n]/3 && i < n-1){
                indexLeft.add(i);
            }
            if(prefixSum[i] == 2*prefixSum[n]/3 && i < n){
                prefixRight[i] = prefixRight[i-1] + 1;
            }
            else{
                prefixRight[i] = prefixRight[i-1];
            }
        }

        long res = 0;
        for(int i: indexLeft){
            res += (prefixRight[n] - prefixRight[i]);
        }
        System.out.println(res);
    }
}
