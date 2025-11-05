import heapq

class Node:
    def __init__(self, ch, freq):
        self.ch = ch
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.freq < other.freq

def generate_codes(root, code, huffman_codes):
    if root is None:
        return
    if root.left is None and root.right is None:
        # Pad the code to 4 bits (e.g., '1' -> '0001')
        huffman_codes[root.ch] = code.zfill(4)
    generate_codes(root.left, code + "0", huffman_codes)
    generate_codes(root.right, code + "1", huffman_codes)

text = input("Enter a string to encode: ")

# Step 1: Count character frequency
freq = {}
for ch in text:
    freq[ch] = freq.get(ch, 0) + 1

# Step 2: Create a min-heap
heap = []
for ch, f in freq.items():
    heapq.heappush(heap, Node(ch, f))

# Step 3: Build Huffman Tree (Greedy Algorithm)
while len(heap) > 1:
    left = heapq.heappop(heap)
    right = heapq.heappop(heap)
    new_node = Node('-', left.freq + right.freq)
    new_node.left = left
    new_node.right = right
    heapq.heappush(heap, new_node)

# Step 4: Generate Huffman Codes
root = heap[0]
huffman_codes = {}
generate_codes(root, "", huffman_codes)

# Step 5: Display codes and encoded string
print("\nCharacter | Frequency | Huffman Code (4-bit)")
print("--------------------------------------------")
for ch, f in freq.items():
    print(f"    {ch}      |     {f}      |     {huffman_codes[ch]}")

encoded = "".join(huffman_codes[ch] for ch in text)
print("\nEncoded string:", encoded)

#_____________________________________________________________________________________________________
#input-output
"""
Enter a string to encode: neha
Character | Frequency | Huffman Code (4-bit)
--------------------------------------------
    n      |     1      |     0001
    e      |     1      |     0010
    h      |     1      |     0011
    a      |     1      |     0100

Encoded string: 0001001000110100
"""
