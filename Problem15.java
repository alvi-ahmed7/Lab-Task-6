import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import static java.lang.System.in;
public class Problem15 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n1 = Integer.parseInt(br.readLine().trim());
        int n2 = Integer.parseInt(br.readLine().trim());
        int n3 = Integer.parseInt(br.readLine().trim());
        int n4 = Integer.parseInt(br.readLine().trim());
        int n5 = Integer.parseInt(br.readLine().trim());

        Prime ob = new Prime();
        ob.checkPrime(n1);
        ob.checkPrime(n1, n2);
        ob.checkPrime(n1, n2, n3);
        ob.checkPrime(n1, n2, n3, n4, n5);

        // Reflection check to ensure no overloading of checkPrime
        Method[] methods = Prime.class.getDeclaredMethods();
        Set<String> set = new HashSet<>();
        boolean overload = false;
        for (Method method : methods) {
            if (set.contains(method.getName())) {
                overload = true;
                break;
            }
            set.add(method.getName());
        }
        if (overload) {
            throw new Exception("Overloading not allowed");
        }
    }
}

class Prime {
    // Single method (no overloading) using varargs
    public void checkPrime(int... nums) {
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            if (isPrime(n)) {
                sb.append(n).append(" ");
            }
        }
        // Print line (blank line if no primes)
        System.out.println(sb.toString());
    }

    // helper method to test primality
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}


