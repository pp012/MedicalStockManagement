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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class display implements ActionListener{
	JButton back;
	JPanel panel,panel1;
	JTable table;
	JScrollPane pane;
	 String[] s= {"MEDICINE","QUANTITY","EXP MONTH","EXP YEAR","COST","MRP"};
	 DefaultTableModel t=new DefaultTableModel(s, 0);
	 JFrame f;
	 display() throws ClassNotFoundException, SQLException {
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

		String a[]=new String[6];
		a[0]=rs.getString(1);
		a[1]=Integer.toString(rs.getInt(2));
		a[2]=Integer.toString(rs.getInt(3));
		a[3]=Integer.toString(rs.getInt(4));
		a[4]=Integer.toString(rs.getInt(5));
		a[5]=Integer.toString(rs.getInt(6));
		t.addRow(a);
	}
	table=new JTable(t);
	back=new JButton("MENU");
	back.addActionListener((ActionListener) this);
	back.setBackground(Color.red);
	back.setForeground(Color.white);
	back.setPreferredSize(new Dimension(200, 50));
	back.setFont(new Font("Arial",Font.BOLD,17));
	table.setRowHeight(35);
	table.setPreferredSize(new Dimension(1400,900));
	table.setFont(new Font("Serif",Font.PLAIN,25));
	table.setBackground(Color.cyan);
	pane=new JScrollPane(table);
	pane.setPreferredSize(new Dimension(1400,900));
	panel=new JPanel();
	panel1=new JPanel();
	panel.add(pane);
	panel.setBackground(Color.cyan);
	panel1.setBackground(Color.cyan);
	panel1.add(back);
	f=new JFrame();
	f.add(panel,BorderLayout.NORTH);
	f.add(panel1,BorderLayout.CENTER);
	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	f.setVisible(true);
}
	public static void main(String[] args) {
		try {
			new display();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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