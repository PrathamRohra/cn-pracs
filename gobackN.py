# Define the total number of frames and window size
total_frames = int(input("Enter the Total number of frames: "))
window_size = int(input("Enter the Window Size: "))

sent_frames = []
transmitted_frames = 0
current_frame = 1

while current_frame <= total_frames:
    acknowledged_count = 0

    # Send frames within the current window
    for frame in range(current_frame, current_frame + window_size):
        print(f"Sending Frame {frame}...")
        sent_frames.append(frame)
        transmitted_frames += 1

    # Receive acknowledgments or handle timeouts
    for frame in range(current_frame, current_frame + window_size):
        received = int(input(f"Did you receive frame {frame}?\n1 for yes, 0 for no: "))

        if received == 1:
            print(f"Acknowledgment for Frame {frame}...")
            acknowledged_count += 1
        else:
            print(f"Timeout!! Frame Number: {frame} Not Received")
            print("Retransmitting Window...")
            break

    print()
    current_frame += acknowledged_count

    # Remove acknowledged frames from the sent_frames list
    sent_frames = sent_frames[acknowledged_count:]

print(f"Total number of frames sent and resent: {transmitted_frames}")
