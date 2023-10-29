import java.util.Scanner;

public class crc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data bits: ");
        String data = sc.nextLine();

        System.out.println("Enter divisor bits: ");
        String div = sc.nextLine();

        int dataLen = data.length();
        int divLen = div.length();

        StringBuilder dataWithZero = new StringBuilder(data);
        for(int i = 0; i < divLen - 1; i++) dataWithZero.append("0");

        System.out.println(dataWithZero.toString());

        String rem = mod2div(dataWithZero, dataLen, divLen, div);

        System.out.println("Remainder is: " + rem.substring(dataLen));

        String dataSent = data + rem.substring(dataLen);
        System.out.println(dataSent);

        System.out.println("Enter received data: ");
        String receivedData = sc.nextLine();

        StringBuilder recData = new StringBuilder(receivedData);
        String rem2 = mod2div(recData, dataLen, divLen, div);

        if(rem2.contains("1")){
            System.out.println("Error");
        }
        else{
            System.out.println("No error");
        }
    }
    public static String mod2div(StringBuilder dataWithZero, int dataLen, int divLen, String div){
        StringBuilder rem = new StringBuilder(dataWithZero);

        for(int i = 0; i < dataLen; i++){
            if(rem.charAt(i) == '1'){
                for(int j = 0; j < divLen; j++){
                    rem.setCharAt(i+j, xor(rem.charAt(i+j), div.charAt(j)));
                }
            }
        }
        return rem.toString();
    }

    public static char xor(char a, char b){
        if(a == b) return '0';
        return '1';
    }
}
