package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import controller.MusicOrganizerController;

public class SerializeHierarchy implements ExportBehaviour{

	MusicOrganizerController controller;
	
	public SerializeHierarchy(File selectedFile, MusicOrganizerController controller) {
		this.controller = controller;
	}
	
	public void exportFile(File selectedFile, Album root) { // Serializes root album
		
		try {
			FileOutputStream fileStream = new FileOutputStream(selectedFile);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(root);
			
			objectStream.close();
			fileStream.close();
			
		} catch (IOException i) {
		         i.printStackTrace();
		}
		
		
	}
}
