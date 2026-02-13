import java.util.Scanner;

public class Hamburgers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String recipe = sc.nextLine();
        int mb = 0;
        int ms = 0;
        int mc = 0;
        for(int i=0; i<recipe.length(); i++){
            if(recipe.charAt(i) == 'B'){
                mb++;
            }
            else if(recipe.charAt(i) == 'S'){
                ms++;
            }
            else if(recipe.charAt(i) == 'C'){
                mc++;
            }
        }
        int nb = sc.nextInt();
        int ns = sc.nextInt();
        int nc = sc.nextInt();
        sc.nextLine();
        int pb = sc.nextInt();
        int ps = sc.nextInt();
        int pc = sc.nextInt();
        sc.nextLine();
        long budget = sc.nextLong();
        long lo = 0;
        long hi = 2000000000100L;
        long ans = 0;
        while(lo <= hi){
            long mid = (lo+hi)/2;
            if(Math.max(0,mb*mid-nb)*pb + Math.max(0,ms*mid-ns)*ps + Math.max(0,mc*mid-nc)*pc <= budget){
                ans = mid;
                lo = mid+1;
            }
            else{
                hi = mid-1;
            }
        }
        System.out.println(ans);
    }
}
