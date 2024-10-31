import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            showMessage("Deposited: " + amount);
        } else {
            showMessage("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            showMessage("Withdrawn: " + amount);
        } else {
            showMessage("Invalid withdrawal amount!");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

class ATMGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField, passwordField;
    private JLabel balanceLabel;
    private JButton loginButton, checkBalanceButton, depositButton, withdrawButton, exitButton;
    private final String password = "1234";

    public ATMGUI(BankAccount account) {
        this.account = account;
        setTitle("ATM");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        add(new JLabel("Enter Password:"));
        passwordField = new JTextField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        balanceLabel = new JLabel("Balance: ");
        add(balanceLabel);

        amountField = new JTextField();
        amountField.setEnabled(false);
        add(amountField);

        checkBalanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exitButton = new JButton("Exit");

        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        exitButton.addActionListener(this);

        setButtonStates(false);
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(exitButton);

        setVisible(true);
    }

    private void setButtonStates(boolean enabled) {
        amountField.setEnabled(enabled);
        checkBalanceButton.setEnabled(enabled);
        depositButton.setEnabled(enabled);
        withdrawButton.setEnabled(enabled);
        exitButton.setEnabled(enabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Login".equals(command)) handleLogin();
        else if (e.getSource() == checkBalanceButton) balanceLabel.setText("Current Balance: " + account.getBalance());
        else if (e.getSource() == depositButton) handleTransaction(true);
        else if (e.getSource() == withdrawButton) handleTransaction(false);
        else if (e.getSource() == exitButton) exitATM();
    }

    private void handleLogin() {
        if (password.equals(passwordField.getText())) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            setButtonStates(true);
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect password!");
        }
    }

    private void handleTransaction(boolean isDeposit) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (isDeposit) account.deposit(amount);
            else account.withdraw(amount);
            balanceLabel.setText("Current Balance: " + account.getBalance());
            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount!");
        }
    }

    private void exitATM() {
        JOptionPane.showMessageDialog(this, "Exiting ATM. Thank You!");
        System.exit(0);
    }
}

public class ATM_INTERFACE_GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI(new BankAccount(1000)));
    }
}
