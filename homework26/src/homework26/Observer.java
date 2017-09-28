package homework26;

@FunctionalInterface
public interface Observer {
	public abstract void update(Observable o, Object arg) ;
}
