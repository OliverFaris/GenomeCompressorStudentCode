/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {
    private static final int[] arr = {'A','C','T','G'};

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        String genome = BinaryStdIn.readString();
        int num = -1;
        BinaryStdOut.write(genome.length());
        for (int i =0; i < genome.length(); i++) {
            switch (genome.charAt(i)) {
                case 'A' -> num =0;
                case 'C' -> num =1;
                case 'T' -> num =2;
                case 'G' -> num =3;
            }
            BinaryStdOut.write(num, 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        int nucleotide = -1;
        int length = BinaryStdIn.readInt();
        int count = 0;
        while (!BinaryStdIn.isEmpty() && count != length) {
            nucleotide = BinaryStdIn.readInt(2);
            BinaryStdOut.write(arr[nucleotide], 8);
            count++;
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}