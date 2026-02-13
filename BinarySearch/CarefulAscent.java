import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CarefulAscent {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int xdist = Integer.parseInt(st.nextToken());
        int ydist = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double t = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            double factor = Double.parseDouble(st.nextToken());
            t += (end-start)*factor;
            ydist -= (end-start);
        }

        t += ydist;

        System.out.println((double) xdist/t);
        System.out.flush();
    }
}
