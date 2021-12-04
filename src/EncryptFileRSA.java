import java.io.EOFException;
import java.io.File;

public class EncryptFileRSA {
    private FilesDeal filesDeal;
    private long n;
    private long e;

    public EncryptFileRSA(String filePath) {
        filesDeal = new FilesDeal(filePath);
        readKey();
    }

    /**
     * Get the value of n, e attributes from the first line of the file
     */
    private void readKey() {
        String publicKey = "6141467345030015629 1227727"; // public key will be read from file
        // Todo: publicKey = filesDeal.getFirstLine()
        String[] tmp = publicKey.split(" ");
        n = Long.parseLong(tmp[0]);
        e = Long.parseLong(tmp[1]);

    }

    private long getLargestBlockSize() {
        // The largest character in ascii is '}' = 125
        StringBuilder block = new StringBuilder("125");
        long blockSize = Long.parseLong(block.toString());
        while (blockSize < n)
            block.append("125");
        return blockSize;
    }

    private String letterToDecimal(char letter) {
        int value = (int)letter;
        return String.format("%03d",value); // add leading zeros
    }

    private String encryptBlock(String block) {
        StringBuilder nBlock = new StringBuilder();
        for(char c :block.toCharArray()) nBlock.append(letterToDecimal(c));
        long blockValue = Long.parseLong(nBlock.toString());
        long result = Math.floorMod(
                Double.doubleToLongBits(Math.pow(blockValue, e)), n);
        return String.valueOf(result);
    }

    /**
     * Encrypt the given file by RSA encryption and store the encryption in the output file
     * with the same location with same input file name with '.rsa' extension
     */
    public void encrypt() {
        long largestBlockSize = getLargestBlockSize();
        int numberOfCharacter = String.valueOf(largestBlockSize).length() / 3;
        String block = "";
        boolean flag = true; // just for testing
        while (flag /*Todo: filesDeal.hasBlock(numberOfCharacter)*/) {
            // Todo: block = filesDeal.getBlock(numberOfCharacter);
            String encryptedBlock = encryptBlock(block);
            // Todo: filesDeal.writeBlock(encryptedBlock);
        }
        // In case there is a block with size less than "numberOfCharacter"
        /*Todo: block = filesDeal.getAll(); */ // filesDeal.getAll() returns all the remainder characters
        String encryptedBlock = encryptBlock(block);
        // Todo: filesDeal.writeBlock(encryptedBlock);
        /* Todo: filesDeal.saveEncryptedFile(encryptedBlock);*/ // save and close encrypted file
        /* Todo: filesDeal.closeTextFile();*/ // close input file

    }

}

