package view;

import java.io.File;

import javafx.stage.FileChooser;

public class LoadHierarchyWindow {
	
	MusicOrganizerWindow stage;
	
	public LoadHierarchyWindow(MusicOrganizerWindow stage) { // GUI that enables user to load album hierarchy from files
		this.stage = stage;
		
		// Creates FileChooser window
		FileChooser filechooser = new FileChooser();
		File selectedFile = filechooser.showOpenDialog(stage.getPrimaryStage());
	}
}
