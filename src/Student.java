import java.util.ArrayList;

public class Student {
    private String name;
    private String surname;
    private String group;
    private ArrayList<TaskResult> taskResults = new ArrayList<>();

    public Student(String name, String surname, String group){
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public void addTaskResult(TaskResult taskResult){
        taskResults.add(taskResult);
    }

    public void removeTaskResult(TaskResult taskResult){
        taskResults.remove(taskResult);
    }

    public ArrayList<TaskResult> getTaskResults(){
        return taskResults;
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        sb.append(String.format("%s %s;%s;%n", name, surname, group));
        for (var taskResult : taskResults){
            sb.append(String.format("%s%n", taskResult.toString()));
        }
        return sb.toString();
    }
}
