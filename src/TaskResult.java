public class TaskResult {
    private Task task;
    private int currentPoints;

    public TaskResult(Task task, int currentPoint){
        this.task = task;
        this.currentPoints = currentPoint;
    }

    private Task getTask(){
        return task;
    }

    private int getCurrentPoints(){
        return currentPoints;
    }

    @Override
    public String toString(){
        return String.format("%s:%d", task.toString(), currentPoints);
    }
}
