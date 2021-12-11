import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {

        long n,d;
        String filePath;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path that you want to encrypt: ");
        filePath = scanner.next();
        EncryptFileRSA encryptFileRSA = new EncryptFileRSA(filePath);
        encryptFileRSA.encrypt();


        System.out.println("Enter the file name without any .txt\ni.e test.txt ==> test");
        String file = scanner.next();

        System.out.println("Enter the private key \"n and d \"");
        n = scanner.nextLong();
        d = scanner.nextLong();

        DecryptFileRSA decryptFileRSA = new DecryptFileRSA(file + ".rsa");
        decryptFileRSA.decrypt(n, d);

        System.out.println("Alright We are good !");
        System.out.println("You can check your file that is encrypted and the decrypted ");
    }
}
