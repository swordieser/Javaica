import java.io.*;
import java.util.ArrayList;

public class Whatever {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> tasks = new ArrayList<>();
        String[] command = reader.readLine().split(" ");
        ArrayList<String> add_tasks = new ArrayList<>();
        String[] com = command;
        while (!com[0].equals("exit")) {
            if (add_tasks.size() != 0) {
                com = add_tasks.get(0).split(" ");
                add_tasks.remove(0);
            } else {
                com = command;
            }
            switch (com[0]) {
                case "add":
                    int i = 0;
                    for (String string : tasks) {
                        if (com[1].equals(string)) {
                            i++;
                        }
                    }
                    if (i != 0) {
                        tasks.add(com[1] + "(" + i + ")");
                    } else {
                        tasks.add(com[1]);
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
                    tasks.remove(com[1]);
                    break;
                case "save":
                    String savename = com[1];
                    FileWriter fw = new FileWriter(savename);
                    tasks.forEach((n) -> {
                        try {
                            fw.write(n);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Save error");
                        }
                    });
                    fw.close();
                    break;
                case "load":
                    String loadname = com[1];
                    try (BufferedReader br = new BufferedReader(new FileReader(loadname))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            add_tasks.add(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Load error");
                    }
                    break;
                case "complete":
                    String task = com[1];
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
            if (add_tasks.size() == 0) {
                com = reader.readLine().split(" ");
            }
        }
    }
}
