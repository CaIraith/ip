package sol;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome(String logo) {
        System.out.println("Hello. I am\n" + logo);
        System.out.println("What would you like to do?");
    }

    public void showGoodbye() {
        System.out.println("Goodbye. See you next time.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void close() {
        scanner.close();
    }
}
