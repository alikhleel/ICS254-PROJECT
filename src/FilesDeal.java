import java.io.*;
import java.util.Scanner;

public class FilesDeal {
    private final String FILE_PATH;
    private RandomAccessFile randomAccessFile;
    private int readingPointer;
    private FileOutputStream fileOutputStream;
    private String readingBlock;

    public FilesDeal(String filePath) throws FileNotFoundException {
        this.FILE_PATH = filePath;
    }


    private void readFile(int numChar) throws IOException {
        if (randomAccessFile == null) randomAccessFile = new RandomAccessFile(FILE_PATH, "r");
        byte[] block = new byte[numChar];
        randomAccessFile.seek(readingPointer);
        randomAccessFile.read(block);
        readingPointer += numChar;
        readingBlock = new String(block);
        if (readingBlock.trim().length() < numChar) throw new EOFException("Error: end of file");
    }


    public String getFirstLine() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(this.FILE_PATH));
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

    public String getBlock() {
        String tmp = readingBlock;
        readingBlock = "";
        return tmp;
    }

    public void closeReading() throws IOException {
        randomAccessFile.close();
        readingPointer = 0;
        readingBlock = "";
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

    public void writeDECBlock(String block) throws IOException {
        if (fileOutputStream == null) createDECFile();
        writeBlock(block);
    }

    public void closeWriting() throws IOException {
        fileOutputStream.close();
    }

    private void createRSAFile() throws FileNotFoundException {
        String outputPath = FILE_PATH.replaceFirst("(\\w+)$", "rsa");
        fileOutputStream = new FileOutputStream(outputPath, false);
    }

    private void createDECFile() throws FileNotFoundException {
        String outputPath = FILE_PATH.replaceFirst("(\\w+)$", "dec");
        fileOutputStream = new FileOutputStream(outputPath, false);
    }
}
