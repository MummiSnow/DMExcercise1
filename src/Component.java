import java.util.List;

public abstract class Component {
	
	private List<Component> children;
	
	public void addChild(Component component){
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public void removeChild(Component component) {
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public void display() {
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
}