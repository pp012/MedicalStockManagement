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
public class cost implements ActionListener{
					static String[] s= {"MEDICINE","QUANTITY","EXP MONTH","EXP YEAR","COST","MRP"};
					static DefaultTableModel t=new DefaultTableModel(s, 0);
					JButton back;
					JLabel label;
					JPanel panel,panel1;
					JFrame f;
					 cost(String n,int m,int y,int c) throws ClassNotFoundException, SQLException {
						String name;
						int month;
						int year;
						int cost;
						int flag;
						flag=0;
						label=null;
						name=n;
						month=m;
						year=y;
						cost=c;
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
							if(rs.getString(1).equals(name) && rs.getInt(3)==month && rs.getInt(4)==year)
							{
								stm=con.prepareStatement("update tp1 set cost=? where name=? and month=? and year=?");
								stm.setInt(1, cost);
								stm.setString(2,name);
								stm.setInt(3, month);
								stm.setInt(4, year);
								stm.executeUpdate();
								flag=1;
								break;
							}
							
						}	
						if(flag==0)
						{
						label=new JLabel("Stock Not Found!");
						label.setFont(new Font("Arial", Font.BOLD, 25));
						}
						else if(flag==1)
						{
							label=new JLabel("Cost Updated!");
							label.setFont(new Font("Arial", Font.BOLD, 25));
						}
						back=new JButton("Menu");
						back.setBackground(Color.red);
						back.setForeground(Color.white);
						back.addActionListener((this));
						back.setPreferredSize(new Dimension(200, 50));
						back.setFont(new Font("Arial",Font.BOLD,17));
						panel=new JPanel();
						panel1=new JPanel();
						panel.add(label);
						panel.setBackground(Color.cyan);
						panel1.setBackground(Color.cyan);
						panel1.add(back);
						f=new JFrame();
						f.add(panel,BorderLayout.NORTH);
						f.add(panel1,BorderLayout.CENTER);
						f.setSize(400, 200);
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
