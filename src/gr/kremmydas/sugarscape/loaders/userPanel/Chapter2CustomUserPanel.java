package gr.kremmydas.sugarscape.loaders.userPanel;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;



public class Chapter2CustomUserPanel implements CustomPanelLoader {

	public Chapter2CustomUserPanel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel loadPanel() {
		JPanel panel = new JPanel();

		panel.setBorder(new TitledBorder("Chapter 2 switches"));
		panel.setLayout(new GridLayout(6, 2, 15, 0));// 6 rows, 2 cols

		final JLabel labelAgentsNum = new JLabel("Number of Agents");
		final JSlider agentsNum = new JSlider(JSlider.HORIZONTAL,10, 600, 400); 
		agentsNum.setMajorTickSpacing(100);
		agentsNum.setMinorTickSpacing(50);
		agentsNum.setPaintTicks(true);
		agentsNum.setPaintLabels(true);		
		agentsNum.setFont(new Font("Serif", Font.PLAIN, 9));
		panel.add(labelAgentsNum,BorderLayout.PAGE_START);
		panel.add(agentsNum,BorderLayout.AFTER_LAST_LINE);
		
		
		final JLabel labelVision = new JLabel("Vision Range");
		final JSlider visionSlider = new JSlider(JSlider.HORIZONTAL,1, 10, 1); 
		visionSlider.setMajorTickSpacing(2);
		visionSlider.setMinorTickSpacing(1);
		visionSlider.setPaintTicks(true);
		visionSlider.setPaintLabels(true);		
		visionSlider.setFont(new Font("Serif", Font.PLAIN, 9));
		panel.add(labelVision,BorderLayout.AFTER_LAST_LINE);
		panel.add(visionSlider,BorderLayout.AFTER_LAST_LINE);
		
		return panel;
	}

}
