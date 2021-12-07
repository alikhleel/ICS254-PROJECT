import java.io.IOException;

public class EncryptFileRSA {
    private FilesDeal filesDeal;
    private long n;
    private long e;

    public EncryptFileRSA(String filePath) throws IOException {
        filesDeal = new FilesDeal(filePath);
        readKey();
    }

    /**
     * Get the value of n, e attributes from the first line of the file
     */
    private void readKey() {
        String publicKey = filesDeal.getFirstLine();
        String[] tmp = publicKey.split(" ");
        n = Long.parseLong(tmp[0]);
        e = Long.parseLong(tmp[1]);

    }

    private long getLargestBlockSize() {
        // The largest character in ascii is '}' = 125
        int lenOfN = String.valueOf(n).length() - 2;
        StringBuilder block = new StringBuilder("");
        long blockSize = 0;
        while (blockSize < n && block.length() < lenOfN) {
            block.append("125");
            blockSize = Long.parseLong(block.toString());
        }
        return blockSize;
    }

    private String letterToDecimal(char letter) {
        int value = (int) letter;
        return String.format("%03d", value); // add leading zeros
    }

    private String encryptBlock(String block) {
        StringBuilder nBlock = new StringBuilder();
        for (char c : block.toCharArray()) nBlock.append(letterToDecimal(c));
        long blockValue = Long.parseLong(nBlock.toString());
        long result = Math.floorMod(
                Double.doubleToLongBits(Math.pow(blockValue, e)), n);
        return String.valueOf(result);
    }

    /**
     * Encrypt the given file by RSA encryption and store the encryption in the output file
     * with the same location with same input file name with '.rsa' extension
     */
    public void encrypt() throws IOException {
        long largestBlockSize = getLargestBlockSize();
        int numberOfCharacter = String.valueOf(largestBlockSize).length() / 3;
        String block = "";

        while (filesDeal.hasBlock(numberOfCharacter)) {
            block = filesDeal.getBlock(numberOfCharacter);
            String encryptedBlock = encryptBlock(block);
            filesDeal.writeRSABlock(encryptedBlock);
            numberOfCharacter += numberOfCharacter;
        }
        // In case there is a block with size less than "numberOfCharacter"
        block = filesDeal.getReadingBlock();
        String encryptedBlock = encryptBlock(block);
        filesDeal.writeRSABlock(encryptedBlock);
        filesDeal.closeWriting();
        filesDeal.closeReading();

    }

}

