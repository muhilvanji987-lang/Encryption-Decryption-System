import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class FileEncryptor {

    private static final String SECRET_KEY = "MySecretKey12345";

    public static void encryptFile(String inputFile, String outputFile) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        FileInputStream fis = new FileInputStream(inputFile);
        byte[] inputBytes = fis.readAllBytes();
        fis.close();

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(outputBytes);
        fos.close();

        System.out.println("File encrypted successfully!");
        System.out.println("Encrypted file saved as: " + outputFile);
    }

    public static void decryptFile(String inputFile, String outputFile) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        FileInputStream fis = new FileInputStream(inputFile);
        byte[] inputBytes = fis.readAllBytes();
        fis.close();

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(outputBytes);
        fos.close();

        System.out.println("File decrypted successfully!");
        System.out.println("Decrypted file saved as: " + outputFile);
    }
}