import java.util.*;
import java.util.regex.*;
public class Problem29 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        // Regex: <(.+)> matches opening tag, </\1> matches same closing tag
        // ([^<>]+) captures text that is NOT another tag
        String regex = "<(.+)>([^<>]+)</\\1>";
        Pattern pattern = Pattern.compile(regex);

        while (n-- > 0) {
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);

            boolean found = false;
            while (matcher.find()) {
                System.out.println(matcher.group(2)); // print content
                found = true;
            }

            if (!found) {
                System.out.println("None");
            }
        }
    }
}

