package ui;

public class UIMessage {
    void error (String message){
        System.out.println("[ERROR]: " + message);
    }

    void info (String message){
        System.out.println("\n** " + message + " **");
    }

    void prompt (String message){
        System.out.println("\n>> " + message);
    }

    void option (){
        System.out.print("\n\nOPTION: ");
    }
}
