import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String temp = scanner.nextLine();
            if (temp.equals("exit")){
                return;
            }
            controller.doCommand(temp);
        }
    }
}