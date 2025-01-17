package ui;

public class UIMessage {
    public void error(String message) {
        System.out.println("[ERROR]: " + message);
    }

    public void info(String message) {
        System.out.printf("\n** %s **", message);
    }

    public void prompt(String message) {
        System.out.printf("\n\n:: %s", message);
    }

    public void option() {
        System.out.print("\n\nOPTION: ");
    }

    public void numberedOption(int num, String text) {
        System.out.printf("\n(%d) %s", num, text);
    }

    public void listItem(Object item) {
        System.out.printf("\n=> %s", item.toString());
    }

    public void field(String text) {
        System.out.printf("\n- %s", text);
    }

    public void showConfirmationOptions() {
        prompt("Confirm: ");
        System.out.println("\n(1). YES\n(2). NO");
        option();
    }
}

