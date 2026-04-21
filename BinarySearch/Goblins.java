import java.util.Scanner;

public class Goblins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int total = 0;
        sc.nextLine();
        int[] books = new int[N];
        int maxPage = 0;
        for(int i=0; i<N; i++){
            books[i] = sc.nextInt();
            total += books[i];
            maxPage = Math.max(maxPage,books[i]);
        }
        int lo = maxPage;
        int hi = total;
        while (lo<=hi){
            int mid = lo+(hi-lo)/2;
            int cnt = 1;
            int n = 0;
            for(int i=0; i<N; i++){
                if(n + books[i] <= mid) n+=books[i];
                else {cnt++; n=books[i];}
            }
            if(cnt > K) lo = mid+1;
            else hi = mid-1;
        }
        System.out.println(lo);
    }
}
