import java.util.Scanner;

public class ipv4 {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);


        System.out.println("Enter IP Address: (dot notation pileej): ");
        String ipAdd = inp.nextLine();
        //Split into array
        String[] ipAddStr = ipAdd.split("\\.");

        int[] ip = new int[ipAddStr.length];
        int j = 0;
        for(String i: ipAddStr){
            ip[j++] = Integer.parseInt(i);
        }
        String nwClass = findClass(ip);
        System.out.println("This nw belongs to Class: " + nwClass);
        String nwMask = getNwMask(nwClass);
        System.out.println("Nw mask is: " + nwMask);

        System.out.println();
        System.out.println("Enter number of subnets (pow of 2): ");
        int noOfSubnets = inp.nextInt();
        System.out.println("Number of bits to represent the subnets: " + Math.log(noOfSubnets)/Math.log(2));
        int totalIpAdd = (int) Math.pow(2, 32 - getNwMaskLen(nwClass))/noOfSubnets;
        System.out.println("Total number of IP addresses in each subnet: " + totalIpAdd);

        subnetting(ip, noOfSubnets, nwClass, totalIpAdd);
    }
    public static String findClass(int[] ip) {
        if (0 <= ip[0] && ip[0] <= 127) {
            System.out.println("Network Address is: " + ip[0]);
            System.out.println("No. of IP addresses possible: " + (int) Math.pow(2, 24));
            return "A";
        } else if (128 <= ip[0] && ip[0] <= 191) {
            System.out.println("Network Address is: " + ip[0] + "." + ip[1]);
            System.out.println("No. of IP addresses possible: " + (int) Math.pow(2, 16));
            return "B";
        } else if (192 <= ip[0] && ip[0] <= 223) {
            System.out.println("Network Address is: " + ip[0] + "." + ip[1] + "." + ip[2]);
            System.out.println("No. of IP addresses possible: " + (int) Math.pow(2, 8));
            return "C";
        } else if (224 <= ip[0] && ip[0] <= 239) {
            System.out.println("In this Class, IP address is not divided into Network and Host ID");
            return "D";
        } else {
            System.out.println("In this Class, IP address is not divided into Network and Host ID");
            return "E";
        }
    }

    public static String getNwMask(String nwClass){
        if(nwClass.equals("A")) return "255.0.0.0";
        else if(nwClass.equals("B")) return "255.255.0.0";
        else if(nwClass.equals("C")) return "255.255.255.0";
        else{
            return "";
        }
    }

    public static int getNwMaskLen(String nwClass){
        if(nwClass.equals("A")) return 8;
        else if(nwClass.equals("B")) return 16;
        else if(nwClass.equals("C")) return 24;
        else return 0;
    }
    public static void subnetting(int[] ip, int numOfSubnets, String className, int ipAddresses) {
        int temp = 0;
        int place2;
        if (className.equals("A")) {
            place2 = ipAddresses / (256 * 256);
            for (int i = 0; i < numOfSubnets; i++) {
                System.out.println("Subnet " + i + " => ");
                System.out.println(temp);
                System.out.println("Subnet Address: " + ip[0] + "." + temp + ".0.0");
                temp += place2;
                System.out.println("Broadcast address: " + ip[0] + "." + (temp - 1) + ".255.255");
                System.out.println("Valid range of host IP address: " + ip[0] + "." + (temp - place2) + ".0.1 - " + ip[0] + "." + (temp - 1) + ".255.254");
                System.out.println();
            }
        } else if (className.equals("B")) {
            place2 = ipAddresses / 256;
            for (int i = 0; i < numOfSubnets; i++) {
                System.out.println("\nSubnet " + i + " => ");
                System.out.println("Subnet Address: " + ip[0] + "." + ip[1] + "." + temp + ".0");
                temp += place2;
                System.out.println("Broadcast address: " + ip[0] + "." + ip[1] + "." + (temp - 1) + ".255");
                System.out.println("Valid range of host IP address: " + ip[0] + "." + ip[1] + "." + (temp - place2) + ".1 - " + ip[0] + "." + ip[1] + "." + (temp - 1) + ".254");
                System.out.println();
            }
        } else if (className.equals("C")) {
            for (int i = 0; i < numOfSubnets; i++) {
                System.out.println("\nSubnet " + i + " => ");
                System.out.println("Subnet Address: " + ip[0] + "." + ip[1] + "." + ip[2] + "." + temp);
                temp += ipAddresses;
                System.out.println("Broadcast address: " + ip[0] + "." + ip[1] + "." + ip[2] + "." + (temp - 1));
                System.out.println("Valid range of host IP address: " + ip[0] + "." + ip[1] + "." + ip[2] + "." + (temp - ipAddresses + 1) + " - " + ip[0] + "." + ip[1] + "." + ip[2] + "." + (temp - 2));
                System.out.println();
            }
        }
    }
}
