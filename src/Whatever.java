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
                    int i = 0;
                    for (String string : tasks) {
                        if (command[1].equals(string)) {
                            i++;
                        }
                    }
                    if (i != 0) {
                        tasks.add(command[1] + "(" + Integer.toString(i) + ")");
                    } else {
                        tasks.add(command[1]);
                    }
                    break;
                case "all":
                    int j = 0;
                    for (; j < tasks.size() - 1; j++) {
                        System.out.println(tasks.get(j) + "; ");
                    }
                    System.out.println(tasks.get(j) + ".");
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

            command = reader.readLine().split(" ");
        }
    }
}
