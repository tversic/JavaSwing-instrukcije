package Polymorph;

public class DeleteCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Delete command executed");
    }

    @Override
    public void undo() {

    }
}
