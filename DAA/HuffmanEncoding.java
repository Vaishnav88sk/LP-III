//To implement Huffman Encoding for data compression using a greedy Algorithm

import java.util.*;

class Node {
    char ch;
    int freq;
    Node left, right;
    Node(char c, int f) { ch = c; freq = f; }
    Node(Node l, Node r) { ch = '\0'; freq = l.freq + r.freq; left = l; right = r; }
}

class Compare implements Comparator<Node> {
    public int compare(Node a, Node b) { return a.freq - b.freq; }
}

public class HuffmanEncoding {
    static void buildCode(Node root, String s, Map<Character, String> map) {
        if (root == null) return;
        if (root.left == null && root.right == null) map.put(root.ch, s);
        buildCode(root.left, s + "0", map);
        buildCode(root.right, s + "1", map);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {  
            System.out.print("Enter a string: ");
            String text = sc.nextLine();

            // Step 1: Frequency count
            Map<Character, Integer> freq = new HashMap<>();
            for (char c : text.toCharArray())
                if (c != ' ') freq.put(c, freq.getOrDefault(c, 0) + 1);

            System.out.println("\nCharacter frequencies (excluding spaces):");
            for (var e : freq.entrySet())
                System.out.println(e.getKey() + " : " + e.getValue() + " times");

            // Step 2: Build Huffman tree
            PriorityQueue<Node> pq = new PriorityQueue<>(new Compare());
            for (var e : freq.entrySet())
                pq.add(new Node(e.getKey(), e.getValue()));
            while (pq.size() > 1)
                pq.add(new Node(pq.poll(), pq.poll()));
            Node root = pq.peek();

            // Step 3: Generate Huffman codes
            Map<Character, String> code = new HashMap<>();
            buildCode(root, "", code);
            System.out.println("\nHuffman Codes:");
            for (var e : code.entrySet())
                System.out.println(e.getKey() + " : " + e.getValue());

            // Step 4: Encode text
            StringBuilder encoded = new StringBuilder();
            for (char c : text.toCharArray())
                if (c != ' ') encoded.append(code.get(c));

            System.out.println("\nEncoded string (grouped 8 bits):");
            for (int i = 0; i < encoded.length(); i++) {
                System.out.print(encoded.charAt(i));
                if ((i + 1) % 8 == 0) System.out.print(" ");
            }
            System.out.println();
        }
    }
}

//Input-Output Format

/*Character frequencies (excluding spaces):
B : 1 times
H : 1 times
a : 2 times
d : 1 times
e : 2 times
f : 2 times
i : 1 times
l : 1 times
m : 1 times
n : 1 times
r : 1 times
t : 1 times
u : 2 times

Huffman Codes:
B : 1000
H : 1101
a : 1111
d : 0110
e : 000
f : 101
i : 1001
l : 1110
m : 0111
n : 0010
r : 0011
t : 1100
u : 010

Encoded string (grouped 8 bits):
10000101 00111100 11011111 10101010 11010111 11110010 11000011 000000 */
