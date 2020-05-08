package medical0;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class costui implements ActionListener {	
					JFrame frame;
					JPanel panel1;
					JPanel panel2;
					JPanel panel3;
					JPanel panel4,panel5;
					JTextField ncost;
					JTextField name;
					JTextField month;
					JTextField year;
					JLabel lname;
					JLabel lcost;
					JLabel expiry;
					JButton up;
					JButton back;
					JScrollPane pane;
					String[] s= {"MEDICINE","QUANTITY","EXP MONTH","EXP YEAR","COST","MRP"};
					 DefaultTableModel t=new DefaultTableModel(s, 0);
					 JTable table;
					costui() throws SQLException
					{
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
						table.setRowHeight(35);
						table.setPreferredSize(new Dimension(1000,1000));
						table.setFont(new Font("Serif",Font.PLAIN,25));
						table.setBackground(Color.cyan);
						pane=new JScrollPane(table);
						pane.setPreferredSize(new Dimension(1000,900));
						frame=new JFrame("Medical Stock Management");
						panel1=new JPanel();
						panel2=new JPanel();
						panel3=new JPanel();
						panel4=new JPanel();
						panel5=new JPanel();
						lname=new JLabel("ENTER NAME");
						lname.setFont(new Font("Arial",Font.PLAIN,30));
						lcost=new JLabel("ENTER NEW COST");
						lcost.setFont(new Font("Arial",Font.PLAIN,30));
						expiry=new JLabel("ENTER EXPIRY DATE");
						expiry.setFont(new Font("Arial",Font.PLAIN,30));
						name=new JTextField(20);
						name.setPreferredSize(new Dimension(20, 30));
						name.setFont(new Font("arial", Font.BOLD, 17));
						ncost=new JTextField(5);
						ncost.setPreferredSize(new Dimension(20, 30));
						ncost.setFont(new Font("arial", Font.BOLD, 17));
						month=new JTextField(2);
						month.setDocument(new limit(2));
						month.setPreferredSize(new Dimension(20, 30));
						month.setFont(new Font("arial", Font.BOLD, 17));
						year=new JTextField(4);
						year.setDocument(new limit(4));
						year.setPreferredSize(new Dimension(20, 30));
						year.setFont(new Font("arial", Font.BOLD, 17));
						up=new JButton("UPDATE COST");
						up.addActionListener(this);
						up.setBackground(Color.orange);
						up.setForeground(Color.white);
						up.setFont(new Font("Arial",Font.BOLD,17));
						up.setPreferredSize(new Dimension(200, 50));
						back=new JButton("MENU");
						back.addActionListener(this);
						back.setBackground(Color.red);
						back.setForeground(Color.white);
						back.setFont(new Font("Arial",Font.BOLD,17));
						back.setPreferredSize(new Dimension(200, 50));
						panel1.add(lname);
						panel1.add(name);
						panel1.setBackground(Color.CYAN);
						panel2.add(panel1);
						panel2.add(expiry);
						panel2.add(month);
						panel2.add(year);
						panel2.setBackground(Color.cyan);
						panel3.add(panel2);
						panel3.add(lcost);
						panel3.add(ncost);
						panel3.setBackground(Color.CYAN);
						panel4.add(up);
						panel4.add(back);
						panel4.setBackground(Color.CYAN);
						panel5.add(pane);
						panel5.setBackground(Color.CYAN);
						frame.add(panel3,BorderLayout.NORTH);
						frame.add(panel5,BorderLayout.CENTER);
						frame.add(panel4,BorderLayout.SOUTH);
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
						frame.setVisible(true);
					}
					public static void main(String[] args) {
						try {
							new costui();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name=this.name.getText();
						int month,year,flag;
						int cost;
						flag=0;
						if(e.getSource()==up)
						{
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
							cost=Integer.parseInt(this.ncost.getText());
							if(flag==0)
							{
							frame.dispose();
							try {
								new cost(name,month,year,cost);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}
						}
						if(e.getSource()==back)
						{
							frame.dispose();
							new menuui();
						}
			}
}
