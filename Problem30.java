import java.io.*;
import java.util.Scanner;

public class Problem30 {
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. */
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String reversed = new StringBuilder(s).reverse().toString();

        if (s.equals(reversed)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
