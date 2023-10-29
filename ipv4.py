def findnet(ip):
    first_octet=ip[0]
    if 0 <= first_octet <= 127:
        return "A", "255.0.0.0"  # Class A IP address
    if 128 <= first_octet <= 191:
        return "B","255.255.0.0" #Class B
    if 192 <= first_octet <=223:
        return "C","255.255.255.0" #Class C
    if first_octet:
        return "Reserved",None

def calc_subnet_bits(netclass):
    if netclass == "A":
        return 24
    if netclass == "B":
        return 16
    if netclass == "C":
        return 8
    if netclass:
        return 0

def subnet_info(ip,nsubnets,netclass):
    subnet_bits = calc_subnet_bits(netclass)
    total_ips_per_subnet = 2 ** (32 - subnet_bits)
    print(f"Total Ips :{total_ips_per_subnet} ")
    for i in range(nsubnets):
        start = i * total_ips_per_subnet
        subnet_address = f"{ip[0]}.{ip[1]}.{ip[2]}.{start}"
        # Calculate the valid IP address range
        valid_range_start = start + 1
        valid_range_end = start + total_ips_per_subnet - 2  # Subtract 2 for network and broadcast addresses
        print(f"Subnet {i + 1}:")
        print(f"   Subnet Address: {subnet_address}")
        print(f"   Valid IP Range: {ip[0]}.{ip[1]}.{ip[2]}.{valid_range_start} - {ip[0]}.{ip[1]}.{ip[2]}.{valid_range_end}")
        print()


def main():
    ip_str = input("Enter IP address (e.g., 192.168.1.0):\n")
    ip_parts = ip_str.split(".")
    ip = []
    for parts in ip_parts:
        ip.append(int(parts))

    netclass,netmask=findnet(ip)
    print(f"This IP belongs to class {netclass}")
    print(f"Default Subnet Address for this Ip address is {netmask}")
    if netclass:
        nsubnets= int(input("Enter the Number of Subnets:\n")) #this should be in power of 2
        subnet_info(ip,nsubnets,netclass)
main()



