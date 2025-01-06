package ui;

public class UIMessage {
    public void error (String message){
        System.out.println("[ERROR]: " + message);
    }

    public void info (String message){
        System.out.println("\n** " + message + " **");
    }

    public void prompt (String message){
        System.out.println("\n:: " + message);
    }

    public void option (){
        System.out.print("\n\nOPTION: ");
    }

    public void numberedOption(int num, String text){
        System.out.printf("\n(%d) %s", num, text);
    }

    public void listItem(String text){
        System.out.println("\n" + text);
    }

    public void field(String text){
        System.out.print("\n- " + text);
    }

    public void showConfirmationOptions(){
        prompt("Confirm: ");
        System.out.println("\n(1). YES\n(2). NO");
        option();
    }
}

