import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PasswordGeneratorGUI extends JFrame {
    private PasswordGenerator passwordGenerator;

    public PasswordGeneratorGUI() {
        super("Password Generator");

        setSize(540, 570);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        passwordGenerator = new PasswordGenerator();
        addGUIComponents();
    }

    private void addGUIComponents() {
        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 540, 39); // Full width
        add(titleLabel);

        // Create result text area
        JTextArea passwordOutput = new JTextArea();
        passwordOutput.setEditable(false);
        passwordOutput.setFont(new Font("Dialog", Font.BOLD, 28));

        JScrollPane passwordOutputPane = new JScrollPane(passwordOutput);
        passwordOutputPane.setBounds(25, 70, 479, 70);
        passwordOutputPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(passwordOutputPane);

        // Password length label
        JLabel passwordLengthLabel = new JLabel("Password Length");
        passwordLengthLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        passwordLengthLabel.setBounds(25, 160, 250, 39);
        add(passwordLengthLabel);

        // Password length input
        JTextArea passwordLengthInputArea = new JTextArea();
        passwordLengthInputArea.setFont(new Font("Dialog", Font.PLAIN, 28));
        passwordLengthInputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordLengthInputArea.setBounds(310, 160, 80, 39);
        add(passwordLengthInputArea);

        // Toggle buttons
        JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
        uppercaseToggle.setBounds(25, 230, 225, 56);
        add(uppercaseToggle);

        JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
        lowercaseToggle.setBounds(282, 230, 225, 56);
        add(lowercaseToggle);

        JToggleButton numberToggle = new JToggleButton("Numbers");
        numberToggle.setBounds(25, 300, 225, 56);
        add(numberToggle);

        JToggleButton symbolToggle = new JToggleButton("Symbols");
        symbolToggle.setBounds(282, 300, 225, 56);
        add(symbolToggle);

        // Generate button
        JButton generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Dialog", Font.PLAIN, 32));
        generateButton.setBounds(155, 400, 222, 41);
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = passwordLengthInputArea.getText().trim();
                if (input.isEmpty()) {
                    passwordOutput.setText("Please enter a password length.");
                    return;
                }

                int passwordLength;
                try {
                    passwordLength = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    passwordOutput.setText("Invalid number.");
                    return;
                }

                boolean useLower = lowercaseToggle.isSelected();
                boolean useUpper = uppercaseToggle.isSelected();
                boolean useNumbers = numberToggle.isSelected();
                boolean useSymbols = symbolToggle.isSelected();

                if (!(useLower || useUpper || useNumbers || useSymbols)) {
                    passwordOutput.setText("Select at least one option.");
                    return;
                }

                String generatedPassword = passwordGenerator.generatePassword(
                        passwordLength,
                        useLower,
                        useUpper,
                        useNumbers,
                        useSymbols
                );

                passwordOutput.setText(generatedPassword);
            }
        });

        add(generateButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordGeneratorGUI gui = new PasswordGeneratorGUI();
            gui.setVisible(true);
        });
    }
}
