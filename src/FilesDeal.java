import java.io.*;
import java.util.Scanner;

public class FilesDeal {
    private String filePath;

    public FilesDeal(String filePath) {
        this.filePath = filePath;
    }


    public void readFile() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(this.filePath);
        Scanner scanner = new Scanner(fileInputStream);


        while (scanner.hasNext()){

            System.out.println(scanner.nextLine());
        }

        scanner.close();
        fileInputStream.close();
    }


    public void writeBlock(String s) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("output.rsa");
        PrintWriter printWriter = new PrintWriter(fileOutputStream);

        printWriter.print(s);
        printWriter.close();
        fileOutputStream.close();
    }


    public String getFirstLine() throws IOException{
        FileInputStream fileInputStream = new FileInputStream(this.filePath);
        Scanner scanner = new Scanner(fileInputStream);
        return scanner.nextLine();
    }

    public boolean hasBlock(int numChar) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(this.filePath);
        Scanner scanner = new Scanner(fileInputStream);
        return scanner.hasNext("/^.{"+numChar+"}$/");
    }

    public String getBlock(int charNumber){

        return null;
    }

}
