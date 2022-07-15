/* *****************************************************************************
 * Name:
 * NetID:
 * Precept:
 *
 * Description: The code creates a data type that simulates the operation of
 * a LFSR, and is used to make pseudo random numbers.
 **************************************************************************** */

public class LFSR {

    private String lfsr1;

    private int tap1;

    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        lfsr1 = seed;
        tap1 = tap;
    }

    // returns the number of bits in this LFSR
    public int length() {
        int length = 0;
        String fake = lfsr1;
        fake = fake + 'z';

        char ch = 'a';

        while (ch != 'z') {
            ch = fake.charAt(length);
            length = length + 1;
        }

        length = length - 1;
        return length;
    }


    // returns the ith bit of this LFSR (as 0 or 1)
    public int bitAt(int i) {
        char bit = lfsr1.charAt(i);
        int correctform = Character.getNumericValue(bit);
        return correctform;
    }

    // returns a string representation of this LFSR
    public String toString() {
        return lfsr1;
    }

    // simulates one step of this LFSR and returns the new bit (as 0 or 1)
    public int step() {
        // Decides the first digit in the step process
        int j = 0;

        int first = bitAt(j);
        int next = bitAt(length() - tap1);

        int sum = first + next;
        int value = sum % 2;


        if (lfsr1.startsWith("0")) {
            lfsr1 = lfsr1.replaceFirst("0", "");
        }
        else {
            lfsr1 = lfsr1.replaceFirst("1", "");
        }

        lfsr1 = lfsr1 + value;

        return value;
    }

    // simulates k steps of this LFSR and returns the k bits as a k-bit integer
    public int generate(int k) {
        String basetwo = "";

        for (int i = 0; i < k; i++) {
            int midstep = step();
            basetwo = basetwo + midstep;
        }

        int baseten = Integer.parseInt(basetwo, 2);

        return baseten;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {

        LFSR lfsr1 = new LFSR("01101000010", 9);

        StdOut.println(lfsr1.length());
        StdOut.println(lfsr1.bitAt(0));
        StdOut.println(lfsr1.toString());

        StdOut.println(lfsr1);
        for (int i = 0; i < 10; i++) {
            int newdigit = lfsr1.step();
            StdOut.println(lfsr1 + " " + newdigit);
        }


        LFSR lfsr2 = new LFSR("01101000010", 9);
        StdOut.println(lfsr2);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }


    }

}
