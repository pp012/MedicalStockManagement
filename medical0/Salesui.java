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

public class Salesui implements ActionListener {
	int flag;
		JFrame frame;
		JTextField name;
		JTextField quantity;
		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		JLabel mn;
		JLabel qn;
		JButton btn;
		JButton back;
		Salesui()
		{
			flag=0;
			frame=new JFrame("SELL");
			name=new JTextField(20);
			name.setPreferredSize(new Dimension(20, 30));
			name.setFont(new Font("Arial", Font.BOLD, 17));
			quantity=new JTextField(3);
			quantity.setPreferredSize(new Dimension(20, 30));
			quantity.setFont(new Font("Arial", Font.BOLD, 17));
			panel1=new JPanel();
			panel2=new JPanel();
			panel3=new JPanel();
			mn=new JLabel("ENTER MEDICINE NAME");
			mn.setFont(new Font("Arial",Font.PLAIN,25));
			qn=new JLabel("ENTER QUANTITY");
			qn.setFont(new Font("Arial",Font.PLAIN,25));
			btn=new JButton("SELL");
			btn.addActionListener(this);
			btn.setBackground(Color.orange);
			btn.setForeground(Color.white);
			btn.setPreferredSize(new Dimension(200, 50));
			btn.setFont(new Font("Arial",Font.BOLD,17));
			back=new JButton("MENU");
			back.addActionListener(this);
			back.setBackground(Color.red);
			back.setForeground(Color.WHITE);
			back.setPreferredSize(new Dimension(200, 50));
			back.setFont(new Font("Arial",Font.BOLD,17));
			panel1.add(mn);	
			panel1.add(name);
			panel1.setBackground(Color.cyan);
			panel2.add(panel1);
			panel2.add(qn);
			panel2.add(quantity);
			panel2.setBackground(Color.cyan);
			panel3.add(btn);
			panel3.add(back);
			panel3.setBackground(Color.cyan);
			frame.add(panel2,BorderLayout.NORTH);
			frame.add(panel3,BorderLayout.CENTER);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
		}
		public static void main(String[] args) {
			new Salesui();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name;
			int quantity;
			if(e.getSource()==btn)
			{
				name=this.name.getText();
				try
				{
					quantity=Integer.parseInt(this.quantity.getText());
				}
				catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "Enter valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					flag=1;
				}
				quantity=Integer.parseInt(this.quantity.getText());
				if(quantity==0)
				{
					flag=1;
					JOptionPane.showMessageDialog(null, "Enter valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
				if(flag==0)
				{
					try {
						new Sales(name,quantity);
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
			else if(e.getSource()==back)
			{
				frame.dispose();
				new menuui();
			}
		}
	}

