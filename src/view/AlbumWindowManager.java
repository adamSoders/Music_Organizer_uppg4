package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Album;

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
	
	public List<Observer> returnObserversToBeDeleted(Album album) { // Used in controller to close observers containing album or subalbum of deleted album
		
		List<Album> albumList = new ArrayList<Album>(); // Contains subalbums of deleted album
		List<Album> nullList = new ArrayList<Album>(); // Empty list that accumulates subalbums 
		List<Observer> toBeReturned = new ArrayList<Observer>(); // Observers that contain any of those subalbums
		
		albumList.add(album); // Delete window of selected album
		
		albumList.addAll(album.returnAllSubalbumsAsList(nullList)); // Adds all subalbums of selected album to the albumlist
		
		for(Observer o : observers) { // checks if any observer contains any of the deleted subalbums and adds that observer to be returned
			if(albumList.contains(o.getAlbum())) { 
				toBeReturned.add(o);
			}
		}
		
		return toBeReturned;
	}
		

	@Override
	public Iterator<Observer> iterator() {
		// TODO Auto-generated method stub
		return null;
	} 

	
}
