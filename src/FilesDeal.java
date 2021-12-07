import java.io.*;
import java.util.Scanner;

public class FilesDeal {
    private String filePath;
    private int readingPointer;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;
    private String readingBlock;

    public FilesDeal(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        fileInputStream = new FileInputStream(this.filePath);
    }


    private void readFile(int numChar) throws IOException,EOFException {
        /*byte[] block = new byte[numChar];
        fileInputStream.readNBytes(block,readingPointer,numChar);
        readingBlock = new String(block);
        readingPointer += numChar;
         */

        byte[] magic = new byte[numChar];
        RandomAccessFile raf = new RandomAccessFile(filePath, "r");
        raf.seek(readingPointer);
        raf.read(magic);
        readingPointer += numChar;
        readingBlock = new String(magic);

        if (readingBlock.trim().length() < numChar) throw new EOFException("Error: end of file");

    }


    public String getFirstLine() {
        Scanner scanner = new Scanner(fileInputStream);
        String line = scanner.nextLine();
        readingPointer += (line.length() + 2);
        return line;
    }

    public boolean hasBlock(int numChar) throws IOException {
        boolean flag = true;
        try {
            readFile(numChar);
        } catch (EOFException e) {
            flag = false;
        }
        return flag;
    }

    public String getBlock(int charNumber) throws IOException {
        if (readingBlock.isEmpty() || readingBlock.isBlank()) {
            readFile(charNumber);
        }
        String tmp = readingBlock;
        readingBlock = "";
        return tmp;
    }

    public String getReadingBlock() {
        String tmp = readingBlock;
        readingBlock = "";
        return tmp;
    }

    private void writeBlock(String block) {
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        printWriter.print(block);
        printWriter.close();
    }

    public void writeRSABlock(String block) throws IOException {
        if (fileOutputStream == null) createRSAFile();
        writeBlock(block);
    }

    public void closeWriting() throws IOException {
        fileOutputStream.close();
    }

    public void closeReading() throws IOException {
        fileInputStream.close();
        readingPointer = 0;
        readingBlock = "";
    }

    private void createRSAFile() throws FileNotFoundException {
        String outputPath = filePath.replaceFirst("(\\w+)$", "rsa");
        fileOutputStream = new FileOutputStream(outputPath, false);
    }

    private void createDECFile() throws FileNotFoundException {
        String outputPath = filePath.replaceFirst("(\\w+)$", "dec");
        fileOutputStream = new FileOutputStream(outputPath, false);
    }
}
