import java.io.*;
import java.util.Scanner;

public class FilesDeal {
    private final String FILE_PATH;
    private RandomAccessFile randomAccessFile;
    private int readingPointer;
    private PrintWriter printWriter;
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
        if (block[numChar-1] == 0) throw new EOFException("Error: end of file");
    }


    public String getFirstLine() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(this.FILE_PATH));
        String line = scanner.nextLine();
        readingPointer += (line.length() + 1);
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
        printWriter.print(block);
    }

    public void writeRSABlock(String block) throws IOException {
        if (printWriter == null) createRSAFile();
        writeBlock(block+" ");
    }

    public void writeDECBlock(String block) throws IOException {
        if (printWriter == null) createDECFile();
        writeBlock(block);
    }

    public void closeWriting() throws IOException {
        printWriter.close();
    }

    private void createRSAFile() throws IOException {
        String outputPath = FILE_PATH.replaceFirst("(\\w+)$", "rsa");
        new File(outputPath).delete();
        printWriter = new PrintWriter(new FileOutputStream(outputPath, true));
    }

    private void createDECFile() throws IOException {
        String outputPath = FILE_PATH.replaceFirst("(\\w+)$", "dec");
        new File(outputPath).delete();
        printWriter = new PrintWriter(new FileOutputStream(outputPath, true));
    }
}
