import java.io.IOException;
import java.io.InputStream;

public class lookmom {
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
        int m = sc.nextInt();
        int n = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        long lo = Integer.MAX_VALUE;
        long hi = Integer.MIN_VALUE;
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] < lo) lo = arr[i][j];
                if (arr[i][j] > hi) hi = arr[i][j];
            }
        }


        int[][] prefix = new int[m + 1][n + 1];     // prefix[i][j]: # >mid from 0,0 to i,j

        while (lo < hi) {
            long mid = (lo + hi) >> 1;

            // Construct prefix matrix
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i - 1][j - 1] <= mid) {
                        prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
                    } else {
                        prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + 1;
                    }
                }
            }


            // Consider each sub-matrix
            boolean valid = true;
            for (int i = 1; i <= m - w + 1; i++) {
                for (int j = 1; j <= n - h + 1; j++) {
                    int cnt = prefix[i + w - 1][j + h - 1] - prefix[i - 1][j + h - 1] - prefix[i + w - 1][j - 1] + prefix[i - 1][j - 1];
                    // cnt: # > mid in sub-matrix.
                    // If cnt > w*h/2, then the medium in sub-matrix is strictly larger than mid. Invalid candidate.
                    if (cnt > w * h / 2) {
                        valid = false;
                        lo = mid + 1;
                        break;
                    }
                }
                if (!valid) break;
            }

            if (valid) {
                hi = mid;
            }
        }

        System.out.println(lo);
    }
}
