import java.util.*;

public class IPv4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the IP address (in the format x.x.x.x): ");
        String ipAddress = scanner.nextLine();
        String[] ipParts = ipAddress.split("\\.");

        int[] ip = new int[4];
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(ipParts[i]);
        }

        String networkClass = findClass(ip);
        System.out.println("Given IP address belongs to class: " + networkClass);

        String networkMask = "";
        int numSubnets = 0;
        int numIPAddresses = 0;

        if (networkClass.equals("A") || networkClass.equals("B") || networkClass.equals("C")) {
            networkMask = getNetworkMask(networkClass);
            System.out.println("Network Mask: " + networkMask);
            System.out.print("No. of subnets (power of 2): ");
            numSubnets = scanner.nextInt();
            numIPAddresses = (int) Math.pow(2, 32 - getMaskLength(networkMask)) / numSubnets;
            System.out.println("The no. of bits in the subnet id: " + (int) (Math.log(numSubnets) / Math.log(2)));
            System.out.println("Total no. of IP addresses possible in each subnet: " + numIPAddresses);
            subnetting(ip, numSubnets, networkClass, numIPAddresses);
        } else {
            System.out.println("In this Class, IP address is not divided into Network and Host ID");
        }

        scanner.close();
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

    public static String getNetworkMask(String networkClass) {
        if (networkClass.equals("A")) {
            return "255.0.0.0";
        } else if (networkClass.equals("B")) {
            return "255.255.0.0";
        } else if (networkClass.equals("C")) {
            return "255.255.255.0";
        }
        return "";
    }

     public static int getMaskLength(String networkMask) {
         String[] maskParts = networkMask.split("\\.");
         int length = 0;
         for (String part : maskParts) {
             int octet = Integer.parseInt(part);
             while (octet > 0) {
                 length++;
                 octet >>= 1;
             }
         }
         return length;
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
