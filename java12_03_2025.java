import java.util.HashMap;

class Task {
    String taskName;
    int priority;
    int duration;

    public Task(String taskName, int priority, int duration) {
        this.taskName = taskName;
        this.priority = priority;
        this.duration = duration;
    }

    public String toString() {
        return "[Priority " + priority + "] " + taskName + " (Duration: " + duration + " mins)";
    }
}

class TaskScheduler {
    Task[] scheduledTasks = new Task[10]; // Фиксированный размер
    Task[] pendingTasks = new Task[10]; 
    int scheduledCount = 0;
    int pendingCount = 0;

    public void addTask(Task task) {
        scheduledTasks[scheduledCount++] = task;
        sortTasks();
    }

    private void sortTasks() {
        for (int i = 0; i < scheduledCount - 1; i++) {
            for (int j = i + 1; j < scheduledCount; j++) {
                if (scheduledTasks[i].priority < scheduledTasks[j].priority ||
                    (scheduledTasks[i].priority == scheduledTasks[j].priority &&
                     scheduledTasks[i].duration > scheduledTasks[j].duration)) {
                    Task temp = scheduledTasks[i];
                    scheduledTasks[i] = scheduledTasks[j];
                    scheduledTasks[j] = temp;
                }
            }
        }
    }

    public void processTask() {
        if (scheduledCount > 0) {
            System.out.println("Processing Task: " + scheduledTasks[0]);
            shiftLeft(scheduledTasks, scheduledCount--);
        } else if (pendingCount > 0) {
            System.out.println("Processing Pending Task: " + pendingTasks[0]);
            shiftLeft(pendingTasks, pendingCount--);
        }
    }

    public void delayTask(String taskName) {
        for (int i = 0; i < scheduledCount; i++) {
            if (scheduledTasks[i].taskName.equals(taskName)) {
                pendingTasks[pendingCount++] = scheduledTasks[i];
                shiftLeft(scheduledTasks, scheduledCount--);
                System.out.println("Delaying Task: " + taskName);
                return;
            }
        }
    }

    private void shiftLeft(Task[] array, int size) {
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
    }

    public void showTasks() {
        System.out.println("\nScheduled Tasks:");
        if (scheduledCount == 0) {
            System.out.println("(No priority tasks)");
        } else {
            for (int i = 0; i < scheduledCount; i++) {
                System.out.println((i + 1) + ". " + scheduledTasks[i]);
            }
        }

        System.out.println("\nPending Tasks:");
        if (pendingCount == 0) {
            System.out.println("(No pending tasks)");
        } else {
            for (int i = 0; i < pendingCount; i++) {
                System.out.println((i + 1) + ". " + pendingTasks[i]);
            }
        }
    }
}

class ListMerger {
    public static <T> Object[] mergeAlternately(T[] list1, T[] list2) {
        int size1 = list1.length;
        int size2 = list2.length;
        Object[] mergedList = new Object[size1 + size2];

        int index = 0, i = 0, j = 0;
        while (i < size1 || j < size2) {
            if (i < size1) mergedList[index++] = list1[i++];
            if (j < size2) mergedList[index++] = list2[j++];
        }
        return mergedList;
    }
}

class MapPrinter {
    public static <K, V> void printMap(HashMap<K, V> map) {
        for (K key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }
}

public class TaskManager {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(new Task("Code Review", 3, 20));
        scheduler.addTask(new Task("System Update", 5, 45));
        scheduler.addTask(new Task("Database Backup", 2, 30));
        scheduler.addTask(new Task("Deploy New Feature", 5, 50));
        scheduler.addTask(new Task("Bug Fixing", 4, 25));

        System.out.println("Tasks Added:");
        scheduler.showTasks();

        scheduler.processTask();
        scheduler.delayTask("Code Review");
        scheduler.showTasks();

        scheduler.delayTask("Database Backup");
        scheduler.showTasks();

        scheduler.processTask();
        scheduler.showTasks();

        scheduler.processTask();
        scheduler.showTasks();

        scheduler.processTask();
        scheduler.showTasks();

        scheduler.processTask();
        scheduler.showTasks();

        Integer[] list1 = {1, 3, 5};
        Integer[] list2 = {2, 4, 6, 8};

        Object[] merged = ListMerger.mergeAlternately(list1, list2);
        System.out.print("Merged List: ");
        for (Object num : merged) {
            System.out.print(num + " ");
        }
        System.out.println();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("Айбек", 25);
        map.put("Айгуль", 30);
        map.put("Хадижа", 22);

        System.out.println("\nMap Contents:");
        MapPrinter.printMap(map);
    }
}
