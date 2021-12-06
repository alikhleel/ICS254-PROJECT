import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DecryptFileRSA {

    static int exponentMod(int A, int B, int C)
    {
        // Base cases
        if (A == 0)
            return 0;
        if (B == 0)
            return 1;

        // If B is even
        long y;
        if (B % 2 == 0) {
            y = exponentMod(A, B / 2, C);
            y = (y * y) % C;
        }

        // If B is odd
        else {
            y = A % C;
            y = (y * exponentMod(A, B - 1, C) % C) % C;
        }

        return (int)((y + C) % C);
    }

    /**
     * Decrypt the input file and store the decryption in the same location with same
     * name with extension '.dec'
     * @param input
     * @param n key
     * @param d the inverse of e
     */
    public static void decrypt(File input, long n, long d) throws IOException {
        Scanner scanner = new Scanner(input);
        FileOutputStream fileOutputStream = new FileOutputStream("decrypted.dec");
        PrintWriter printWriter = new PrintWriter(fileOutputStream);


        long block;
        long dec;
        String s="";
        while (scanner.hasNext()){
            block = scanner.nextLong();
            System.out.println(block);
            dec =exponentMod((int)block,(int)d,(int)n);
            System.out.println(dec);
            s +=dec;
        }

        printWriter.print(s);
        printWriter.close();

    }
}
