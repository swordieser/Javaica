import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final List<Task> tasksNotDone;
    private final List<Task> tasksDone;

    private FileReader fr;
    private FileWriter fw;

    public View() {
        this.tasksNotDone = new ArrayList<>();
        this.tasksDone = new ArrayList<>();
    }

    void addTask(int id, String description) {
        for (Task task : tasksNotDone){
            if (task.getId() == id){
                System.out.println("Incorrect id, type another");
                return;
            }
        }

        for (Task task : tasksDone){
            if (task.getId() == id){
                System.out.println("Incorrect id, type another");
                return;
            }
        }

        this.tasksNotDone.add(new Task(id, description));
    }

    void printAllTasks() {
        for (Task task : tasksNotDone) {
            System.out.printf("Task №%d — %s\n", task.getId(), task.getDescription());
        }

        for (Task task : tasksDone) {
            System.out.printf("Task №%d — done — %s\n", task.getId(), task.getDescription());
        }
    }

    void deleteTask(int id) {
        boolean deleted = false;

        for (Task task : tasksNotDone) {
            if (task.getId() == id) {
                tasksNotDone.remove(task);
                deleted = true;
                break;
            }
        }

        if (!deleted){
            for (Task task : tasksDone) {
                if (task.getId() == id) {
                    tasksDone.remove(task);
                    deleted = true;
                    break;
                }
            }
        }

        if (!deleted){
            System.out.println("There is no task with this id");
        }
    }

    void saveToFile(String fileName) {
        File file = new File("src/" + fileName);

        if (!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            fw = new FileWriter(file);
            for (Task task : tasksNotDone){
                String s = task.getId() + " — " + task.getDescription() + "\n";
                fw.write(s);
                fw.flush();
            }

            for (Task task : tasksDone){
                String s = task.getId() + " — done — " + task.getDescription() + "\n";
                fw.write(s);
                fw.flush();
            }


        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void loadFromFile(String fileName){
        File file = new File("src/" + fileName);

        if (file.exists()){
            ArrayList<String> tasks = new ArrayList<>();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    tasks.add(scanner.nextLine());
                }

                for (String line : tasks) {
                    String[] str = line.split(" ", 5);
                    int id = -1;
                    try {
                        id = Integer.parseInt(str[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect task id, use decimal integer");
                    }

                    boolean done = false;
                    if (str[2].equals("done")) {
                        done = true;
                    }

                    Task task = done ? new Task(id, str[4]) : new Task(id, str[2]);
                    if (done) {
                        task.setDone(true);
                        tasksDone.add(task);
                    } else {
                        tasksNotDone.add(task);
                    }
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("No file with this name");
        }

        try {
            fr.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void complete(int id) {
        for (Task task : tasksNotDone){
            if (task.getId() == id){
                tasksNotDone.get(tasksNotDone.indexOf(task)).setDone(true);
                tasksDone.add(task);
                deleteTask(id);
                return;
            }
        }
        System.out.println("There is no task with this id");
    }

    void completed() {
        for (Task task : tasksDone) {
            System.out.printf("Task №%d — done — %s\n", task.getId(), task.getDescription());
        }
    }
}
