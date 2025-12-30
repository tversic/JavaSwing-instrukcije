package Polymorph;

public class CreateCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Create command executed");
    }

    @Override
    public void undo() {

    }
}
