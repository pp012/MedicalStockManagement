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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class expiryui implements ActionListener {
			int flag;
			JFrame frame;
			JPanel panel1;
			JPanel panel2;
			JPanel panel3;
			JLabel expiry1;
			JTextField month;
			JTextField year;
			JButton btn;
			JButton back;
			expiryui()
			{
				flag=0;
				frame=new JFrame("CHECK EXPIRY");
				panel1=new JPanel();
				panel2=new JPanel();
				panel3=new JPanel();
				expiry1=new JLabel("ENTER TODAY'S DATE");
				expiry1.setFont(new Font("Arial",Font.PLAIN,30));
				month=new JTextField(2);
				year=new JTextField(4);
				month.setDocument(new limit(2));
				month.setPreferredSize(new Dimension(20, 30));
				month.setFont(new Font("Arial", Font.BOLD, 17));
				year.setDocument(new limit(4));
				year.setPreferredSize(new Dimension(20, 30));
				year.setFont(new Font("Arial", Font.BOLD, 17));
				btn=new JButton("CHECK EXPIRY");
				btn.addActionListener(this);
				btn.setBackground(Color.orange);
				btn.setForeground(Color.WHITE);
				btn.setPreferredSize(new Dimension(200, 50));
				btn.setFont(new Font("Arial",Font.BOLD,17));
				back=new JButton("MENU");
				back.addActionListener(this);
				back.setBackground(Color.red);
				back.setForeground(Color.white);
				back.setPreferredSize(new Dimension(200, 50));
				back.setFont(new Font("Arial",Font.BOLD,17));
				panel1.add(expiry1);
				panel1.add(month);
				panel1.add(year);
				panel1.setBackground(Color.cyan);
				panel2.add(btn);
				panel2.add(back);
				panel2.setBackground(Color.cyan);
				frame.add(panel1,BorderLayout.NORTH);
				frame.add(panel2,BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
			public static void main(String[] args) {
				new expiryui();
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				if(e.getSource()==btn)
				{
					int month,year;
					try
					{
						month=Integer.parseInt(this.month.getText());
					}
					catch(NumberFormatException e1)
					{
						flag=1;
						JOptionPane.showMessageDialog(null, "Enter valid month", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
					try
					{
						year=Integer.parseInt(this.year.getText());

					}
					catch(NumberFormatException e1)
					{
						flag=1;
						JOptionPane.showMessageDialog(null, "Enter valid year", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}

					month=Integer.parseInt(this.month.getText());
					if(month>12 || month==0)
					{
						flag=1;
						JOptionPane.showMessageDialog(null, "Enter valid month", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
					year=Integer.parseInt(this.year.getText());
					if(year==0000)
					{
						flag=1;
						JOptionPane.showMessageDialog(null, "Enter valid year", "Invalid Input", JOptionPane.ERROR_MESSAGE);	
					}
					if(flag==0)
					{
					month=Integer.parseInt(this.month.getText());
					year=Integer.parseInt(this.year.getText());
						try {
						new expiry(month, year);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					frame.dispose();
					}
				}
				if(e.getSource()==back)
				{
					frame.dispose();
					new menuui();
				}
			}
}
