import math #for exponent

#finding class, no of possible addresses and subnet mask
def findclass(ip):
    if 0 <= ip[0] <= 127:
        print("Network Address is: ", ip[0])
        num_ip = 2**24
        print("Number of IP addresses possible: ", num_ip)
        return "A", "255.0.0.0", str(num_ip)
    elif 128 <= ip[0] <= 191:
        ip = [str(i) for i in ip]
        print("Network Address is: ", ".".join(ip[0:2]))
        num_ip = 2 ** 16
        print("Number of IP addresses possible: ", num_ip)
        return "B", "255.255.0.0", str(num_ip)
    elif 192 <= ip[0] <= 223:
        ip = [str(i) for i in ip]
        print("Network Address is: ", ".".join(ip[0:3]))
        num_ip = 2 ** 8
        print("Number of IP addresses possible: ", num_ip)
        return "C", "255.255.255.0", str(num_ip)
    elif 224 <= ip[0] <= 239:
        print("In this class, IP address is not divided into Network and Host ID")
        return "D"
    else:
        print("In this class, IP address is not divided into Network and Host ID")
        return "E"

#function to get each subnet's address, broadcast address and range of ip addresses
def subnetting(ip, num, classname, ip_addresses):
    temp = 0
    if classname == "A":
        place2 = ip_addresses / (256 ** 2)
        for i in range(num):
            print(f"Subnet {i+1} => ")
            print(temp)
            print("Subnet Address: ", ip[0] + '.' + str(temp) + '.0' + '.0')
            temp += int(place2)
            print("Broadcast address: ", ip[0] + '.' + str(temp - 1) + '.255' + '.255')
            print("Valid range of host IP address : ",ip[0] + '.' + str(temp - int(place2)) + '.' + '0' + '.1' + '\t-\t' + ip[0] + '.' + str(temp - 1) + '.254' + '.254')
            print()
    elif classname == "B":
        place2 = ip_addresses / 256
        for i in range(num):
            print(f"\nSubnet {i+1} => ")
            print("Subnet Address: ", ".".join(ip[0:2]) + '.' + str(temp) + '.0')
            temp += int(place2)
            print("Broadcast address: ", ".".join(ip[0:2]) + '.' + str(temp - 1) + '.255')
            print("Valid range of host IP address: ",".".join(ip[0:2]) + '.' + str(temp - int(place2)) + '.1\t-\t' + ".".join(ip[0:2]) + '.' + str(temp - 1) + '.254')
            print()
    elif classname == "C":
        for i in range(num):
            print(f"\nSubnet {i+1} => ")
            print("Subnet Address: ", ".".join(ip[0:3]) + '.' + str(temp))
            temp += int(ip_addresses)
            print("Broadcast address: ", ".".join(ip[0:3]) + '.' + str(temp - 1))
            print("Valid range of host IP address: ",".".join(ip[0:3]) + '.' + str(temp - int(ip_addresses) + 1) + '\t-\t' + ".".join(ip[0:3]) + '.' + str(temp - 2))
            print()

# function to get subnet mask
def subnetmask(num, network_mask):
    var = '1' * int(math.log(num, 2))
    var1 = '0' * (8 - int(math.log(num, 2)))
    binary_num = var + var1
    network_mask = network_mask.split('.')
    network_mask = [i for i in network_mask if i != '0']
    network_mask.append(str(int(binary_num, 2)))
    while len(network_mask) < 5:
        network_mask.append('0')
    print('Subnet Mask – ', ".".join(network_mask[0:4]))


#start
ip = input("Enter the IP address: ")
ip = ip.split(".") #split into octets (4), makes a list
ip = [int(i) for i in ip] #turns octets into integers

lst = findclass(ip) #gets letter and mask of that class
networkClass = lst[0] #letter
print("Given IP address belongs to class: ", networkClass)
ip = [str(i) for i in ip]

if ord(networkClass) < 68:
    network_mask = lst[1] #mask returned in find class
    print('Network Mask: ', network_mask)
    num_subnet = int(input('\nNumber of subnets (power of 2): '))
    num_sub_ip = int(int(lst[2]) / num_subnet)  # no of ip addresses of that class/no of subnets = ip addresses per subnet
    print('Number of bits in subnet id : ', int(math.log(num_subnet, 2)))  # 2^x subnets needs x bits, this prints x using log
    print('Total number of IP addresses possible in each subnet: ', num_sub_ip)
    subnetting(ip, num_subnet, networkClass, num_sub_ip)
    subnetmask(num_subnet, network_mask)
else:
    print("In this Class, there is no network mask")
    print("This class cannot be divided into subnets")