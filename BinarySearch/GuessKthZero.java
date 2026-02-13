import java.util.Scanner;

public class GuessKthZero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int high = sc.nextInt();
        int low = 1;
        int mid;
        sc.nextInt(); //t
        sc.nextLine();
        int target = sc.nextInt(); // Target kth 0


        while(low < high){
            mid = (high + low)/2;
            System.out.println("? 1 "+mid);
            System.out.flush();

            int sum = sc.nextInt(); // Receive from input
            int numOf0 = mid - sum;

            if (numOf0 >= target){
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        System.out.println("! "+ high);
        System.out.flush();
    }
}
