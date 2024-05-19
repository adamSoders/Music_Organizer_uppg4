package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class FileMenu extends HBox {

	MusicOrganizerWindow view;
	
	public FileMenu(MusicOrganizerWindow view) { // Button with dropdown menu that enables user to save and load hierarchy
		super();
		this.view = view;
		
		// MenuItem that opens save dialog when clicked on
		MenuItem save = new MenuItem("Save");
		save.setOnAction(e -> {
			SaveHierarchyWindow fileSaveWindow = new SaveHierarchyWindow(view);
		});
		
		// MenuItem that opens load dialog when clicked on
		MenuItem load = new MenuItem("Load Hierarchy");
		load.setOnAction(e -> {
			LoadHierarchyWindow fileLoadWindow = new LoadHierarchyWindow(view);
		});
		
		MenuButton menu1 = new MenuButton("File", null, save, load);
		
		
		this.getChildren().add(menu1);

		
	}
}
