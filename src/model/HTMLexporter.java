package model;

import java.io.File;

import controller.MusicOrganizerController;

public class HTMLexporter implements ExportBehaviour{
	
	MusicOrganizerController controller;
	
	public HTMLexporter(MusicOrganizerController controller) {
		this.controller = controller;
	}
	

	@Override
	public void exportFile(Album root) { // Exports file as HTML 
		// TODO Auto-generated method stub
		
	}

}
