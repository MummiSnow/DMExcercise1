import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Composite extends Component {
	
	private String name;
	private Component parent;
	private List<Component> children;
	
	public Composite(String name, Component parent) {
		this.name = name;
		this.children = new ArrayList<>();
		this.parent = parent;
		if (parent != null) parent.addChild(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setParent(Component parent) {
		this.parent = parent;
	}
	
	@Override
	public void addChild(Component component) {
		children.add(component);
	}
	
	@Override
	public void removeChild(Component component) {
		children.remove(component);
	}
	
	public boolean isRoot() {
		return (this.parent == null);
	}
	
	public boolean isChild(){
		if (this.children.size() == 0)
			return true;
		else
			return false;
	}

	@Override
	public Collection<Component> getChildren()
	{
		return children;
	}

	@Override
	public Component getFather(){
		if (parent == null) return null;
		return parent;
	}

	@Override
	public String toString() {
		return name;
	}
}
