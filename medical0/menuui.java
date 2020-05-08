package medical0;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class menuui implements ActionListener {	
					JFrame frame;
					JPanel panel1;
					JPanel panel2;
					JPanel panel3;
					JButton search;
					JButton add;
					JButton expiry;
					JButton sell;
					JButton display1;
					JButton update;
					menuui()
					{
						frame=new JFrame("Medical Stock Management");
						panel1=new JPanel();
						panel2=new JPanel();
						panel3=new JPanel();
						search=new JButton("SEARCH MEDICINE");
						search.addActionListener(this);
						search.setBackground(Color.orange);
						search.setFont(new Font("Arial",Font.BOLD,17));
						search.setPreferredSize(new Dimension(200, 50));
						add=new JButton("ADD NEW STOCK");
						add.addActionListener(this);
						add.setBackground(Color.orange);
						add.setFont(new Font("Arial",Font.BOLD,17));
						add.setPreferredSize(new Dimension(200, 50));
						expiry=new JButton("CHECK FOR EXPIRY");
						expiry.addActionListener(this);
						expiry.setBackground(Color.orange);
						expiry.setFont(new Font("Arial",Font.BOLD,17));
						expiry.setPreferredSize(new Dimension(200, 50));
						sell=new JButton("SELL MEDICINE");
						sell.addActionListener(this);
						sell.setBackground(Color.orange);
						sell.setFont(new Font("Arial",Font.BOLD,17));
						sell.setPreferredSize(new Dimension(200, 50));
						display1=new JButton("DISPLAY STOCK");
						display1.addActionListener(this);
						display1.setBackground(Color.orange);
						display1.setFont(new Font("Arial",Font.BOLD,17));
						display1.setPreferredSize(new Dimension(200, 50));
						update=new JButton("UPDATE STOCK");
						update.addActionListener(this);
						update.setBackground(Color.orange);
						update.setFont(new Font("Arial",Font.BOLD,17));
						update.setPreferredSize(new Dimension(200, 50));
						panel1.add(add);
						panel1.add(sell);
						panel1.setBackground(Color.CYAN);
						panel2.add(panel1);
						panel2.add(search);
						panel2.setBackground(Color.CYAN);
						panel3.add(panel2);
						panel3.add(expiry);
						panel3.add(display1);
						panel3.add(update);
						panel3.setBackground(Color.CYAN);
						frame.add(panel3);
						frame.setSize(520, 170);
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
						frame.setVisible(true);
					}
					public static void main(String[] args) {
						new menuui();
					}
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(e.getSource()==add)
						{
							frame.dispose();
							new ui();
						}
						if(e.getSource()==sell)
						{
							frame.dispose();
							new Salesui();
						}
						if(e.getSource()==search)
						{
							frame.dispose();
							new searchui();
						}
						if(e.getSource()==expiry)
						{
							frame.dispose();
							new expiryui();
						}
						if(e.getSource()==display1)
						{
							frame.dispose();
							try {
								new display();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(e.getSource()==update)
						{
							frame.dispose();
							new updatem();
						}
}
}