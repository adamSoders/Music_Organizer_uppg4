package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import view.AlbumWindow;
import view.Observer;

public class AlbumWindowManager implements Subject, Iterable<Observer>{ // Manages AlbumWindows
	
	List<Observer> observers; // Contains all observers
	
	public AlbumWindowManager() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public boolean registerObserver(Observer o) { // Adds observer to list
		// TODO Auto-generated method stub
		return observers.add(o);
	}

	@Override
	public boolean removeObserver(Observer o) { // Removes observer from list
		// TODO Auto-generated method stub
		return observers.remove(o);
	}

	@Override
	public void notifyObservers() { // Notifies observers when the album structure is modified and updates them
		// TODO Auto-generated method stub
		for (Observer o : observers) {
			o.update();
		}
		
	}
	
	public List<Observer> returnObserversContainingAlbum(Album album) { // Used in controller to close observers containing album
		List<Observer> list = new ArrayList<Observer>();
		for(Observer o : observers) {
			if(o.getAlbum().equals(album)) {
				list.add(o);
			}
		}
		
		return list;
	}

	@Override
	public Iterator<Observer> iterator() {
		// TODO Auto-generated method stub
		return null;
	} 

	
}
