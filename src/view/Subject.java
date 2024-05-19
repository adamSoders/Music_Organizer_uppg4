package view;

public interface Subject { //albumWindowManager implementerar detta interface
	
	public boolean registerObserver(Observer o); //metoder f�r att hantera observat�rer enligt observer pattern
	public boolean removeObserver(Observer o);
	public void notifyObservers();

}
