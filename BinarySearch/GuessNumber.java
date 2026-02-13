import java.util.Scanner;

class GuessNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int low = 1;
        int high = 1000;
        int mid;
        while (low <= high){
            mid = (low+high)/2;
            System.out.println(mid);
            System.out.flush();
            String status = sc.nextLine();
            if(status.equals("correct")){
                break;
            } else if (status.equals("higher")) {
                low = mid + 1;
            } else if (status.equals("lower")) {
                high = mid -1;
            }
        }
        sc.close();
    }
}