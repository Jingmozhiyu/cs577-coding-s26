import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class walk {
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
        int n = sc.nextInt();
        long k = sc.nextLong();
        List<long[]> intervals = new ArrayList<>(); // intervals.(i):i+1st heat's bound
        for(int i=1; i<=n; i++){
            long left = sc.nextInt();
            long right = sc.nextInt();
            intervals.add(new long[]{left,right});
        }

        long[] coldness = new long[n]; // coldness[i]: from 1st heat's start to i+1st heat's end's coldness
        long[] warmness = new long[n+1]; // warmness[i]: from start to ist heat's end's warmness
        for(int i=1; i<n; i++){
            coldness[i] = coldness[i-1] + (intervals.get(i)[0] - intervals.get(i-1)[1]);
        }
        for(int i=1; i<=n; i++){
            warmness[i] = warmness[i-1] + (intervals.get(i-1)[1] - intervals.get(i-1)[0]);
        }

        int left = 0;
        long currCold = 0;
        long maxWalk = 0;
        for (int right=0; right<n; right++){
            currCold = coldness[right] - coldness[left];
            while(currCold >= k){
                currCold -= (coldness[left+1] - coldness[left]);
                left++;
            }
            maxWalk = Math.max(maxWalk,k+warmness[right+1]-warmness[left]);
        }
        System.out.println(maxWalk);
    }
}
