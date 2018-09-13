package be.fogoziv.launcherTest;

import java.awt.Font;

import javax.swing.JPanel;

public class PanelEdited extends JPanel{
	public PanelEdited() {
		TextFieldEdited tfe = new TextFieldEdited();
		PasswordTextFieldEdited ptfe = new PasswordTextFieldEdited();
		Font f = new Font("Font 1", 1, 18);
		tfe.setSize(100, 20);
		tfe.setFont(f);
		tfe.setLocation(350, 150);
		ptfe.setFont(f);
		ptfe.setLocation(350, 180);
		ptfe.setSize(100, 20);
		this.add(ptfe);
		this.add(tfe);
		this.setLayout(null);		
	}
}
