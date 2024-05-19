package view;

import java.io.File;

import controller.MusicOrganizerController;
import javafx.stage.FileChooser;

public class LoadHierarchyWindow {
	
	MusicOrganizerWindow view;
	MusicOrganizerController controller;
	
	public LoadHierarchyWindow(MusicOrganizerWindow stage, MusicOrganizerController controller) { // GUI that enables user to load album hierarchy from files
		this.view = view;
		this.controller = controller;
		
		// Creates FileChooser window
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load album hierarchy");
		
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized file", "*.ser"));
		
		File selectedFile = fileChooser.showOpenDialog(view.getPrimaryStage());
		controller.loadFile(selectedFile);
	}
}
