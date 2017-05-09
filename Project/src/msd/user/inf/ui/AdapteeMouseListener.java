package msd.user.inf.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class implements the MouseListener interface and 
 * acts as an adaptee to our custom Mouse Listener
 */
public class AdapteeMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	return;	
	}

}
