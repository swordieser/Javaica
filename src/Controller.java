public class Controller {
    private final View view;

    Controller(View view) {
        this.view = view;
    }

    void doCommand(String command) {
        String[] str = command.split(" ", 3);

        if (str.length == 1) {
            String temp = str[0].toLowerCase();
            switch (temp) {
                case "all":
                    this.view.printAllTasks();
                    break;
                case "completed":
                    this.view.completed();
                    break;
            }
        } else if (str.length == 2) {
            String temp_command = str[0].toLowerCase();
            String temp = str[1];
            int id;

            switch (temp_command) {
                case "delete":
                    id = -1;
                    try {
                        id = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect task id, use decimal integer");
                    }
                    this.view.deleteTask(id);
                    break;
                case "complete":
                    id = -1;
                    try {
                        id = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect task id, use decimal integer");
                    }
                    this.view.complete(id);
                    break;
                case "load":
                    this.view.loadFromFile(temp);
                    break;
                case "save":
                    this.view.saveToFile(temp);
                    break;
            }
        } else if (str.length == 3) {
            String add = str[0].toLowerCase();
            String temp_id = str[1];
            String description = str[2];
            if (add.equals("add")) {
                int id = -1;
                try {
                    id = Integer.parseInt(temp_id);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect task id, use decimal integer");
                }
                this.view.addTask(id, description);
            }
        }

    }

}
