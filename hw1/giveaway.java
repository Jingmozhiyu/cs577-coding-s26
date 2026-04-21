import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class giveaway {

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
        // Build Tree
        FastReader sc = new FastReader(System.in);
        List<List<Integer>> G = new ArrayList<>();
        PrintWriter o = new PrintWriter(System.out);
        int n = sc.nextInt();
        int[] timer = new int[]{0};
        int[] in = new int[n+1];    // in[i]: time when node is accessed by dfs
        int[] out = new int[n+1];   // out[i]: the last-visited node's access time inside i's subtree

        long[] res = new long[n+2]; // res[i]: difference array sorted by dfs sequence (in[])

        for (int i = 0; i <= n; i++) {
            G.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            G.get(u).add(v);
            G.get(v).add(u);
        }

        int q = sc.nextInt();
        dfs(1,0,G,timer,in,out);
        for(int i=0; i<q; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int x = sc.nextInt();

            if(in[u] < in[v]){
                res[1] += x;
                res[n+1] -= x;
                res[in[v]] -= x;
                res[out[v]+1] += x;
            }
            else if(in[u] > in[v]){
                res[in[u]] += x;
                res[out[u]+1] -= x;
            }
        }
        for(int i=1; i<=n; i++){
            res[i] = res[i-1] + res[i];
        }
        for(int i=1; i<=n; i++){
            if(i != n){
                o.print(res[in[i]]+" ");
            }
            else{
                o.print(res[in[i]]);
            }
        }
        o.flush();
    }

    public static void dfs(int u, int p, List<List<Integer>> G, int[] timer, int[] in, int[] out){
        timer[0]++;
        in[u] = timer[0];

        for(int v: G.get(u)){
            if(v!=p){
                dfs(v,u,G,timer,in,out);
            }
        }

        out[u] = timer[0];
    }
}
