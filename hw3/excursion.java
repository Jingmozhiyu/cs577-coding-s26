import java.io.*;
import java.util.StringTokenizer;

public class excursion {

    static class IO extends PrintWriter {
        public IO(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public IO(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public IO(String fileName) {
            super(new BufferedOutputStream(System.out));
            try {
                r = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                this.println("File Not Found");
            }

        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }


        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }




    public static void main(String[] args) {

        IO io = new IO(System.in, System.out);

        int N = io.getInt(); // Number of intersections (nodes)
        int M = io.getInt(); // Number of streets (edges - undirected)

        int A = io.getInt() - 1; // Intersection (node) where Luka starts
        int B = io.getInt() - 1; // Intersection (node) Luka must get to
        int K = io.getInt(); // Difference in starting times (Luka starts at A exactly K minutes after George)
        int G = io.getInt(); // Number of intersections on George's route

        // Easier to model with adj matrix
        long[][] weights = new long[N][N];
        long[][] times = new long[N][N];

        // Fill weights with infinity
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                weights[i][j] = Integer.MAX_VALUE;
                times[i][j] = Integer.MAX_VALUE;
            }
        }

        // Nodes that george is visiting
        int[] george = new int[G];
        for (int i = 0; i < G; i++) {
            george[i] = io.getInt() - 1;
        }

        // Fill in edge weights
        for (int i = 0; i < M; i++) {
            int a = io.getInt() - 1;
            int b = io.getInt() - 1;
            int w = io.getInt();
            weights[a][b] = w;
            weights[b][a] = w; // undirected
        }

        // Add cumulative time that george occupied
        int time = 0; // Time until edge is occupied
        for (int i = 0; i < G - 1; i++) {
            times[george[i]][george[i + 1]] = time;
            times[george[i + 1]][george[i]] = time; // undirected
            time += weights[george[i]][george[i + 1]]; // weights the same for george
        }

        // Temporary dijkstra variables
        long[] dist = new long[N];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Dijkstra with constraints
        dist[A] = K;
        for (int i = 0; i < N; i++) {
            int min = -1;
            for (int j = 0; j < N; j++) {
                if (!visited[j] && (min == -1 || dist[j] < dist[min])) {
                    min = j;
                }
            }
            visited[min] = true;

            for (int j = 0; j < N; j++) {
                // Check constraints before updating distances
                if (dist[min] < times[min][j]) { // regular dijkstra update
                    if (dist[min] + weights[min][j] < dist[j]) {
                        dist[j] = dist[min] + weights[min][j];
                    }
                } else { // add time that george blocks
                    long blocking;
                    if (dist[min] > weights[min][j] + times[min][j]) {
                        blocking = dist[min];
                    } else {
                        blocking = weights[min][j] + times[min][j];
                    }
                    blocking += weights[min][j]; // add regular weights always
                    if (blocking < dist[j]) {
                        dist[j] = blocking;
                    }
                }
            }
        }

        // started at K so we have to subtract that
        io.println(dist[B] - dist[A]);

        io.close();
    }

}
