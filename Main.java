import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("   ENCRYPTION PROJECT MENU");
        System.out.println("==============================");
        System.out.println("1. Encrypt Text");
        System.out.println("2. Decrypt Text");
        System.out.println("3. Encrypt File");
        System.out.println("4. Decrypt File");
        System.out.println("5. Encrypt Image");
        System.out.println("6. Decrypt Image");
        System.out.println("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println("Enter text to encrypt: ");
            String text = sc.nextLine();
            String encrypted = AESUtil.encrypt(text);
            System.out.println("Encrypted Text: " + encrypted);

        } else if (choice == 2) {
            System.out.println("Enter encrypted text to decrypt: ");
            String text = sc.nextLine();
            String decrypted = AESUtil.decrypt(text);
            System.out.println("Decrypted Text: " + decrypted);

        } else if (choice == 3) {
            System.out.println("Enter input file name (example: test.txt): ");
            String inputFile = sc.nextLine();
            System.out.println("Enter output encrypted file name (example: test.enc): ");
            String outputFile = sc.nextLine();
            FileEncryptor.encryptFile(inputFile, outputFile);

        } else if (choice == 4) {
            System.out.println("Enter encrypted file name (example: test.enc): ");
            String inputFile = sc.nextLine();
            System.out.println("Enter output decrypted file name (example: decrypted.txt): ");
            String outputFile = sc.nextLine();
            FileEncryptor.decryptFile(inputFile, outputFile);

        } else if (choice == 5) {
            System.out.println("Enter image file name (example: test.png): ");
            String inputFile = sc.nextLine();
            System.out.println("Enter output encrypted image name (example: encrypted.png): ");
            String outputFile = sc.nextLine();
            ImageEncryptor.encryptImage(inputFile, outputFile);

        } else if (choice == 6) {
            System.out.println("Enter encrypted image name (example: encrypted.png): ");
            String inputFile = sc.nextLine();
            System.out.println("Enter output decrypted image name (example: decrypted.png): ");
            String outputFile = sc.nextLine();
            ImageEncryptor.decryptImage(inputFile, outputFile);
        }
    }
}