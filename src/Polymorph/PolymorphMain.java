package Polymorph;

import java.util.ArrayList;
import java.util.List;

public class PolymorphMain {
    public static void main(String[] args) {
        var createCommand = new CreateCommand();
        var deleteCommand = new DeleteCommand();
        var updateCommand = new UpdateCommand();

        List<Command> commands = new ArrayList<>();
        commands.add(createCommand);
        commands.add(deleteCommand);
        commands.add(updateCommand);

        for (Command command : commands)
            command.execute();
    }
}
