package view;

import javafx.stage.Stage;
import model.Album;

public interface Observer { // AlbumWindow functions as observer in this implementation
	
	public void update(); // Updates the observers
	public Album getAlbum(); // An observer to AlbumWindowManager must contain album/s
	public Stage getStage(); // An observer to AlbumWindowManager must be a javafx window
}
