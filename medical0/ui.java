package medical0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ui implements ActionListener {
	JFrame frame;
	JTextField name;
	JTextField quantity;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3,panel5;
	JPanel panel4;
	JLabel expiry;
	JLabel mn;
	JLabel c;
	JLabel s;
	JTextField cost,sp;
	JLabel qn;
	JButton btn;
	JButton back;
	JTextField month;
	JTextField year;
	ui()
	{
		frame=new JFrame("ADD STOCK");
		name=new JTextField(20);
		name.setPreferredSize(new Dimension(20, 30));
		name.setFont(new Font("arial", Font.BOLD, 17));
		quantity=new JTextField(3);
		quantity.setPreferredSize(new Dimension(20, 30));
		quantity.setFont(new Font("arial", Font.BOLD, 17));
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		c=new JLabel("ENTER COST");
		c.setFont(new Font("Arial",Font.PLAIN,30));
		cost=new JTextField(5);
		cost.setPreferredSize(new Dimension(20, 30));
		cost.setFont(new Font("arial", Font.BOLD, 17));
		back=new JButton("Menu");
		back.addActionListener(this);
		back.setBackground(Color.red);
		back.setForeground(Color.WHITE);
		back.setPreferredSize(new Dimension(200, 50));
		back.setFont(new Font("Arial",Font.BOLD,17));
		mn=new JLabel("ENTER MEDICINE NAME");
		mn.setFont(new Font("Arial",Font.PLAIN,30));
		qn=new JLabel("ENTER QUANTITY");
		qn.setFont(new Font("Arial",Font.PLAIN,30));
		expiry=new JLabel("ENTER EXPIRY DATE");
		expiry.setFont(new Font("Arial",Font.PLAIN,30));
		month=new JTextField(2);
		year=new JTextField(4);
		month.setDocument(new limit(2));
		year.setDocument(new limit(4));
		year.setPreferredSize(new Dimension(20, 30));
		year.setFont(new Font("arial", Font.BOLD, 17));
		month.setPreferredSize(new Dimension(20, 30));
		month.setFont(new Font("arial", Font.BOLD, 17));
		s=new JLabel("ENTER SELLING PRICE");
		s.setFont(new Font("Arial",Font.PLAIN,30));
		sp=new JTextField(5);
		sp.setPreferredSize(new Dimension(20, 30));
		sp.setFont(new Font("arial", Font.BOLD, 17));
		btn=new JButton("ADD STOCK");
		btn.addActionListener(this);
		btn.setBackground(Color.ORANGE);
		btn.setForeground(Color.WHITE);
		btn.setPreferredSize(new Dimension(200, 50));
		btn.setFont(new Font("Arial",Font.BOLD,17));
		panel5.add(month);
		panel5.add(year);
		panel5.setBackground(Color.CYAN);
		panel1.setLayout(new GridLayout(5, 2, 3, 10));
		panel1.add(mn);
		panel1.add(name);
		panel1.add(qn);
		panel1.add(quantity);
		panel1.add(expiry);
		panel1.add(panel5);
		panel1.add(c);
		panel1.add(cost);
		panel1.add(s);
		panel1.add(sp);
		panel1.setBackground(Color.CYAN);
		panel3.add(btn);
		panel3.add(back);
		panel3.setBackground(Color.CYAN);
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel3);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new ui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name=null;
		int quantity;
		int month;
		int year;
		int cost;
		int sp;
		int flag;
		flag=0;
		if(e.getSource()==btn)
		{
			name=this.name.getText();
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
			try
			{
				cost=Integer.parseInt(this.cost.getText());
			}
			catch(NumberFormatException e1)
			{
				flag=1;
				JOptionPane.showMessageDialog(null, "Enter valid cost", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
			try
			{
				sp=Integer.parseInt(this.sp.getText());
			}
			catch(NumberFormatException e1)
			{
				flag=1;
				JOptionPane.showMessageDialog(null, "Enter valid Selling Price", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
			try
			{
				quantity=Integer.parseInt(this.quantity.getText());
			}
			catch(NumberFormatException e1)
			{
				flag=1;
				JOptionPane.showMessageDialog(null, "Enter valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
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
				cost=Integer.parseInt(this.cost.getText());
				sp=Integer.parseInt(this.sp.getText());
				quantity=Integer.parseInt(this.quantity.getText());
	
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				new connect(name,quantity,month,year,cost,sp);
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