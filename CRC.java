import java.util.Scanner;

public class CRC {
    public static String mod2div(String key, String dataWord, StringBuilder dataWithZeros) {
        StringBuilder remainder = new StringBuilder(dataWithZeros);
        for (int i = 0; i < dataWord.length(); i++) {
            if (remainder.charAt(i) == '1') {
                for (int j = 0; j < key.length(); j++) {
                    remainder.setCharAt(i + j, xor(remainder.charAt(i + j), key.charAt(j)));
                }
            }
        }
        return remainder.toString(); //000000101
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data string");
        String dataWord = sc.nextLine(); //data
        System.out.println("Enter key string");
        String key = sc.nextLine(); //divisor
        int keyLength = key.length(); //L
        int dataLength = dataWord.length();
        StringBuilder dataWithZeros = new StringBuilder(dataWord);
        for (int i = 0; i < keyLength - 1; i++) {
            dataWithZeros.append('0'); //L-1 zeroes
        }

        String s = mod2div(key, dataWord, dataWithZeros); //remainder
        System.out.println("Remainder is " + s.substring(dataLength));
        StringBuilder finalData = new StringBuilder(dataWord);
        finalData.append(s.substring(dataLength));
        System.out.println("Encoded data transmitted is " + finalData.toString());

        int[] array = new int[dataWithZeros.length()];
        System.out.println("Enter data received ");
        for (int i = 0; i < dataWithZeros.length(); i++) {
            array[i] = sc.nextInt();
        }

        StringBuilder st = new StringBuilder();
        for (int i = 0; i < dataWithZeros.length(); i++) {
            st.append(array[i]);
        }

        String ans = mod2div(key, dataWord, st); //remainder

        if (ans.contains("1")) {
            System.out.println("Error in data sending ");
        } else {
            System.out.println("Final remainder: " + ans.substring(dataLength));
            System.out.println("No errors in data sending ");
        }
    }

    public static char xor(char a, char b) {
        return (a == b) ? '0' : '1';
    }
}
