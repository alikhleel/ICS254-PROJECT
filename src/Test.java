import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        EncryptFileRSA encryptFileRSA = new EncryptFileRSA("try.txt");
        encryptFileRSA.encrypt();
      // test decryption
        DecryptFileRSA decryptFileRSA = new DecryptFileRSA("try.dec");
        decryptFileRSA.decrypt(new File("try.rsa"),
                6141467345030015629L, 155341640901324583L);
    }
}
