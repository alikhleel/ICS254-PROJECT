import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DecryptFileRSA {
    private final String FILE_PATH;
    public DecryptFileRSA(String filePath){
        this.FILE_PATH = filePath;
    }
    private String decryptBlock(long block) {
        StringBuilder nBlock = new StringBuilder();
        while (block != 0) {
            int subBlock = (int) (block % 1000);
            block = block/1000;
            if(subBlock!=0)nBlock.insert(0,Helper.decimalToLetter(subBlock));
        }
        return nBlock.toString();
    }

    /**
     * Decrypt the input file and store the decryption in the same location with same
     * name with extension '.dec'
     */
    public void decrypt(long n, long d) throws IOException {
        Scanner scanner = new Scanner(new File(FILE_PATH));
        FilesDeal filesDeal = new FilesDeal(FILE_PATH);

        while (scanner.hasNext()) {
            long block = scanner.nextLong();
            long dec = Helper.exponentMod(block, d, n);
            filesDeal.writeDECBlock(decryptBlock(dec));
        }
        filesDeal.closeWriting();
        scanner.close();
    }
}
