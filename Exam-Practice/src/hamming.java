import java.util.Scanner;

public class hamming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of data bits: ");
        int n = sc.nextInt();

        System.out.println("Enter data bits: ");
        int[] data = new int[n + 1];
        for(int i = 1; i <= n; i++){
            data[i] = sc.nextInt();
        }

        int r = 0; //redundant or parity bits.
        while (Math.pow(2, r) < n + r + 1){
            r++;
        }

        int[] encodedData = generateHamCode(data, n, r);
        for(int i = 1; i < n + r + 1; i++){
            System.out.print(encodedData[i] + " ");
        }
    }

    public static int[] generateHamCode(int[] data, int n, int r){
        int[] encodedData = new int[n + r + 1];
        int j = 1;

        for(int i = 1; i < encodedData.length; i++){
            if(Math.ceil(Math.log(i) / Math.log(2)) == Math.floor(Math.log(i) / Math.log(2))){
                encodedData[i] = 0;
            }
            else {
                encodedData[i] = data[j];
                j++;
            }
        }

        for(int i = 0; i < r; i++){
            int x = (int) Math.pow(2, i);

            for(j = 1; j < encodedData.length; j++){
                if(((j >> i) & 1) == 1){
                    if(x != j){
                        encodedData[x] = encodedData[x]^encodedData[j];
                    }
                }
            }
        }

        return encodedData;
    }
}
