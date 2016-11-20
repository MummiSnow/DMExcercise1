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
		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("Cliche>", "Welcome to console - ?help for instructions", main);
		shell.commandLoop();
		
		
		
		
		
	}

	@Command(description = "Compares leaf and parent branch")
	public void checkParent(@Param(name = "branch") String father,
							@Param(name = "leaf") String leaf)

	{
		if (leaf.trim().length() == 0 && father.trim().length() != 0) {
			Collection<Component> leafs = components.get(father).getChildren();
			System.out.println(leafs);
		} else if (father.trim().length() == 0 && leaf.trim().length() != 0) {
			Component retFather = components.get(leaf).getFather();
			System.out.println(retFather);
		} else {
			System.out.println(components.get(father).equals(components.get(leaf).getFather()));
		}
	}

	@Command(description = "Compares leaf and grandparent branch")
	public void checkGrandparent(@Param(name = "branch") String grandfather,
								 @Param(name = "leaf") String leaf)

	{
		Collection<Component> leafs = new ArrayList<>();
		if (leaf.trim().length() == 0 && grandfather.trim().length() != 0)
		{
			for (Component father : components.get(grandfather).getChildren())
			{
				leafs.addAll(father.getChildren());
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

	@Command(description = "Add new root")
	public void addRoot(@Param(name = "branch") String branch,
						@Param(name = "leaf") String leaf){
		Component oldRoot = components.get(leaf);
		Component newRoot = new Composite(leaf,null);
		if (oldRoot.isRoot()) {
			System.out.println("Root transfered from: " + leaf + " to "+ branch);
			newRoot.addChild(oldRoot);
			components.put(leaf,newRoot);
		}
		
	}
	
	@Command(description = "Add leafs")
	public void addLeaf(@Param(name = "branch") String branch,
						@Param(name = "leaf") String leaf) {
		if (components.get(branch).isRoot()) {
			components.put(leaf, new Composite(leaf, components.get(branch)));
			System.out.println("iam the root - added " + leaf + " to children");
		} else if(components.get(branch).isChild()){
			System.out.println("iam a child: ");
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