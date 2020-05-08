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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sales implements ActionListener{
	JFrame f;
	JPanel panel,panel1;
	JButton back;
	JLabel l;
 Sales(String name, int quantity) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
	
		String name1;
		int quantity1;
		int flag;
		int temp;
		int m,y;
		int profit;
		profit=0;
		name1=name;
		quantity1=quantity;
		flag=0;
		JLabel l1 = null;
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
			if(rs.getString(1).equalsIgnoreCase(name1))
			{
				temp=rs.getInt(2);
				temp=temp-quantity1;
				m=rs.getInt(3);
				y=rs.getInt(4);
				if(temp<0)
				{
					profit=profit + (rs.getInt(6)-rs.getInt(5))*rs.getInt(2);
					quantity1=Math.abs(temp);
					stm=con.prepareStatement("update tp1 set quantity=? where name=? and month=? and year=?");
					stm.setInt(1, 0);
					stm.setString(2, name1);
					stm.setInt(3, m);
					stm.setInt(4, y);
					stm.executeUpdate();
					flag=1;
				}
				else
				{
					profit=profit + (rs.getInt(6)-rs.getInt(5))*rs.getInt(2);
					stm=con.prepareStatement("update tp1 set quantity=? where name=? and month=? and year=?");
					stm.setInt(1, temp);
					stm.setString(2, name1);
					stm.setInt(3, m);
					stm.setInt(4, y);
					stm.executeUpdate();
					flag=2;
					break;
				}
			}	
		}
		stmt.executeQuery("delete from tp1 where quantity=0");
		if(profit>0)
		{
		l=new JLabel("PROFIT FROM THE TRANSACTION IS $"+profit);
		l.setFont(new Font("Arial",Font.BOLD,25));
		}
		else if(profit==0)
		{
			l=new JLabel("THERE IS NO PROFIT FROM THE TRANSACTION.");
			l.setFont(new Font("Arial",Font.PLAIN,30));
		}
		else
		{
			l=new JLabel("LOSS FROM THE TRANSACTION IS $"+profit+"!");
			l.setFont(new Font("Arial",Font.PLAIN,30));
		}
		if(flag==0)
			{
				l1=new JLabel("PRODUCT NOT FOUND. ");
				l1.setFont(new Font("Arial",Font.PLAIN,30));
			}
		if(flag==1)
		{
			l1=new JLabel("QUANTITY OF PRODUCT INSUFFICIENT BY "+quantity1+" order new stock");
			l1.setFont(new Font("Arial",Font.PLAIN,30));
		}
	back=new JButton("MENU");
	back.addActionListener(this);
	back.setBackground(Color.red);
	back.setForeground(Color.white);
	back.setPreferredSize(new Dimension(200, 50));
	back.setFont(new Font("Arial",Font.BOLD,17));
	panel=new JPanel();
	panel.add(l);
	if(l1!=null)
	{
		panel.add(l1);
	}
	panel.setBackground(Color.cyan);
	panel1=new JPanel();
	panel1.setBackground(Color.cyan);
	panel1.add(back);
	f=new JFrame();
	f.add(panel,BorderLayout.NORTH);
	f.add(panel1,BorderLayout.CENTER);
	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back)
		{
			f.dispose();
			new menuui();
		}
	}
}

