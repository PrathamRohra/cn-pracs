import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoBackN {
    public static void main(String[] args) {
        //1, 2, 3, 4, 5
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Total number of frames: ");
        int tf = scanner.nextInt();
        System.out.print("Enter the Window Size: ");
        int N = scanner.nextInt();

        List<Integer> sentFrames = new ArrayList<>();
        int i = 1;
        int tt = 0; //tt (total transmitted frames)

        while (i <= tf) { //i =3
            int z = 0; //z is used to count the number of frames that were successfully acknowledged within the current window.
            for (int k = i; k < i + N && k <= tf; k++) {
                System.out.println("Sending Frame " + k + "...");
                sentFrames.add(k);
                tt++;
            }

            for (int k = i; k < i + N && k <= tf; k++) {
                int f;
                System.out.println(k);
                System.out.println("Did you receive frame " + k + "?");
                System.out.println("1 for yes, 0 for no");
                f = scanner.nextInt();

                if (f == 1) {
                    System.out.println("Acknowledgment for Frame " + k + "...");
                    z++;
                } else {
                    System.out.println("Timeout!! Frame Number : " + k + " Not Received");
                    System.out.println("Retransmitting Window...");
                    break;
                }
            }

            System.out.println();
            i = i + z;

            // Remove acknowledged frames from the sentFrames list
            for (int j = 0; j < z; j++) {
                sentFrames.remove(0);
            }
        }

        System.out.println("Total number of frames which were sent and resent are: " + tt);
    }
}
