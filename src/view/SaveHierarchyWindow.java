package view;

import java.io.File;
import java.io.IOException;

import controller.MusicOrganizerController;
import javafx.stage.FileChooser;

public class SaveHierarchyWindow {

	MusicOrganizerWindow view;
	MusicOrganizerController controller;
	
	
	public SaveHierarchyWindow(MusicOrganizerWindow view, MusicOrganizerController controller) { // GUI that enables user to save album hierarchy to files
		this.view = view;
		this.controller = controller;
		
		// Creates FileChooser window
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save album hierarchy");
		
		fileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Serialized file", "*.ser"),
			     new FileChooser.ExtensionFilter("HTML Files", "*.htm")
			);
		
		File selectedFile = fileChooser.showSaveDialog(view.getPrimaryStage());
		try {
			controller.saveFile(selectedFile); // If user exports file, the task is first delegated to the controller
		}catch (IOException e) {
		
		}
		
	}
	
}
