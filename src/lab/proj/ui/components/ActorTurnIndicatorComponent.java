package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ActorTurnIndicatorComponent implements UIComponent {
	private List<Drawable> actors;
	@Override
	public void Draw(JComponent target) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		int topX = (int) (0.8f * target.getWidth());
		int topY = (int) (0.0f * target.getHeight());
		int width = (int) (0.2f * target.getWidth());
		int height = (int) (1.0f * target.getHeight());

		for (Drawable actor : actors) {
			JPanel actorPanel = new JPanel();

			actorPanel.setMinimumSize(new Dimension((int) (width * 0.9), 50));
			actor.Draw(actorPanel);

			actorPanel.setBackground(Color.decode("#808080"));

			panel.add(actorPanel);
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(topX, topY, width, height);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		target.add(scrollPane);
	}
	
	public void SetActors(List<Drawable> actors) {
		this.actors = actors;
	}
}