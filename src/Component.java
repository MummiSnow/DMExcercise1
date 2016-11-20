import java.util.Collection;
import java.util.List;

public abstract class Component {
	
	private List<Component> children;
	
	public void addChild(Component component){
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public String getName(){
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public void setParent(Component parent) {
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public void removeChild(Component component) {
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public boolean isRoot() {
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
	public boolean isChild() {
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}

	public Component getFather(){
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}

	public Collection<Component> getChildren(){
		throw new UnsupportedOperationException("Current Operation is not supported for this object");
	}
	
}
