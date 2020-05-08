package medical0;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class searchui implements ActionListener {	
				JFrame frame;
				JPanel panel2,panel1;
				JLabel search1;
				JTextField key;
				JButton btn;
				JButton back;
				searchui()
				{
					frame=new JFrame("Search Medicine");
					panel1=new JPanel();
					btn=new JButton("SEARCH");
					btn.addActionListener(this);
					btn.setBackground(Color.orange);
					btn.setForeground(Color.white);
					btn.setPreferredSize(new Dimension(200, 50));
					btn.setFont(new Font("Arial",Font.BOLD,17));
					back=new JButton("MENU");
					back.addActionListener(this);
					back.setBackground(Color.red);
					back.setForeground(Color.white);
					back.setPreferredSize(new Dimension(200, 50));
					back.setFont(new Font("Arial",Font.BOLD,17));
					key=new JTextField(15);
					key.setPreferredSize(new Dimension(20, 30));
					key.setFont(new Font("Arial", Font.BOLD, 17));
					search1=new JLabel("ENTER MEDICINE NAME");
					search1.setFont(new Font("Arial",Font.PLAIN,30));
					panel1.add(search1);
					panel1.add(key);
					panel2=new JPanel();
					panel2.add(btn);
					panel2.add(back);
					panel1.setBackground(Color.cyan);
					panel2.setBackground(Color.cyan);
					frame.add(panel1,BorderLayout.NORTH);
					frame.add(panel2,BorderLayout.CENTER);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				}
				public static void main(String[] args) {
					new searchui();
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String name;
					if(e.getSource()==btn)
					{
					name=this.key.getText();
							try {
							new search(name);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						frame.dispose();
					}
					if(e.getSource()==back)
					{
						frame.dispose();
						new menuui();
					}
				}
	}

