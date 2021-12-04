import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
      //  EncryptFileRSA fileRSA = new EncryptFileRSA(new File("input.txt"));
      //  fileRSA.encrypt();

        long n = 0;
        long d = 0;
        DecryptFileRSA.decrypt(new File("input.rsa"), n, d);


    }
}
