import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class D2185 {


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
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int h = sc.nextInt();
            int[] arr = new int[n];
            int[] cpy = new int[n];
            int[] last_mod = new int[n];
            int last_crush_time = 0;
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
                cpy[j] = arr[j];
            }
            for (int j = 0; j < m; j++) {
                int bi = sc.nextInt();
                int ci = sc.nextInt();
                if(last_mod[bi-1] <= last_crush_time){
                    cpy[bi-1] = arr[bi-1];
                }
                last_mod[bi-1] = j+1;
                cpy[bi-1] = cpy[bi-1] + ci;
                if(cpy[bi-1] > h){
                    last_crush_time = j+1;
                }
            }


            for (int j = 0; j < arr.length; j++) {
                if (last_mod[j] <= last_crush_time) {
                    out.print(arr[j]+" ");
                } else {
                    out.print(cpy[j] + " ");
                }
            }
            out.println();
        }
        out.flush();
    }
}