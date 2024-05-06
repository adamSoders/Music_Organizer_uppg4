package view;

import java.util.List;

import controller.MusicOrganizerController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Album;
import model.SoundClip;

public class AlbumWindow implements Observer, Browsable{ //fönster som visar upp innehållet av ett album 
	
	private Album album; //albumet som visas
	private static MusicOrganizerController controller; 
	private SoundClipListView soundClipListView; // The view of soundclips in the window 
	private Stage stage; 
	
	
	public AlbumWindow(Album album, MusicOrganizerWindow view) { // Sets up window
		this.album = album;
		
		controller = new MusicOrganizerController();
		controller.registerView(view);
		
		stage = new Stage();
		stage.setTitle(album.toString());
		
		soundClipListView = createSoundClipListView();
		Scene scene = new Scene(soundClipListView);
		
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
		
		
	}
	
	public void update() { // Window shows the contents of its album, closes window if album ceases to exist
		soundClipListView.display(album);
	}

	@Override
	public List<SoundClip> getSelectedSoundClips(){ // Returns selected soundclips 
		return soundClipListView.getSelectedClips();
	}

	@Override
	public SoundClipListView createSoundClipListView() {
		SoundClipListView v = new SoundClipListView();
		v.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		v.display(album);
		
		v.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) {
					// This code gets invoked whenever the user double clicks in the sound clip table
					// Gives selected soundclip to the main window to be played
					controller.playSoundClips(getSelectedSoundClips());
				}
				
			}
			
		});
		
		return v;
		// TODO Auto-generated method stub
	}

	public Stage getStage() { // Returns the stage (used for closing a window in controller)
		// TODO Auto-generated method stub
		return stage;
	}
	
	public Album getAlbum() { // Used in AlbumWindowManager to check if any observers contain a particular album 
		return album;
	}
}
