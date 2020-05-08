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
public class updatem implements ActionListener {	
						JFrame frame;
						JPanel panel1;
						JPanel panel2;
						JPanel panel3;
						JPanel panel4;
						JButton name;
						JButton quantity;
						JButton expiry;
						JButton mrp;
						JButton cost;
						JButton back;
						updatem()
						{
							frame=new JFrame("Medical Stock Management");
							panel1=new JPanel();
							panel2=new JPanel();
							panel3=new JPanel();
							panel4=new JPanel();
							quantity=new JButton("UPDATE QUANTITY");
							quantity.addActionListener(this);
							quantity.setBackground(Color.orange);
							quantity.setFont(new Font("Arial",Font.BOLD,17));
							quantity.setPreferredSize(new Dimension(200, 50));
							name=new JButton("UPDATE NAME");
							name.addActionListener(this);
							name.setBackground(Color.orange);
							name.setFont(new Font("Arial",Font.BOLD,17));
							name.setPreferredSize(new Dimension(200, 50));
							expiry=new JButton("UPDATE EXPIRY");
							expiry.addActionListener(this);
							expiry.setBackground(Color.orange);
							expiry.setFont(new Font("Arial",Font.BOLD,17));
							expiry.setPreferredSize(new Dimension(200, 50));
							cost=new JButton("UPDATE COST");
							cost.addActionListener(this);
							cost.setBackground(Color.orange);
							cost.setFont(new Font("Arial",Font.BOLD,17));
							cost.setPreferredSize(new Dimension(200, 50));
							mrp=new JButton("UPDATE SELLING PRICE");
							mrp.addActionListener(this);
							mrp.setBackground(Color.orange);
							mrp.setFont(new Font("Arial",Font.BOLD,17));
							mrp.setPreferredSize(new Dimension(250, 50));
							back=new JButton("MENU");
							back.addActionListener(this);
							back.setBackground(Color.red);
							back.setForeground(Color.white);
							back.setFont(new Font("Arial",Font.BOLD,17));
							back.setPreferredSize(new Dimension(200, 50));
							panel1.add(name);
							panel1.add(quantity);
							panel1.setBackground(Color.CYAN);
							panel2.add(panel1);
							panel2.add(expiry);
							panel2.setBackground(Color.CYAN);
							panel3.add(panel2);
							panel3.add(cost);
							panel3.add(mrp);
							panel3.setBackground(Color.CYAN);
							panel4.add(back);
							panel4.setBackground(Color.cyan);
							frame.add(panel3,BorderLayout.NORTH);
							frame.add(panel4,BorderLayout.CENTER);
							frame.setSize(700, 170);
							frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
							frame.setVisible(true);
						}
						public static void main(String[] args) {
							new updatem();
						}
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(e.getSource()==cost)
							{
								frame.dispose();
								try {
									new costui();
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
							if(e.getSource()==name)
							{
								frame.dispose();
								try {
									new updatename();
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
							if(e.getSource()==quantity)
							{
								frame.dispose();
								try {
									new quantityui();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if(e.getSource()==expiry)
							{
								frame.dispose();
								try {
									new uiexpiry();
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
							if(e.getSource()==mrp)
							{
								frame.dispose();
								try {
									new mrpui();
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
							if(e.getSource()==back)
							{
								frame.dispose();
								new menuui();
							}
	}
	}
