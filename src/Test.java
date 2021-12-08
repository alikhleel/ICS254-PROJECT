import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String basePath = "D:\\ali-kh\\Downloads\\";
        EncryptFileRSA encryptFileRSA = new EncryptFileRSA(basePath + "sample3.txt");
        encryptFileRSA.encrypt();
        // test decryption
        DecryptFileRSA decryptFileRSA = new DecryptFileRSA(basePath + "sample3.rsa");
        decryptFileRSA.decrypt(
                6141467345030015629L, 155341640901324583L);
    }
}
