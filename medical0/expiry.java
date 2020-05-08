package medical0;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class expiry implements ActionListener{
	JLabel soon,label1,label2,soon1;
	JPanel panel,panel1;
	JFrame frame; 
	JButton back;
	expiry(int month,int year) throws ClassNotFoundException, SQLException
{
	panel=new JPanel();
	frame=new JFrame();
	soon=null;
	soon1=null;
	label1=null;
	label2=null;
	int m;
	int y,flag1;
	int m1,y1;
	int loss;
	loss=0;
	m=month;
	y=year;
	flag1=0;
	String name1;
	Vector<String> v=new Vector<String>();
	Vector<Integer> v1=new Vector<Integer>();
	Vector<String> v3=new Vector<String>();
	Vector<Integer> v4=new Vector<Integer>();
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException e)
	{
		System.out.println("class not found");
	}
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","visionware","visionware");
	java.sql.Statement stmt=con.createStatement();
	PreparedStatement stm;
	ResultSet rs=stmt.executeQuery("select *from tp1");
	while(rs.next())
	{
		name1=rs.getString(1);
		m1=rs.getInt(3);
		y1=rs.getInt(4);
		if(rs.getInt(4)>y)
		{
			continue;
		}
		else if(rs.getInt(4)<y)
		{
			loss=loss+(rs.getInt(5)*rs.getInt(2));
			v3.add(rs.getString(1));
			v4.add(rs.getInt(2));
			stm=con.prepareStatement("update tp1 set quantity=? where name=? and month=? and year=?");
			stm.setInt(1, 0);
			stm.setString(2, name1);
			stm.setInt(3, m1);
			stm.setInt(4, y1);
			stm.executeUpdate();
		}
		else
		{
			if(rs.getInt(3)<m)
			{
				v3.add(rs.getString(1));
				v4.add(rs.getInt(2));
				loss=loss+(rs.getInt(5)*rs.getInt(2));
				stm=con.prepareStatement("update tp1 set quantity=? where name=? and month=? and year=?");
				stm.setInt(1, 0);
				stm.setString(2, name1);
				stm.setInt(3, m1);
				stm.setInt(4, y1);
				stm.executeUpdate();
			}
			else if(rs.getInt(3)==m)
			{
				v.add(rs.getString(1));
				v1.add(rs.getInt(2));
				flag1=1;
			}
		}
			
	}
	stmt.executeQuery("delete from tp1 where quantity=0");
	if(flag1==1)
	{
		soon=new JLabel("THE PRODUCTS EXPIRING THIS MONTH ARE "+v+" .");
		soon1=new JLabel("THEIR QUANTITIES ARE "+v1+" RESPECTIVELY.");
		soon.setFont(new Font("Arial", Font.BOLD, 25));
		soon1.setFont(new Font("Arian", Font.BOLD, 25));
	}
	
	if(v3.isEmpty())
	{
		label1=new JLabel("NO PRODUCTS HAVE EXPIRED! ");
		label1.setFont(new Font("Arial", Font.BOLD, 25));
	}
	else if(!v3.isEmpty())
	{
		label1=new JLabel("THE PRODUCTS EXPIRED ARE "+v3+" AND THEIR QUANTITIES "+v4+" RESPECTIVELY.");
		label1.setFont(new Font("Arial", Font.BOLD, 25));
	}
	if(loss==0)
	{
		label2=new JLabel("NO LOSS FROM THE STOCK CLEARING!");
		label2.setFont(new Font("Arial", Font.BOLD, 25));
	}
	else
	{
		label2=new JLabel("LOSS FROM THE STOCK CLEARING IS $"+loss);
		label2.setFont(new Font("Arial", Font.BOLD, 25));
	}
	if(soon!=null)
	{
		panel.add(soon);
	}
	if(soon1!=null)
	{
		panel.add(soon1);
	}
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
	panel1=new JPanel();
	panel1.add(back);
	panel.setBackground(Color.cyan);
	panel1.setBackground(Color.cyan);
	frame.add(panel,BorderLayout.NORTH);
	frame.add(panel1, BorderLayout.CENTER);
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
