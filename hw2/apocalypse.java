import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class apocalypse {
    private static class FastReader {
        InputStream is;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0, ptrbuf = 0;

        public FastReader(InputStream is) {
            this.is = is;
        }

        private int readByte() {
            if (lenbuf == -1) throw new java.util.InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new java.util.InputMismatchException();
                }
                if (lenbuf <= 0) return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b)) ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }

    public static void main(String[] args) {
        FastReader sc = new FastReader(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        int s = (int) Math.pow(2, n);
        int[][] dp = new int[s][2];
        for (int i = 0; i < s; i++) {
            dp[i] = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        dp[0] = new int[]{1, 0};

        for (int mask = 0; mask < s; mask++) {
            if (dp[mask][0] == Integer.MAX_VALUE) continue;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) continue;
                int nextMask = mask | (1 << i);
                if (nextMask >= s) continue;
                if (w[i] <= k - dp[mask][1]) {
                    int currTrip = dp[mask][0];
                    int currWeight = dp[mask][1] + w[i];
                    if (currTrip < dp[nextMask][0]) {
                        dp[nextMask] = new int[]{currTrip, currWeight};
                    } else if (currTrip == dp[nextMask][0]) {
                        if (currWeight < dp[nextMask][1]) {
                            dp[nextMask] = new int[]{currTrip, currWeight};
                        }
                    }
                } else {
                    int currTrip = dp[mask][0] + 1;
                    int currWeight = w[i];
                    if (currTrip < dp[nextMask][0]) {
                        dp[nextMask] = new int[]{currTrip, currWeight};
                    } else if (currTrip == dp[nextMask][0]) {
                        if (currWeight < dp[nextMask][1]) {
                            dp[nextMask] = new int[]{currTrip, currWeight};
                        }
                    }
                }
            }
        }

        System.out.println(dp[s - 1][0]);
    }
}
