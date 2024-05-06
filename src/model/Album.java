package model;
import java.util.*;

 

public class Album implements Iterable<Album>{

	private Set<SoundClip> soundclips = new HashSet<SoundClip>(); //set, eftersom album innehåller unika soundclips
	private Map<String,Album> subAlbums = new HashMap<String,Album>(); //albumets subalbum accesseras med hjälp av dess namn
	private String name; 
	private Album parentAlbum; //albumets förälder, ifall det har en
	
	public Album(String albumName, Album previous) {
		
		name = albumName;
		if(previous!=null) { //ser till att rootalbumet inte har en förälder
			parentAlbum = previous; //sparar till vem som man är förälder till

		} 
	}
	
	public Album(String albumname) { //consrtuctor for creating the root album (just for the project)
		
		name = albumname;
		parentAlbum = null;
	}
	
	public Map<String,Album> getSubAlbums() { //returnerar albumets hashmap av subalbum
		return subAlbums;

	}
	
	public List<Album> returnAllSubalbumsAsList(List<Album> albumsToBeReturned) { // Returns subalbum of album as a list
		List<Album> currentSubAlbums = new ArrayList<Album>(); // Subalbums of current album
		List<Album> subalbumsToBeReturned = albumsToBeReturned; // Accumulation of subalbums
		
		currentSubAlbums.addAll(subAlbums.values()); // Adds sublabums from hashmap as list
		subalbumsToBeReturned.addAll(currentSubAlbums);
			for(Album subAlbum2 : currentSubAlbums) { 
				subalbumsToBeReturned.addAll(subAlbum2.returnAllSubalbumsAsList(subalbumsToBeReturned)); // Recursively adds subalbums to accumulated list and gives accumulated list as parameter
				
			}
			
			return subalbumsToBeReturned;
	}
		

	public boolean addTrack(SoundClip track) { // sätter till ljudfilen till listan
		
		if(soundclips.add(track)) { //Försöker sätta till en fil till listan av filer
			if(parentAlbum!=null) { //Ifall den lyckades behöver den iterera uppåt igenom alla dens föreldrar
				parentAlbum.addTrack(track); //Lägger till tracken till sina föreldrar ifall de inte har den

			} return true; //returnerar true eftersom den lyckades lägga till på den här nivån

		} else {

			return false; //annars returnerar false

		}

	}
	
	public void addTracks(Set<SoundClip> tracks) { // sätter till ljudfilen till listan

		for (SoundClip track : tracks) {

		addTrack(track);

		}
	} 
	

	public Set<SoundClip> getTracks() { //returnerar albumets set av tracks
		return soundclips;
	}
	
	public Album getParent() { //getter f�r albumets f�r�lder
		return parentAlbum;
	}


	public boolean deleteTrack(SoundClip track) {
		
		if(containsTrack(track)) {
			soundclips.remove(track); //tar bort låten
			for (Map.Entry<String, Album> subAlbum : subAlbums.entrySet()) { //itererag genom albumets subalbum
				subAlbum.getValue().deleteTrack(track); //tar bort låten från alla dess subalbum

			}

			return true; //returnerar true eftersom den lyckades lägga till på den här nivån

		} else {

			return false;

		}

	}


	public boolean containsTrack(SoundClip track) {
		
		return soundclips.contains(track); //Granskar att låten finns i albumet

	}


	public void addSubAlbum(String albumName) {

		Album subAlbum = new Album(albumName,this); //Skapar ett nytt subAlbum och medför dens namn och förälderaralbum
		subAlbums.put(albumName,subAlbum); //Sparar det i hashmängden av subalbum för albumet medoden exekveras från

	}


	public boolean removeSubAlbum(String albumName) {
		
		if(containsSubAlbum(albumName)) { //ser till att albumet finns
			subAlbums.remove(albumName); //returnerar resultatet på bortagnigen

			return true;

		} else {

			return false; //ifall det inte finns returnar man false
		} 

	}

	public boolean containsSubAlbum(String albumName) {
		return subAlbums.containsKey(albumName); //Granskar ifall albumslistan innehåller albumet

	}
	
	public String toString() { //returnerar albumets namn
		return name;
	}

	public Iterator<Album> iterator() { //låter oss använda for each loop för att interera genom album
		// TODO Auto-generated method stub
		return null;

}


}