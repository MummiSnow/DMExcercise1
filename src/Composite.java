import java.util.ArrayList;
import java.util.List;


public class Composite extends Component {
	
	String name;
	private List<Component> children;
	
	public Composite(String name) {
		this.name = name;
		this.children = new ArrayList<>();
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
}
