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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class connect implements ActionListener{
	static String[] s= {"MEDICINE","QUANTITY","EXP MONTH","EXP YEAR","COST","MRP"};
	static DefaultTableModel t=new DefaultTableModel(s, 0);
	JButton back;
	JLabel label;
	JPanel panel,panel1;
	JFrame f;
	 connect(String name,int quantity,int month,int year,int cost,int sp) throws ClassNotFoundException, SQLException {
		String name1;
		int flag;
		flag=0;
		int temp,quantity1,month1,year1,cost1,sp1;
		name1=name;
		quantity1=quantity;
		month1=month;
		year1=year;
		cost1=cost;
		sp1=sp;
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
			if(rs.getString(1).equalsIgnoreCase(name1) && rs.getInt(3)==month1 && rs.getInt(4)==year1)
			{
				temp=rs.getInt(2);
				quantity1=quantity1+temp;
				stm=con.prepareStatement("update tp1 set quantity=? where name=? and month=? and year=?");
				stm.setInt(1, quantity1);
				stm.setString(2, name1);
				stm.setInt(3, month1);
				stm.setInt(4, year1);
				stm.executeUpdate();
				flag=1;
				break;
			}
			
		}	
		if(flag==0)
		{
			stm=con.prepareCall("insert into tp1 values(?,?,?,?,?,?)");
			stm.setString(1, name1);
			stm.setInt(2,quantity1);
			stm.setInt(3, month1);
			stm.setInt(4, year1);
			stm.setInt(5, cost1);
			stm.setInt(6, sp1);
			stm.execute();
		}
		rs=stmt.executeQuery("select *from tp1");
		while(rs.next())
		{
			
			String a[]=new String[6];
			a[0]=rs.getString(1);
			a[1]=Integer.toString(rs.getInt(2));
			a[2]=Integer.toString(rs.getInt(3));
			a[3]=Integer.toString(rs.getInt(4));
			a[4]=Integer.toString(rs.getInt(5));
			a[5]=Integer.toString(rs.getInt(6));
			t.addRow(a);
		}
		label=new JLabel("STOCK ADDED SUCCESFULLY!!");
		label.setFont(new Font("Arial", Font.BOLD, 25));
		back=new JButton("Menu");
		back.setBackground(Color.red);
		back.setForeground(Color.white);
		back.setPreferredSize(new Dimension(200, 50));
		back.setFont(new Font("Arial",Font.BOLD,17));
		back.addActionListener((this));
		panel=new JPanel();
		panel1=new JPanel();
		panel.add(label);
		panel1.add(back);
		panel.setBackground(Color.cyan);
		panel1.setBackground(Color.cyan);
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