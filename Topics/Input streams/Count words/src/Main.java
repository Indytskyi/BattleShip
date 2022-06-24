import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int number =  0;
        while (scanner.hasNext()) {
            String s = scanner.next();
            number++;
        }
        System.out.println(number);
        reader.close();
    }
}