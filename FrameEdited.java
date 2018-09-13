package be.fogoziv.launcherTest;

import javax.swing.JFrame;

public class FrameEdited extends JFrame{
	public FrameEdited() {
		PanelEdited pe = new PanelEdited();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setContentPane(pe);
	}
}
