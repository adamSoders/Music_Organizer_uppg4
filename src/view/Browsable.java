package view;

import java.util.List;

import model.SoundClip;

public interface Browsable { //interface that enables the user to see and select soundclips

	public List<SoundClip> getSelectedSoundClips(); //user can select soundclips from window
	public SoundClipListView createSoundClipListView(); //enables window to display soundclips
		
	
}
