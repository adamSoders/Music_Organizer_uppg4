package model;

import java.io.File;

public interface ExportBehaviour { // Implemented by different classes to that export files in certain ways

	public void exportFile(Album root);
}
