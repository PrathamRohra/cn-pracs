import java.util.*;
import java.util.Scanner;

public class goback {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total number of frames: ");
        int totalFrames = sc.nextInt();

        System.out.println("Enter window size: ");
        int N = sc.nextInt();

        List<Integer> sentFrames = new ArrayList<>();
        int currFrame = 1;
        int transmittedCount = 0;

        while (currFrame <= totalFrames){
            int ackFrameCount = 0;
            for(int i = currFrame; i < currFrame + N && i <= totalFrames; i++){
                System.out.println("Sending Frame: " + i);
                sentFrames.add(i);
                transmittedCount++;
            }

            for (int i  = currFrame; i < currFrame + N && i <= totalFrames; i++){
                System.out.println("Frame: " + i);
                System.out.println("Did you receive frame: " + i +  " \n Type 1 or 0"); //ack
                int ack = sc.nextInt();

                if(ack == 1){
                    System.out.println("Acknowledgement for frame " + i + " received");
                    ackFrameCount++;
                }
                else {
                    System.out.println("Ack for frame " + i + " not received");
                    System.out.println("Retransmitting window");
                    break;
                }
            }

            currFrame += ackFrameCount;

            for(int i = 0; i < ackFrameCount; i++){
                sentFrames.remove(0);
            }
        }

        System.out.println("Total number of frames transferred are: " + transmittedCount);
    }
}
