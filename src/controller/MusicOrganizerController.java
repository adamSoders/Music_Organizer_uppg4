package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Album;
import model.AlbumWindowManager;
import model.SoundClip;
import model.SoundClipBlockingQueue;
import model.SoundClipLoader;
import model.SoundClipPlayer;
import view.AlbumWindow;
import view.MusicOrganizerWindow;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private AlbumWindowManager windowManager;
	
	public MusicOrganizerController() {

		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		// Create the window manager
		windowManager = new AlbumWindowManager();
				
		// Create a separate thread for the sound clip player and start it
		
		(new Thread(new SoundClipPlayer(queue))).start();
	}
	
	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {

		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);

		// TODO: Add the loaded sound clips to the root album

		root.addTracks(clips);

		return clips;

		}
	
	public void registerView(MusicOrganizerWindow view) {
		this.view = view;
	}
	
	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){ //TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		if(view.getSelectedAlbum() != null) {
			String newName = view.promptForAlbumName();
			if(newName != null) {
				view.getSelectedAlbum().addSubAlbum(newName);
				view.onAlbumAdded(view.getSelectedAlbum().getSubAlbums().get(newName));
			}	
		}
	}
		
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ //TODO Update parameters if needed
		// TODO: Add your code here
		if(view.getSelectedAlbum() != null) { //granskar att ett album �r valt
			if(view.getSelectedAlbum().getParent() != null) { //granskar att albumet �r ett subalbum
				view.getSelectedAlbum().getParent().removeSubAlbum(view.getSelectedAlbum().toString()); //tar bort valda albumet genom att ta bort albumet fr�n f�r�lderns subalbum
				view.onAlbumRemoved(); 
				windowManager.notifyObservers(); // Method can alter structure of shown albums
				
			}
			
		}
		
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		if(view.getSelectedAlbum() != null) { //kan enbart adda tracks till ett subalbum
			if(view.getSelectedAlbum().getParent() != null) {
				for (SoundClip track : view.getSelectedSoundClips()) { //l�gger till valda tracken till valda albumet och eventuellt till dess f�r�ldrar
					view.getSelectedAlbum().addTrack(track);
				}
				
			view.onClipsUpdated();
			windowManager.notifyObservers(); // Method can alter structure of shown albums
			}
		}
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		if(view.getSelectedAlbum() != null) { //kan enbart radera tracks fr�n ett subalbum
			if(view.getSelectedAlbum().getParent() != null) {
				for (SoundClip track : view.getSelectedSoundClips()) { //tar bort tracken fr�n valda albumet och eventuellt fr�n dess subalbum
					view.getSelectedAlbum().deleteTrack(track); 
				}
			view.onClipsUpdated();
			windowManager.notifyObservers(); // Method can alter structure of shown albums
			}
		}
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(List<SoundClip> l){ // Modified to take list of soundClips as a parameter so that any Browsable class can add selected soundclips to the queue in the main window
		queue.enqueue(l);
		for(int i=0;i<l.size();i++) {
			view.displayMessage("Playing " + l.get(i));
		}
	}

	public void createAlbumWindow(Album selectedAlbum) { // Creates albumWindow in AlbumWindowmanager
		// TODO Auto-generated method stub
		AlbumWindow window = new AlbumWindow(selectedAlbum); 
		windowManager.registerObserver(window); // Adds window as observer
		
		
	}

	public void closeWindow(AlbumWindow albumWindow) { // Closes albumWindow and removes it from list of observers in the windowmangager
		// TODO Auto-generated method stub
		albumWindow.getStage().close();
		windowManager.removeObserver(albumWindow);
		
		
	}
	
	public void closeWindowsContainingAlbum() { //closes windows containing a particular album
		
	}
}
	
