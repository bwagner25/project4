import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import static java.lang.Integer.parseInt;
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        try{
            deserialize();
            System.out.println("\nWhat would you like to do?\n(1) Add a task.\n(2) Remove a task.\n(3) Update a task.\n(4) List tasks by priority.\n(5) List all tasks.\n(0) Exit.\n");
            String userInput = input.nextLine();
            while (!(userInput.equals("0"))) {
                boolean validInput = false;
                while(!validInput) {
                    switch (userInput) {
                        case "1" -> {
                            addTask();
                            validInput = true;
                        }
                        case "2" -> {
                            removeTask();
                            validInput = true;
                        }
                        case "3" -> {
                            updateTask();
                            validInput = true;
                        }
                        case "4" -> {
                            listTasks();
                            validInput = true;
                        }
                        case "5" -> {
                            listAllTasks();
                            validInput = true;
                        }
                        default -> {
                            System.out.println("That's not a valid option. Try again.");
                            userInput = input.nextLine();
                        }
                    }
                }
                System.out.println("\nWhat would you like to do?\n(1) Add a task.\n(2) Remove a task.\n(3) Update a task.\n(4) List tasks by priority.\n(5) List all tasks.\n(0) Exit.\n");
                userInput = input.nextLine();
            }
            serialize();
        }catch(Exception e){
            System.out.println("Something went wrong.");
        }
    }
    static void addTask(){
        System.out.println("What is the title of the task you want to add?");
        String newTaskTitle = input.nextLine();
        System.out.println("Describe the task you want to add.");
        String newTaskDesc = input.nextLine();
        System.out.println("From 0-5, what is the priority of the task?");
        String newTaskPriority = input.nextLine();
        boolean validInput = false;
        while(!validInput){
            switch(newTaskPriority){
                case "0", "1", "2", "3", "4", "5" -> validInput = true;
                default -> {
                    System.out.println("That isn't a valid priority. Try again.");
                    newTaskPriority = input.nextLine();
                }
            }
        }
        Task NewTask = new Task(newTaskTitle,newTaskDesc,newTaskPriority);
        tasks.add(NewTask);
    }
    static void removeTask(){
        System.out.println("Which number task would you like to remove?");
        String taskNumber = input.nextLine();
        int taskIndex = parseInt(taskNumber) - 1;
        tasks.remove(taskIndex);
    }
    static void updateTask(){
        System.out.println("Which number task would you like to update?");
        String taskNumber = input.nextLine();
        int taskIndex = parseInt(taskNumber) - 1;
        System.out.println("What is the title of the updated task.");
        String updatedTitle = input.nextLine();
        System.out.println("What is the description of the updated task?");
        String updatedDesc = input.nextLine();
        System.out.println("What is the priority of the updated task?");
        String updatedPriority = input.nextLine();
        Task updatedTask = new Task(updatedTitle, updatedDesc, updatedPriority);
        tasks.set(taskIndex, updatedTask);
    }
    static void listTasks(){
        Collections.sort(tasks);
        System.out.println("What priority would you like to list? (0-5)");
        String priority = input.nextLine();
        boolean validPriority = false;
        while(!validPriority) {
            switch (priority) {
                case "0", "1", "2", "3", "4", "5" -> {
                    validPriority = true;
                    for (Task task:tasks) {
                        if (task.getPriority().equals(priority)){
                            System.out.println(task);
                        }
                    }
                }
                default -> System.out.println("That is not a valid priority level. Try again..");
            }
        }
    }
    static void listAllTasks(){
        Collections.sort(tasks);
        System.out.println(tasks);
    }
    static void serialize(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("tasklist.json")){
            gson.toJson(tasks,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void deserialize(){
        try (FileReader reader = new FileReader("tasklist.json")){
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            tasks = gson.fromJson(jsonElement,type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}