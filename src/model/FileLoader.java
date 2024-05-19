package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import controller.MusicOrganizerController;

public class FileLoader { // Deserializes file
	
	File file;
	MusicOrganizerController controller;

	public FileLoader(File selectedFile, MusicOrganizerController controller) { 
		// TODO Auto-generated constructor stub
		this.file = selectedFile;
		this.controller = controller;
	}

	public Album deserialize() {
		// TODO Auto-generated method stub
		Album newRoot;
		try {
	         FileInputStream fileStream = new FileInputStream(file);
	         ObjectInputStream objectStream = new ObjectInputStream(fileStream);
	         newRoot = (Album) objectStream.readObject();
	         objectStream.close();
	         fileStream.close();
	         
		} catch (IOException i) {
	         i.printStackTrace();
	         return null;
	         
	    } catch (ClassNotFoundException c) {
	    	 c.printStackTrace();
	    	 return null;
	      }
		return newRoot;
	}
	

}
