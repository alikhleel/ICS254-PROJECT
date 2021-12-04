import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        FilesDeal filesDeal = new FilesDeal("try.txt");
        System.out.println(filesDeal.hasBlock(2));
        System.out.println(filesDeal.getBlock());
    }
}
