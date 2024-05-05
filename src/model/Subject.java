package model;

import view.Observer;

public interface Subject { //albumWindowManager implementerar detta interface
	
	public boolean registerObserver(Observer o); //metoder för att hantera observatörer enligt observer pattern
	public boolean removeObserver(Observer o);
	public void notifyObservers();

}
