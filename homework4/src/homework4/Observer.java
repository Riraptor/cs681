package homework4;
@FunctionalInterface
public interface Observer {
	public abstract void update(Observable o, Object arg) ;
}
