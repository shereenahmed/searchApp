package GUI;
import searchApp.DBConnect;
import searchApp.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;


public class AppLauncher extends JFrame {

	private JPanel contentPane;
    private JTextField lastNameTextField;
    private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_1;
    private DBConnect connect;
    private JButton btnAddStudent;
	private JButton btnUpdateStudent;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppLauncher frame = new AppLauncher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AppLauncher() {
		try {
			connect = new DBConnect();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		setTitle("Student Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Enter last name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		
		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		ImageIcon searchImage = new ImageIcon("/home/mohamed/learnjava/jdbc2/src/search.png");
		JButton btnSearch = new JButton("Search", searchImage);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String lastName = lastNameTextField.getText();

					List<Student> students = null;

					if (lastName != null && lastName.trim().length() > 0) {
						students = connect.searchStudents(lastName);
					} else {
						students =connect.getAllStudents();
					}
					
					// create the model and update the "table"
					StudentTableModel model = new StudentTableModel(students);
					
					table.setModel(model);
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(AppLauncher.this, "Error: " 
				+ exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		ImageIcon addImage= new ImageIcon ("/home/mohamed/learnjava/jdbc2/src/GUI/add.png");
		JButton btnAddStudent = new JButton("Add Student", addImage);
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				AddStudentDialog dialog = new AddStudentDialog(AppLauncher.this, connect);

				// show dialog
				dialog.setVisible(true);
			}
		});
		
		panel_1.add(btnAddStudent);
		
		ImageIcon updateImage= new ImageIcon ("/home/mohamed/learnjava/jdbc2/src/GUI/update.png");
		JButton btnUpdateStudent = new JButton("Update student", updateImage);
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				if (row < 0) {
					JOptionPane.showMessageDialog(AppLauncher.this, "You must select a student", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current employee
				Student tempStudent = (Student) table.getValueAt(row, StudentTableModel.OBJECT_COL);
				
				UpdateStudentDialog dialog = new UpdateStudentDialog(AppLauncher.this, connect, 
															tempStudent, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		panel_1.add(btnUpdateStudent);
		
		ImageIcon deleteImage = new ImageIcon("/home/mohamed/learnjava/jdbc2/src/GUI/delete.png");
		JButton btnDeleteStudent = new JButton("Delete Student", deleteImage);
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int row = table.getSelectedRow();

					if (row < 0) {
						JOptionPane.showMessageDialog(AppLauncher.this, 
								"You must select a student", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							AppLauncher.this, "Sure you want to delete this student?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current student
					Student tempStudent = (Student) table.getValueAt(row, StudentTableModel.OBJECT_COL);

					connect.deleteStudent(tempStudent.getId());

					// refresh GUI
					refreshStudentsView();
					
					JOptionPane.showMessageDialog(AppLauncher.this,
							"Student deleted succesfully.", "Student Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(AppLauncher.this,
							"Error deleting student: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		panel_1.add(btnDeleteStudent);
	}
	
	
	public void refreshStudentsView() {

		try {
			List<Student> students = connect.getAllStudents();

			// create the model and update the "table"
			StudentTableModel model = new StudentTableModel(students);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}