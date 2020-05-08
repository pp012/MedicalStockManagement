package medical0;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class search implements ActionListener{
	JLabel label1;
	JLabel label2;
	JButton back;
	JFrame frame;
	JPanel panel,panel1;
search(String name) throws ClassNotFoundException, SQLException
{
	label1=null;
	label2=null;
	Vector<Integer> v=new Vector<Integer>();
	String name1;
	name1=name;
	int flag;
	flag=0;
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException e)
	{
		System.out.println("class not found");
	}
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","visionware","visionware");
	java.sql.Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select *from tp1");
	while(rs.next())
	{
		if(rs.getString(1).equalsIgnoreCase(name1))
		{
			v.add(rs.getInt(2));
			flag=1;
		}
	}
	if(v.size()>0)
	{
		label1=new JLabel("THE STOCK CONTAINS "+v.size()+" DIFFERENT STOCKS OF "+name1+" WITH QUANTITY "+v+" RESPECTIVELY");
		label1.setFont(new Font("Arial",Font.PLAIN,25));
	}
	if(flag==0)
	{
		label2=new JLabel("MEDICINE NOT FOUND IN STOCK!");
		label2.setFont(new Font("Arial",Font.PLAIN,25));
	}
	panel=new JPanel();
	if(label1!=null)
	{
		panel.add(label1);
	}
	if(label2!=null)
	{
		panel.add(label2);
	}
	back=new JButton("MENU");
	back.addActionListener(this);
	back.setBackground(Color.red);
	back.setForeground(Color.white);
	back.setPreferredSize(new Dimension(200, 50));
	back.setFont(new Font("Arial",Font.BOLD,17));
	panel.setBackground(Color.cyan);
	panel1=new JPanel();
	panel1.setBackground(Color.cyan);
	panel1.add(back);
	frame=new JFrame();
	frame.add(panel,BorderLayout.NORTH);
	frame.add(panel1,BorderLayout.CENTER);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==back)
	{
		frame.dispose();
		new menuui();
	}
}
}
