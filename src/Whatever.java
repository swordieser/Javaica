import java.io.*;
import java.util.ArrayList;

public class Whatever {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> tasks = new ArrayList<>();
        String[] command = reader.readLine().split(" ");

        while (!command[0].equals("exit")) {
            switch (command[0]) {
                case "add":
                    tasks.add(command[1]);
                    break;
                case "all":
                    tasks.forEach((n) -> System.out.print(n + "; "));
                    break;
                case "delete":
                    tasks.remove(command[1]);
                    break;
                case "save":
                    String savename = command[1];
                    FileWriter fw = new FileWriter(savename);
                    tasks.forEach((n) -> {
                        try {
                            fw.write(n);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    fw.close();
                    break;
                case "load":
                    String loadname = command[1];
                    try (BufferedReader br = new BufferedReader(new FileReader(loadname))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            tasks.add(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "complete":
                    String task = command[1];
                    tasks.remove(task);
                    task += " - completed";
                    tasks.add(task);
                    break;
                case "completed":
                    tasks.forEach((n) -> {
                        if (n.split(" - ").length > 1) {
                            System.out.println(n + " ");
                        }
                    });
                    break;
            }
            System.out.println("Whatever");
            command = reader.readLine().split(" ");
        }
    }
}
