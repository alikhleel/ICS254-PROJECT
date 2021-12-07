import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        EncryptFileRSA encryptFileRSA = new EncryptFileRSA("try.txt");
        encryptFileRSA.encrypt();
      // test decryption
    }
}
