import java.security.*;
import java.util.*;
import java.io.*;

public class Problem22 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Get MD5 MessageDigest instance
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Convert input to bytes and compute hash
        byte[] digest = md.digest(input.getBytes());

        // Convert hash bytes to hex string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println(sb.toString());
        sc.close();
    }
}
