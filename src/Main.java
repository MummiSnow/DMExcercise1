import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Main {


	private HashMap<String, Component> components = new HashMap<>();
	public Main() throws Exception
	{

		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("", "Welcome to console - ?help for instructions", main);
		shell.commandLoop();

	}

	public static void main(String[] args) {
		System.out.println("Hello");
	}

	@Command(description = "Compares leaf and parent branch")
	public void checkParent(@Param(name = "leaf") String leaf,
							@Param(name = "branch") String father)

	{
		if (leaf.trim().length() == 0 && father.trim().length() != 0) {
			Collection<Component> leafs = components.get(father).getLeafs();
			System.out.println(leafs);
		} else if (father.trim().length() == 0 && leaf.trim().length() != 0) {
			Component retFather = components.get(leaf).getFather();
			System.out.println(retFather);
		} else {
			System.out.println(components.get(father) == components.get(leaf).getFather());
		}
	}

	@Command(description = "Compares leaf and grandparent branch")
	public void checkGrandparent(@Param(name = "leaf") String leaf,
								 @Param(name = "branch") String grandfather)

	{
		Collection<Component> leafs = new ArrayList<>();
		if (leaf.trim().length() == 0 && grandfather.trim().length() != 0)
		{
			for (Component father : components.get(grandfather).getLeafs())
			{
				leafs.addAll(father.getLeafs());
			}
			System.out.println(leafs);
		}
		else if (grandfather.trim().length() == 0 && leaf.trim().length() != 0)
		{
			Component gfather = components.get(leaf).getFather().getFather();
			System.out.println(gfather);
		}
		else
		{
			System.out.println((components.get(grandfather) == components.get(leaf).getFather().getFather()));
		}
	}


}