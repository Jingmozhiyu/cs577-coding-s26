import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A2193 {
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
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            String s = sc.next();
            int ocnt = 0;
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<n; j++){
                if(s.charAt(j) == '0'){
                    ocnt++;
                }
            }
            for(int j=0; j<ocnt; j++){
                if(s.charAt(j) == '1'){
                    list.add(j+1);
                }
            }
            for(int j=ocnt; j<n; j++){
                if(s.charAt(j) == '0'){
                    list.add(j+1);
                }
            }
            if(list.size() == 0) pw.print("Bob");
            else{
                pw.println("Alice");
                pw.println(list.size());
                for(int num :list){
                    pw.print(num+" ");
                }
            }
            pw.println();
        }
        pw.flush();
    }
}
