import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EncryptionGUI {

    public static void main(String[] args) {
        // Main Window
        JFrame frame = new JFrame("Encryption & Decryption System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));
        frame.setLayout(new BorderLayout());

        // Title Label
        JLabel title = new JLabel("ENCRYPTION & DECRYPTION SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(0, 200, 255));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(title, BorderLayout.NORTH);

        // Tab Panel
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(new Color(50, 50, 50));
        tabs.setForeground(Color.WHITE);
        tabs.setFont(new Font("Arial", Font.BOLD, 14));

        // ── TAB 1: TEXT ──
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(40, 40, 40));
        textPanel.setLayout(new GridLayout(6, 1, 10, 10));
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel textLabel = new JLabel("Enter Text:");
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField textInput = new JTextField();
        textInput.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton encryptTextBtn = new JButton("ENCRYPT TEXT");
        encryptTextBtn.setBackground(new Color(0, 150, 255));
        encryptTextBtn.setForeground(Color.WHITE);
        encryptTextBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton decryptTextBtn = new JButton("DECRYPT TEXT");
        decryptTextBtn.setBackground(new Color(255, 100, 0));
        decryptTextBtn.setForeground(Color.WHITE);
        decryptTextBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel textResultLabel = new JLabel("Result:");
        textResultLabel.setForeground(Color.WHITE);
        textResultLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField textResult = new JTextField();
        textResult.setEditable(false);
        textResult.setFont(new Font("Arial", Font.PLAIN, 13));
        textResult.setBackground(new Color(60, 60, 60));
        textResult.setForeground(new Color(0, 255, 100));

        encryptTextBtn.addActionListener(e -> {
            try {
                String result = AESUtil.encrypt(textInput.getText());
                textResult.setText(result);
                JOptionPane.showMessageDialog(frame, "Text Encrypted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        decryptTextBtn.addActionListener(e -> {
            try {
                String result = AESUtil.decrypt(textInput.getText());
                textResult.setText(result);
                JOptionPane.showMessageDialog(frame, "Text Decrypted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        textPanel.add(textLabel);
        textPanel.add(textInput);
        textPanel.add(encryptTextBtn);
        textPanel.add(decryptTextBtn);
        textPanel.add(textResultLabel);
        textPanel.add(textResult);
        tabs.addTab("TEXT", textPanel);

        // ── TAB 2: FILE ──
        JPanel filePanel = new JPanel();
        filePanel.setBackground(new Color(40, 40, 40));
        filePanel.setLayout(new GridLayout(6, 1, 10, 10));
        filePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel fileLabel = new JLabel("Selected File: None");
        fileLabel.setForeground(Color.WHITE);
        fileLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton browseFileBtn = new JButton("BROWSE FILE");
        browseFileBtn.setBackground(new Color(100, 100, 100));
        browseFileBtn.setForeground(Color.WHITE);
        browseFileBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton encryptFileBtn = new JButton("ENCRYPT FILE");
        encryptFileBtn.setBackground(new Color(0, 150, 255));
        encryptFileBtn.setForeground(Color.WHITE);
        encryptFileBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton decryptFileBtn = new JButton("DECRYPT FILE");
        decryptFileBtn.setBackground(new Color(255, 100, 0));
        decryptFileBtn.setForeground(Color.WHITE);
        decryptFileBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel fileResultLabel = new JLabel("Status: Waiting...");
        fileResultLabel.setForeground(new Color(0, 255, 100));
        fileResultLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        final String[] selectedFilePath = {""};

        browseFileBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFilePath[0] = chooser.getSelectedFile().getAbsolutePath();
                fileLabel.setText("Selected: " + chooser.getSelectedFile().getName());
            }
        });

        encryptFileBtn.addActionListener(e -> {
            try {
                String output = selectedFilePath[0] + ".enc";
                FileEncryptor.encryptFile(selectedFilePath[0], output);
                fileResultLabel.setText("Status: Encrypted → " + new File(output).getName());
                JOptionPane.showMessageDialog(frame, "File Encrypted Successfully!\nSaved as: " + output, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        decryptFileBtn.addActionListener(e -> {
            try {
                String output = selectedFilePath[0].replace(".enc", "_decrypted.txt");
                FileEncryptor.decryptFile(selectedFilePath[0], output);
                fileResultLabel.setText("Status: Decrypted → " + new File(output).getName());
                JOptionPane.showMessageDialog(frame, "File Decrypted Successfully!\nSaved as: " + output, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        filePanel.add(fileLabel);
        filePanel.add(browseFileBtn);
        filePanel.add(encryptFileBtn);
        filePanel.add(decryptFileBtn);
        filePanel.add(fileResultLabel);
        filePanel.add(new JLabel(""));
        tabs.addTab("FILE", filePanel);

        // ── TAB 3: IMAGE ──
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(40, 40, 40));
        imagePanel.setLayout(new GridLayout(6, 1, 10, 10));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel imageLabel = new JLabel("Selected Image: None");
        imageLabel.setForeground(Color.WHITE);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton browseImageBtn = new JButton("BROWSE IMAGE");
        browseImageBtn.setBackground(new Color(100, 100, 100));
        browseImageBtn.setForeground(Color.WHITE);
        browseImageBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton encryptImageBtn = new JButton("ENCRYPT IMAGE");
        encryptImageBtn.setBackground(new Color(0, 150, 255));
        encryptImageBtn.setForeground(Color.WHITE);
        encryptImageBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton decryptImageBtn = new JButton("DECRYPT IMAGE");
        decryptImageBtn.setBackground(new Color(255, 100, 0));
        decryptImageBtn.setForeground(Color.WHITE);
        decryptImageBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel imageResultLabel = new JLabel("Status: Waiting...");
        imageResultLabel.setForeground(new Color(0, 255, 100));
        imageResultLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        final String[] selectedImagePath = {""};

        browseImageBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImagePath[0] = chooser.getSelectedFile().getAbsolutePath();
                imageLabel.setText("Selected: " + chooser.getSelectedFile().getName());
            }
        });

        encryptImageBtn.addActionListener(e -> {
            try {
                String output = selectedImagePath[0] + ".enc";
                ImageEncryptor.encryptImage(selectedImagePath[0], output);
                imageResultLabel.setText("Status: Encrypted → " + new File(output).getName());
                JOptionPane.showMessageDialog(frame, "Image Encrypted Successfully!\nSaved as: " + output, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        decryptImageBtn.addActionListener(e -> {
            try {
                String output = selectedImagePath[0].replace(".enc", "_decrypted.png");
                ImageEncryptor.decryptImage(selectedImagePath[0], output);
                imageResultLabel.setText("Status: Decrypted → " + new File(output).getName());
                JOptionPane.showMessageDialog(frame, "Image Decrypted Successfully!\nSaved as: " + output, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        imagePanel.add(imageLabel);
        imagePanel.add(browseImageBtn);
        imagePanel.add(encryptImageBtn);
        imagePanel.add(decryptImageBtn);
        imagePanel.add(imageResultLabel);
        imagePanel.add(new JLabel(""));
        tabs.addTab("IMAGE", imagePanel);

        frame.add(tabs, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("Developed by Vanji Muthu P | AES Encryption System", SwingConstants.CENTER);
        footer.setForeground(new Color(150, 150, 150));
        footer.setFont(new Font("Arial", Font.ITALIC, 12));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(footer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}