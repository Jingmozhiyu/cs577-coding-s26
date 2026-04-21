import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    public static int lengthOfLIS(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
        }
        return g.size();
    }

    private static int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (g.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    static class FastReader {
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
        int t = sc.nextInt();
        int[] arr = new int[t];
        for(int i=0; i<t; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(lengthOfLIS(arr));
    }
}
