package Polymorph;

public class UpdateCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Update command executed");
    }

    @Override
    public void undo() {

    }
}
