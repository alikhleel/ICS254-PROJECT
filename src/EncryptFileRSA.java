import java.io.File;

public class EncryptFileRSA {
    private long n;
    private long e;
    // size of the block
    private long m;

    public EncryptFileRSA(File file){
        readKey();
    }

    /**
     *  Get the value of n, e, and m attributes from the first line of the file
     */
    private void readKey(){

    }

    /**
     * Encrypt the given file by RSA encryption and store the encryption in the output file
     * with the same location with same input file name with '.rsa' extension
     */
    public void encrypt(){

    }

}
