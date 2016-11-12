import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Composite extends Component {
	
	private String name;
	private Component father;
	private List<Component> children;
	
	public Composite(String name, Component father) {
		this.name = name;
		this.children = new ArrayList<>();
		this.father = father;
	}
	
	
	@Override
	public void addChild(Component component) {
		children.add(component);
	}
	
	@Override
	public void removeChild(Component component) {
		children.remove(component);
	}
	
	@Override
	public void display() {
		System.out.println(name);
		int depth = 0;
		for (Component comp : children) {
			comp.display();
		}
	}

	@Override
	public Collection<Component> getLeafs()
	{
		return children;
	}

	@Override
	public Component getFather()
	{
		return father;
	}
}
