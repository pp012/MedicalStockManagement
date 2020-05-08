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

public class name  implements ActionListener{
		static String[] s= {"MEDICINE","QUANTITY","EXP MONTH","EXP YEAR","COST","MRP"};
		static DefaultTableModel t=new DefaultTableModel(s, 0);
		JButton back;
		JLabel label;
		JPanel panel,panel1;
		JFrame f;
		 name(String oname,String nname,int month,int year) throws ClassNotFoundException, SQLException {
			String nameo;
			String namen;
			int month1;
			int year1;
			int flag;
			flag=0;
			label=null;
			nameo=oname;
			namen=nname;
			month1=month;
			year1=year;
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
				if(rs.getString(1).equals(nameo) && rs.getInt(3)==month1 && rs.getInt(4)==year1)
				{
					stm=con.prepareStatement("update tp1 set name=? where name=? and month=? and year=?");
					stm.setString(1, namen);
					stm.setString(2,nameo);
					stm.setInt(3, month1);
					stm.setInt(4, year1);
					stm.executeUpdate();
					flag=1;
					break;
				}
				
			}	
			if(flag==0)
			{
			label=new JLabel("STOCK NOT FOUND!");
			label.setFont(new Font("Arial", Font.BOLD, 25));
			}
			else if(flag==1)
			{
				label=new JLabel("NAME UPDATED!");
				label.setFont(new Font("Arial", Font.BOLD, 25));
			}
			back=new JButton("MENU");
			back.setBackground(Color.red);
			back.setForeground(Color.white);
			back.addActionListener((this));
			back.setPreferredSize(new Dimension(200, 50));
			back.setFont(new Font("Arial",Font.BOLD,17));
			panel=new JPanel();
			panel.add(label);
			panel1=new JPanel();
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
