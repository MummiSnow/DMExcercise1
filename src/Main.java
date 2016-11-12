import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import java.util.*;

public class Main {


	private HashMap<String, Component> components = new HashMap<>();
	public Main() throws Exception
	{



	}

	public static void main(String[] args) throws Exception {
		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("Cliche>", "Welcome to console - ?help for instructions", main);
		shell.commandLoop();

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
			System.out.println(components.get(father).equals(components.get(leaf).getFather()));
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

	@Command(description = "Add leafs")
	public void addLeaf(@Param(name = "leaf") String leaf,
						@Param(name = "branch") String branch)
	{
		if (components.size() == 0)
		{
			components.put(branch, new Composite(branch, null));
		}
		components.put(leaf,new Composite(leaf, components.get(branch)));
	}

	@Command(description = "display Leafs")
	public void displayLeafs(@Param(name = "branch") String branch)
	{
		components.get(branch).display();
	}



}