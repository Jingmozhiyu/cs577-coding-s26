import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class F2193 {
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

//    public static void main(String[] args) {
//        FastScanner sc = new FastScanner();
//        PrintWriter pw = new PrintWriter(System.out);
//        int t = sc.nextInt();
//        for(int i=0; i<t; i++){
//            int n = sc.nextInt();
//            int ax = sc.nextInt();
//            int ay = sc.nextInt();
//            int bx = sc.nextInt();
//            int by = sc.nextInt();
//            HashSet<Integer> set = new HashSet<>();
//            int[][] arr = new int[n][2];
//            for(int j=0; j<n; j++){
//                int x = sc.nextInt();
//                set.add(x);
//                arr[j][0] = x;
//            }
//            for(int j=0; j<n; j++){
//                arr[j][1] = sc.nextInt();
//            }
//            Arrays.sort(arr,(a,b)->Integer.compare(a[0],b[0]));
//            int num_cols = set.size();
//            int[][][] nums = new int[num_cols][2][2];
//            long[][] dp = new long[num_cols][2];
//            set.clear();
//
//            int prev = -1;
//            int cnt = -1;
//            for(int[] a: arr){
//                if(a[0] != prev){
//                    prev = a[0];
//                    cnt++;
//                    nums[cnt][0] = new int[]{a[0], a[1]};
//                    nums[cnt][1] = new int[]{a[0], a[1]};
//                }
//                else{
//                    nums[cnt][0][1] = Math.min(nums[cnt][0][1],a[1]);
//                    nums[cnt][1][1] = Math.max(nums[cnt][1][1],a[1]);
//                }
//            }
//            long v_dist_0 = Math.abs(nums[0][0][1] - nums[0][1][1]);
//            dp[0][0] = Math.abs(nums[0][1][1] - ay) + v_dist_0;
//            dp[0][1] = Math.abs(nums[0][0][1] - ay) + v_dist_0;
//            for(int j=1; j<num_cols; j++){
//                long v_dist = Math.abs((long)nums[j][0][1] - nums[j][1][1]);
//                dp[j][0] = Math.min(dp[j-1][0] + Math.abs((long)nums[j][1][1]-nums[j-1][0][1]),
//                        dp[j-1][1] + Math.abs((long)nums[j][1][1]-nums[j-1][1][1])) + v_dist;
//                dp[j][1] = Math.min(dp[j-1][0] + Math.abs((long)nums[j][0][1]-nums[j-1][0][1]),
//                        dp[j-1][1] + Math.abs((long)nums[j][0][1]-nums[j-1][1][1])) + v_dist;
//            }
//            long dist = (long)bx-ax + Math.min(dp[num_cols-1][0]+Math.abs((long)by-nums[num_cols-1][0][1]),
//                    dp[num_cols-1][1]+Math.abs((long)by-nums[num_cols-1][1][1]));
//            pw.println(dist);
//        }
//        pw.flush();
//    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int ax = sc.nextInt(), ay = sc.nextInt();
            int bx = sc.nextInt(), by = sc.nextInt();

            int[] xCoords = new int[n];
            for (int i = 0; i < n; i++) xCoords[i] = sc.nextInt();

            // 核心 1：利用 TreeMap 完成 排序 + 分组 + 极值提取
            // Key: X 坐标, Value: long[]{minY, maxY}
            TreeMap<Integer, long[]> columns = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                int x = xCoords[i];
                long y = sc.nextInt();
                if (!columns.containsKey(x)) {
                    columns.put(x, new long[]{y, y});
                } else {
                    long[] bounds = columns.get(x);
                    bounds[0] = Math.min(bounds[0], y);
                    bounds[1] = Math.max(bounds[1], y);
                }
            }

            // 核心 2：滚动变量取代 DP 数组
            // 初始状态：我们将起点 (Ax, Ay) 视为 "第 0 列"
            long prevMinY = ay;
            long prevMaxY = ay;
            long dp0 = 0; // 停留在上一列 minY 的最小代价
            long dp1 = 0; // 停留在上一列 maxY 的最小代价

            for (Map.Entry<Integer, long[]> entry : columns.entrySet()) {
                long currMinY = entry.getValue()[0];
                long currMaxY = entry.getValue()[1];
                long walk = currMaxY - currMinY; // 当前列必须遍历的距离

                // 状态转移：要停在当前列的 minY，必须先瞄准当前列的 maxY，再走完 walk
                long nextDp0 = Math.min(
                        dp0 + Math.abs(currMaxY - prevMinY),
                        dp1 + Math.abs(currMaxY - prevMaxY)
                ) + walk;

                // 状态转移：要停在当前列的 maxY，必须先瞄准当前列的 minY，再走完 walk
                long nextDp1 = Math.min(
                        dp0 + Math.abs(currMinY - prevMinY),
                        dp1 + Math.abs(currMinY - prevMaxY)
                ) + walk;

                // 滚动更新
                dp0 = nextDp0;
                dp1 = nextDp1;
                prevMinY = currMinY;
                prevMaxY = currMaxY;
            }

            // 最终计算：加上 Y 轴前往终点的距离，以及 X 轴上必然跨越的常量距离
            long finalYDist = Math.min(
                    dp0 + Math.abs(by - prevMinY),
                    dp1 + Math.abs(by - prevMaxY)
            );

            long totalDist = (bx - ax) + finalYDist;
            pw.println(totalDist);
        }
        pw.flush();
    }
}
