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
		components.put(root.getName(), root );
		components.put(child.getName(), child);
		components.put(child2.getName(), child2);
		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("Cliche>", "Welcome to console - ?help for instructions", main);
		shell.commandLoop();
		
		
		
		
		
	}

	@Command(description = "Compares leaf and parent branch. Please put x for unknown branch and y for unknown leaf")
	public void checkParent(@Param(name = "branch") String father,
							@Param(name = "leaf") String leaf)

	{
		if (leaf.equals("y"))
		{
			Collection<Component> leafs = components.get(father).getChildren();
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
			for (Component father : components.get(grandfather).getChildren())
			{
				leafs.addAll(father.getChildren());
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