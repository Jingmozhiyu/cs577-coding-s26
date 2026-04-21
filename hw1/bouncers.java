import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bouncers {
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
        int m = sc.nextInt();
        int[] diff = new int[n+2];
        List<int[]> intervals = new ArrayList<>();
        for(int i=1; i<=m; i++){
            int left = sc.nextInt();
            int right = sc.nextInt();
            intervals.add(new int[]{left,right});
            diff[left] += 1;
            diff[right+1] -= 1;
        }
        int[] prefix1 = new int[n+2]; // prefix1[i]: hours from 1 to i with 1 bouncer guarded
        int[] prefix2 = new int[n+2]; // prefix2[i]: hours from 1 to i with 2 bouncers guarded
        int guardedHours = 0;
        for(int i=1; i<=n; i++){
            diff[i] = diff[i-1] +diff[i]; // diff[i]: k bouncers are guarded at i-th hour
            if(diff[i] != 0){
                guardedHours++;
            }
            if(diff[i] == 1){
                prefix1[i] = prefix1[i-1] + 1;
            }
            else{
                prefix1[i] = prefix1[i-1];
            }
            if(diff[i] == 2){
                prefix2[i] = prefix2[i-1] + 1;
            }
            else{
                prefix2[i] = prefix2[i-1];
            }
        }

        int minLostHours = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            int numOneHourI = prefix1[intervals.get(i)[1]]-prefix1[(intervals.get(i)[0]-1)];
            for(int j=i+1; j<m; j++){
                int numOneHourJ = prefix1[intervals.get(j)[1]]-prefix1[(intervals.get(j)[0]-1)];
                int numTwoHours =
                        Math.max(0,(
                        prefix2[Math.min(intervals.get(i)[1],intervals.get(j)[1])]
                        -
                        prefix2[Math.max(intervals.get(i)[0],intervals.get(j)[0])-1]
                        ));
                minLostHours = Math.min(minLostHours, numOneHourI + numOneHourJ + numTwoHours);

            }
        }

        System.out.println(guardedHours-minLostHours);
    }
}
