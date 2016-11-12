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

		Shell shell = ShellFactory.createConsoleShell("Tree", "Welcome to console - ?help for instructions - ?list for list of commands", main);
		shell.commandLoop();
	}

	@Command(description = "Compares leaf and parent branch. Please put x for unknown branch and y for unknown leaf")
	public void checkParent(@Param(name = "branch") String father,
							@Param(name = "leaf") String leaf)

	{
		if (leaf.equals("y"))
		{
			Collection<Component> leafs = components.get(father).getLeafs();
			System.out.println(leafs);
		}
		else if (father.equals("x"))
		{
			Component retFather = components.get(leaf).getFather();
			System.out.println(retFather);
		}
		else
		{
			System.out.println(components.get(father).equals(components.get(leaf).getFather()));
		}
	}

	@Command(description = "Compares leaf and grandparent branch. Please put gf for unknown branch and gc for unknown leaf")
	public void checkGrandparent(@Param(name = "branch") String grandfather,
								 @Param(name = "leaf") String leaf)
	{
		System.out.println("Grandfather check has been called.");
		System.out.println(grandfather);
		System.out.println(leaf);
		Collection<Component> leafs = new ArrayList<>();
		if (leaf.equals("gc"))
		{
			for (Component father : components.get(grandfather).getLeafs())
			{
				leafs.addAll(father.getLeafs());
			}
			System.out.println(leafs);
		}
		else if (grandfather.equals("gf"))
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
	public void addLeaf(@Param(name = "branch") String branch,
						@Param(name = "leaf") String leaf)
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