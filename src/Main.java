import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import java.util.*;

public class Main {


	private static HashMap<String, Component> components = new HashMap<>();
	public Main() throws Exception
	{



	}

	public static void main(String[] args) throws Exception {
		Component root = new Composite("root",null);
		Component child = new Composite("child1",root);
		Component child2 = new Composite("child2",root);
		Component child3 = new Composite("child11",child);
		Component child4 = new Composite("child12",child);
		components.put(root.getName(), root );
		components.put(child.getName(), child);
		components.put(child2.getName(), child2);
		components.put(child3.getName(), child3);
		components.put(child4.getName(), child4);
		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("Cliche>", "Welcome to console - ?help for instructions", main);
		shell.commandLoop();

	}


	@Command(description = "Variable for various relations")
	public void relations(@Param(name = "Upwards") int up,
						  @Param(name = "Downwards") int down,
						  @Param(name = "StartLeaf") String leaf)
	{
		checkRelations(up,down,leaf);
	}

	private Collection<Component> checkRelations(int up, int down, String startPoint)
	{
		Collection<Component> leafs = new ArrayList<>();
		Collection<Component> oldTempLeafs = new ArrayList<>();
		Collection<Component> tempLeafs = new ArrayList<>();
		Component currentLeaf = components.get(startPoint);
		for (int i = 0; i < up; i++)
		{
			currentLeaf = currentLeaf.getFather();
		}
		tempLeafs.add(currentLeaf);
		for (int i = 0; i < down; i++)
		{
			oldTempLeafs.clear();
			oldTempLeafs.addAll(tempLeafs);
			tempLeafs.clear();
			for (Component comp : oldTempLeafs)
			{
				tempLeafs.addAll(comp.getChildren());
				if (i - down == -1)
				{
					leafs.addAll(comp.getChildren());
				}
			}
		}
		if (down == 0)
		{
			leafs.add(currentLeaf);
		}
		return leafs;
	}

	@Command(description = "Compares leaf and parent branch. Please put x for unknown branch and y for unknown leaf")
	public void checkParent(@Param(name = "branch") String father,
							@Param(name = "leaf") String leaf)
	{
		if (leaf.equals("y"))
		{
			System.out.println(checkRelations(0,1,father));
		}
		else if (father.equals("x"))
		{
			System.out.println(checkRelations(1,0,leaf));
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

		Collection<Component> leafs = new ArrayList<>();
		if (leaf.equals("gc"))
		{
			System.out.println(checkRelations(0,2,grandfather));
		}
		else if (grandfather.equals("gf"))
		{
			System.out.println(checkRelations(2,0,leaf));
		}
		else
		{
			System.out.println((components.get(grandfather) == components.get(leaf).getFather().getFather()));
		}
	}

	@Command(description = "Add new root")
	public void addRoot(@Param(name = "branch") String branch,
						@Param(name = "leaf") String leaf){
		Component oldRoot = components.get(leaf);
		Component newRoot = new Composite(branch,null);
		if (oldRoot.isRoot()) {
			System.out.println("Root transfered from: " + leaf + " to "+ branch);
			newRoot.addChild(oldRoot);
			components.put(branch,newRoot);
		}
		
	}
	
	@Command(description = "Add leafs")
	public void addLeaf(@Param(name = "branch") String branch,
						@Param(name = "leaf") String leaf) {
		
		
		if (components.get(branch).isChild() || components.get(branch).isRoot()) {
			Component newChild = new Composite(leaf, components.get(branch));
			components.put(leaf, newChild);
			
		} else {
			components.forEach((k,v)->{
				System.out.println("key: "+k+" value: "+v.getName());
				
			});
		}
		
		
	}

	@Command(description = "display Leafs")
	public void displayLeafs(@Param(name = "branch") String branch)
	{
		if (components.containsKey(branch)) {
			components.forEach((k,v)->{
				if (branch.equals(k)){
					printTree(v," ");
				}
			});
			
		}
		
	}
	private static void printTree(Component node, String appender) {
			System.out.println(appender + node.getName());
			for (Component each : node.getChildren()) {
				printTree(each, appender + appender);
			}
	}

}