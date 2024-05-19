package model;

import controller.MusicOrganizerController;

public class SerializeHierarchy implements ExportBehaviour{

	MusicOrganizerController controller;
	
	SerializeHierarchy(MusicOrganizerController controller) {
		this.controller = controller;
	}
	
	public void exportFile() {
		
	}
}
