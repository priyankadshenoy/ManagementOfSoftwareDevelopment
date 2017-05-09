package msd.user.inf.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The new mouse listener that will be used in the SearchPanel:
 * Using the Adapter design pattern
 */
public class ExportMouseListener extends AdapteeMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		return;
	}
}
