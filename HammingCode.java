import java.util.*;

public class HammingCode {
    public static int binToDec(String str) {
        int bin = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1')
                bin += Math.pow(2, i);
        }
        return bin;
    }

    public static int detectError(int[] received, int r) {
        StringBuilder errorWord = new StringBuilder();
        for (int i = 0; i < r; i++) {
            int count = 0;
            for (int j = 1; j < received.length; j++) {
                if (((j >> i) & 1) == 1) {
                    if (received[j] == 1)
                        count++;
                }
            }
            if (count % 2 == 0)
                errorWord.append('0');
            else
                errorWord.append('1');
        }
        System.out.println("Error Word: " + errorWord);
        int errorPosition = binToDec(errorWord.toString());
        return errorPosition;
    }

    public static int[] generateCode(int[] data, int n, int r) {
        int arr[] = new int[n + r + 1];
        int j = 1;
        for (int i = 1; i < arr.length; i++) {
            if ((Math.ceil(Math.log(i) / Math.log(2)) == Math.floor(Math.log(i) / Math.log(2))))
                arr[i] = 0;
            else {
                arr[i] = data[j];
                j++;
            }
        }
        for (int i = 0; i < r; i++) {
            int x = (int) Math.pow(2, i);
            //
            for (j = 1; j < arr.length; j++) {
                if (((j >> i) & 1) == 1) {
                    if (x != j)
                        arr[x] = arr[x] ^ arr[j];
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of data bits:");
        int n = sc.nextInt();
        System.out.println("Enter data bits:");
        int data[] = new int[n + 1];
        for (int i = 1; i <= n; i++)
            data[i] = sc.nextInt();
        

        int r = 0;
        while ((Math.pow(2, r)) < n + r + 1)
            r++;
        int[] encoded = generateCode(data, n, r);
        System.out.println("Generated Code:");
        for (int i = 1; i <= n + r; i++)
            System.out.print(encoded[i] + " ");
        System.out.println("\nEnter received code for error detection and correction:");
        int received[] = new int[n + r + 1];
        for (int i = 1; i <= n + r; i++)
            received[i] = sc.nextInt();
        int errorPosition = detectError(received, r);
        if (errorPosition != -1) {
            System.out.println("Error detected at position: " + errorPosition);
            received[errorPosition] = received[errorPosition] ^ 1;
            System.out.println("Corrected code:");
            for (int i = 1; i <= n + r; i++)
                System.out.print(received[i] + " ");
        } else
            System.out.println("No error detected.");
    }
}
