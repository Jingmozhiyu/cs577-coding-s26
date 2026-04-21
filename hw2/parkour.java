import java.io.IOException;
import java.io.InputStream;

public class parkour {

    public static class SegmentTree {
        private long[] tree;
        private int[] idx;
        private long[] lazyAdd;
        private long[] lazySet;
        private boolean[] isSet;
        private int n;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            this.tree = new long[n * 4];
            this.idx = new int[n * 4];
            this.lazyAdd = new long[n * 4];
            this.lazySet = new long[n * 4];
            this.isSet = new boolean[n * 4];

            build(arr, 1, 0, n - 1);
        }

        private void pushUp(int node) {
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
            idx[node] = tree[node * 2] >= tree[node * 2 + 1] ? idx[node * 2] : idx[node * 2 + 1];
        }

        private void pushDown(int node, int l, int r) {
            int leftNode = node * 2;
            int rightNode = node * 2 + 1;

            if (isSet[node]) {
                long val = lazySet[node];

                isSet[leftNode] = true;
                lazySet[leftNode] = val;
                lazyAdd[leftNode] = 0;
                tree[leftNode] = val;

                isSet[rightNode] = true;
                lazySet[rightNode] = val;
                lazyAdd[rightNode] = 0;
                tree[rightNode] = val;

                isSet[node] = false;
                lazySet[node] = 0;
            }

            if (lazyAdd[node] != 0) {
                long val = lazyAdd[node];

                if (isSet[leftNode]) {
                    lazySet[leftNode] += val;
                } else {
                    lazyAdd[leftNode] += val;
                }
                tree[leftNode] += val;

                if (isSet[rightNode]) {
                    lazySet[rightNode] += val;
                } else {
                    lazyAdd[rightNode] += val;
                }
                tree[rightNode] += val;

                lazyAdd[node] = 0;
            }
        }

        private void build(int[] arr, int node, int l, int r) {
            if (l == r) {
                tree[node] = arr[l];
                idx[node] = l;
                return;
            }
            int mid = l + (r - l) / 2;
            build(arr, node * 2, l, mid);
            build(arr, node * 2 + 1, mid + 1, r);
            pushUp(node);
        }

        public void updateSet(int L, int R, int val) {
            updateSet(1, 0, n - 1, L, R, val);
        }

        private void updateSet(int node, int l, int r, int L, int R, int val) {
            if (L <= l && r <= R) {
                isSet[node] = true;
                lazySet[node] = val;
                lazyAdd[node] = 0;
                tree[node] = val;
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            if (L <= mid) updateSet(node * 2, l, mid, L, R, val);
            if (R > mid) updateSet(node * 2 + 1, mid + 1, r, L, R, val);
            pushUp(node);
        }

        public void updateAdd(int L, int R, int val) {
            updateAdd(1, 0, n - 1, L, R, val);
        }

        private void updateAdd(int node, int l, int r, int L, int R, int val) {
            if (L <= l && r <= R) {
                if (isSet[node]) {
                    lazySet[node] += val;
                } else {
                    lazyAdd[node] += val;
                }
                tree[node] += val;
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            if (L <= mid) updateAdd(node * 2, l, mid, L, R, val);
            if (R > mid) updateAdd(node * 2 + 1, mid + 1, r, L, R, val);
            pushUp(node);
        }

        public long[] query(int L, int R) {
            return query(1, 0, n - 1, L, R);
        }

        private long[] query(int node, int l, int r, int L, int R) {
            if (L <= l && r <= R) {
                return new long[]{tree[node], (long) idx[node]};
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            long[] res = new long[]{Long.MIN_VALUE, 0};
            if (L <= mid) {
                long[] q = query(node * 2, l, mid, L, R);
                if (q[0] > res[0]) {
                    res[0] = q[0];
                    res[1] = q[1];
                }
            }
            if (R > mid) {
                long[] q = query(node * 2 + 1, mid + 1, r, L, R);
                if (q[0] > res[0]) {
                    res[0] = q[0];
                    res[1] = q[1];
                }
            }
            return res;
        }
    }

    static long totalMov = 0;

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
        int t = sc.nextInt();
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = sc.nextInt();
        }

        SegmentTree s = new SegmentTree(arr);
        solve(0, t - 1, s,0, Integer.MAX_VALUE);
        System.out.println(totalMov);
    }

    private static void solve(int L, int R, SegmentTree s, int mov, int prev) {
        if (L > R) return;
        long[] q = s.query(L, R);
        int h = (int)q[0];
        int idx = (int) q[1];
        if(prev <= h) return;
        mov++;
        totalMov = Math.max(totalMov,mov);
        if(L == R) return;
        solve(L,idx-1,s,mov,h);
        solve(idx+1,R,s,mov,h);
    }
}
