import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import java.util.Collection;

public class Main {


	public static void main(String[] args) {
		System.out.println("Hello");
	}
/*
	@Command(description = "Compares leaf and parent branch")
	public void checkParent(@Param(name = "leaf") String leaf,
							@Param(name = "branch") String father)

	{
		if (leaf.trim().length() == 0 && father.trim().length() != 0) {
			Collection<Component> leafs = getLeafs(father.trim());
			System.out.println(leafs);
		} else if (father.trim().length() == 0 && leaf.trim().length() != 0) {
			Component father = leaf.getFather();
			System.out.println(father);
		} else {
			return (father == leaf.getFather());
		}
	}

	@Command(description = "Compares leaf and grandparent branch")
	public void checkGrandparent(@Param(name = "leaf") String leaf,
								 @Param(name = "branch") String grandfather)

	{
		Collection<Component> leafs = new arrayList<Component>;
		if (leaf.trim().length() == 0 && grandfather.trim().length() != 0) {
			for (Component father : grandfather.getLeafs()) {
				leafs += father.getLeafs();
			}
			System.out.println(leafs);
		} else if (grandfather.trim().length() == 0 && leaf.trim().length() != 0) {
			Component gfather = leaf.getFather().getFather();
			System.out.println(gfather);
		} else {
			return (grandfather == leaf.getFather().getFather());
		}
	}

*/
}