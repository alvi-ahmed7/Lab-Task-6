import java.security.*;
import java.util.*;

public class Problem23 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Get SHA-256 MessageDigest instance
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Compute hash
        byte[] digest = md.digest(input.getBytes());

        // Convert hash bytes to hex string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println(sb.toString());
    }
}
