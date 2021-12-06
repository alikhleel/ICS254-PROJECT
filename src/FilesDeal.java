import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FilesDeal {
    private String filePath;
    private long pointer;
    private String block;

    public FilesDeal(String filePath) {
        this.filePath = filePath;
    }


    private void readFile(int numChar) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        byte[] a =fileInputStream.readNBytes(numChar);
//        block = new String(a, StandardCharsets.UTF_8);

        byte[] magic = new byte[numChar];
        RandomAccessFile raf = new RandomAccessFile(filePath, "r");
        raf.seek(pointer);
        raf.readFully(magic);
        pointer += numChar;
        block = new String(magic);
        if (block.length() < numChar) throw new EOFException("Error: end of file");

/*
        FileInputStream fileInputStream = new FileInputStream(this.filePath);
        Scanner scanner = new Scanner(fileInputStream);


        while (scanner.hasNext()){

            System.out.println(scanner.nextLine());
        }

        scanner.close();
        fileInputStream.close();
*/
    }

    public String getBlock() {
        return block;
    }

    public void writeBlock(String s) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("output.rsa");
        PrintWriter printWriter = new PrintWriter(fileOutputStream);

        printWriter.print(s);
        printWriter.close();
        fileOutputStream.close();
    }

    public String getFirstLine() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.filePath);
        Scanner scanner = new Scanner(fileInputStream);
        return scanner.nextLine();
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
        if (block.isEmpty() || block.isBlank()) {
            readFile(charNumber);
        }
        String tmp = block;
        block = "";
        return tmp;
    }

}
