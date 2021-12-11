import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DecryptFileRSA {
    private final String FILE_PATH;

    public DecryptFileRSA(String filePath) {
        this.FILE_PATH = filePath;
    }

    private String decryptBlock(long block) {
        StringBuilder nBlock = new StringBuilder();
        while (block != 0) {
            int subBlock = (int) (block % 1000);
            block = block / 1000;
            if (subBlock != 0) nBlock.insert(0, Helper.decimalToLetter(subBlock));
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
        String strN = String.valueOf(n);
        int sizeN = strN.length() - 1;
        int firstDigitOfN = Integer.parseInt(strN.substring(0, 1));
        while (scanner.hasNext()) {
            String stringBlock = scanner.findInLine(".{" + sizeN + "}");
            for (int i = 0; i <= sizeN; i++)
                if (Integer.parseInt(stringBlock.substring(i, i + 1)) < Integer.parseInt(strN.substring(i, i + 1))) {
                    stringBlock += scanner.findInLine("\\d");
                    break;
                }
            long block = Long.parseLong(stringBlock);
            long dec = Helper.exponentMod(block, d, n);
            filesDeal.writeDECBlock(decryptBlock(dec));
        }
        filesDeal.closeWriting();
        scanner.close();
    }
}
