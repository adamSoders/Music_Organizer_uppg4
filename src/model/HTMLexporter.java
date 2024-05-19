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
		extraSpace = 0;
		try {
			buf = new BufferedWriter(new FileWriter(selectedFile));
			subAlbumFormating(root);
			buf.close();
		}catch(IOException e){
			
		}
	}
	
	public void subAlbumFormating(Album root) {
		try {
			if(extraSpace!=0) {
				buf.write("|");
				for(int i=0;i<extraSpace;i++) {
					buf.write("-");
				}
			}
			buf.write(root.toString()+": "+root.getTracks()+"<br />");
		}catch(IOException e) {
			
		}
		if(root.getSubAlbums()!=null) {
			for(Map.Entry<String, Album> subAlbum : root.getSubAlbums().entrySet()) {
				extraSpace++;
				subAlbumFormating(subAlbum.getValue());
			}
		}
		extraSpace--;
	}
}
