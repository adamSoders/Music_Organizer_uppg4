package view;

import java.io.File;

import javafx.stage.FileChooser;

public class SaveHierarchyWindow {

	MusicOrganizerWindow stage;
	
	
	public SaveHierarchyWindow(MusicOrganizerWindow stage) { // GUI that enables user to save album hierarchy to files
		this.stage = stage;
		
		// Creates FileChooser window
		FileChooser filechooser = new FileChooser();
		File selectedFile = filechooser.showSaveDialog(stage.getPrimaryStage());
		
		filechooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Serialized Files", "*.ser"),
			     new FileChooser.ExtensionFilter("HTML Files", "*.htm")
			);
	}
	
	
}
