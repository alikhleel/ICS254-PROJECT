import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        while (true) {
            try {
                System.out.println("1. Encrypt text");
                System.out.println("2. Decrypt text");
                System.out.println("3. Exit");

                System.out.print(">> ");
                int choice = scanner.nextInt();
                if (choice == 1) encrypt();
                else if (choice == 2) decrypt();
                else System.exit(0);

            } catch (IOException e) {
                System.out.println("File not found.");
                e.printStackTrace();
            }
        }
    }

    private static void decrypt() throws IOException {
        System.out.println("Enter the file path that you want to decrypt: ");
        String file = scanner.next();
        System.out.println("Enter the private key \"n and d \"");
        long n = scanner.nextLong();
        long d = scanner.nextLong();

        DecryptFileRSA decryptFileRSA = new DecryptFileRSA(file);
        decryptFileRSA.decrypt(n, d);
        System.out.println("Alright We are good !");
        System.out.println("You can check your file that is decrypted");
    }

    private static void encrypt() throws IOException {
        System.out.println("Enter the file path that you want to encrypt: ");
        String filePath = scanner.next();
        EncryptFileRSA encryptFileRSA = new EncryptFileRSA(filePath);
        encryptFileRSA.encrypt();
        System.out.println("You can check your file that is encrypted");
    }
}
