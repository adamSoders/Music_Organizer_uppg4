package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import controller.MusicOrganizerController;

public class HTMLexporter implements ExportBehaviour{
	
	MusicOrganizerController controller;
	BufferedWriter buf;
	int extraSpace;
	
	public HTMLexporter(File selectedFile, MusicOrganizerController controller) {
		this.controller = controller;
	}
	

	@Override
	public void exportFile(File selectedFile,Album root) { // Exports file as HTML 
		extraSpace = 0; //Counts Depth from root file
		try {
			buf = new BufferedWriter(new FileWriter(selectedFile));
			subAlbumFormating(root); //Start of writing loop
			buf.close(); //Commits changes to file
		}catch(IOException e){
			
		}
	}
	
	public void subAlbumFormating(Album root) {
		try {
			if(extraSpace!=0) { //If not the true root album
				buf.write("|");
				for(int i=0;i<extraSpace;i++) { //Adds extra dashes depending on depth
					buf.write("-");
				}
			}
			buf.write(root.toString()+": "+root.getTracks()+"<br />"); //Gets name of album and what tracks it contains
		}catch(IOException e) {
			
		}
		if(root.getSubAlbums()!=null) {
			for(Map.Entry<String, Album> subAlbum : root.getSubAlbums().entrySet()) { //For each album
				extraSpace++; //incrases depth by one
				subAlbumFormating(subAlbum.getValue()); //runs on sub albums
			}
		}
		extraSpace--; //deccrases depth by one
	}
}
