package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import searchApp.Student;
import searchApp.DBConnect;
public class UpdateStudentDialog extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JTextField firstNameTextField;
	private JTextField lastNametextField;
	private JTextField ageTextField;
	private JTextField coursesTextField;
	private JTextField teacherTextField;
	private JTextField durationTextField;
	
	private DBConnect connect;
	private AppLauncher appLauncher;	
    private Student previousStudent = null;
    private boolean updateMode = false;
	
	/*public static void main(String[] args) {
		try {
			UpdateStudentDialog dialog = new UpdateStudentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public UpdateStudentDialog(AppLauncher theAppLauncher, DBConnect theDBConnect,
			            Student thePreviousStudent,Boolean theUpdateMode) {
		this();
		connect = theDBConnect;
	    appLauncher = theAppLauncher;
	    previousStudent = thePreviousStudent;
	    updateMode = theUpdateMode;
	    
	    if(updateMode){
	    	setTitle("Update Student");
	    	populateGui(previousStudent);
	    }
	}

	private void populateGui(Student theStudent) {
        idTextField.setText(theStudent.getId());
		firstNameTextField.setText(theStudent.getFirstName());
		lastNametextField.setText(theStudent.getLastName());
		ageTextField.setText(theStudent.getAge());
		coursesTextField.setText(theStudent.getCourses());
		teacherTextField.setText(theStudent.getTeacher());
		durationTextField.setText(theStudent.getDuration());
	}

	public UpdateStudentDialog(AppLauncher theAppLauncher, DBConnect theDBConnect) {
		this(theAppLauncher, theDBConnect, null, false);
	}
	
	public UpdateStudentDialog() {
		setTitle("Add Student");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(230, 230, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(32dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblId = new JLabel("ID");
			contentPanel.add(lblId, "2, 2, center, default");
		}
		{
			idTextField = new JTextField();
			contentPanel.add(idTextField, "4, 2, fill, default");
			idTextField.setColumns(10);
		}
		{
			JLabel lblFirstname = new JLabel("Firstname");
			lblFirstname.setBackground(new Color(230, 230, 250));
			contentPanel.add(lblFirstname, "2, 4, right, default");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "4, 4, fill, default");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblLastname = new JLabel("Lastname");
			contentPanel.add(lblLastname, "2, 6, right, default");
		}
		{
			lastNametextField = new JTextField();
			contentPanel.add(lastNametextField, "4, 6, fill, default");
			lastNametextField.setColumns(10);
		}
		{
			JLabel lblAge = new JLabel("Age");
			contentPanel.add(lblAge, "2, 8, center, default");
		}
		{
			ageTextField = new JTextField();
			contentPanel.add(ageTextField, "4, 8, fill, default");
			ageTextField.setColumns(10);
		}
		{
			JLabel lblCourses = new JLabel("Courses");
			contentPanel.add(lblCourses, "2, 10, right, default");
		}
		{
			coursesTextField = new JTextField();
			contentPanel.add(coursesTextField, "4, 10, fill, default");
			coursesTextField.setColumns(10);
		}
		{
			JLabel lblTeacher = new JLabel("Teacher");
			contentPanel.add(lblTeacher, "2, 12, right, default");
		}
		{
			teacherTextField = new JTextField();
			contentPanel.add(teacherTextField, "4, 12, fill, default");
			teacherTextField.setColumns(10);
		}
		{
			JLabel lblDuration = new JLabel("Duration");
			contentPanel.add(lblDuration, "2, 14, right, default");
		}
		{
			durationTextField = new JTextField();
			contentPanel.add(durationTextField, "4, 14, fill, default");
			durationTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(245, 222, 179));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				ImageIcon saveImage = new ImageIcon("/home/mohamed/learnjava/jdbc2/src/GUI/save.png");
				JButton okButton = new JButton("Save", saveImage);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveStudent();
					}
				});
				okButton.setForeground(new Color(0, 0, 255));
				okButton.setBackground(new Color(211, 211, 211));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				ImageIcon cancelImage = new ImageIcon("/home/mohamed/learnjava/jdbc2/src/GUI/cancel.png");
				JButton cancelButton = new JButton("Cancel", cancelImage);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setForeground(new Color(165, 42, 42));
				cancelButton.setBackground(new Color(211, 211, 211));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	protected void saveStudent() {
		// STUCK BIG TIME !!! :/
		String id =  idTextField.getText();
		String firstName = firstNameTextField.getText();
		String lastName = lastNametextField.getText();
		String age = ageTextField.getText();
		String courses = coursesTextField.getText();
		String teacher = teacherTextField.getText();
		String duration = durationTextField.getText();
       
		Student tempStudent = null;
		
		if(updateMode){
			tempStudent = previousStudent;
			
			tempStudent.setId(id);
			tempStudent.setFirstName(firstName);
			tempStudent.setLastName(lastName);
			tempStudent.setAge(age);
			tempStudent.setCourses(courses);
			tempStudent.setTeacher(teacher);
			tempStudent.setDuration(duration);
			
		}else{
			tempStudent = new Student(id, firstName, lastName, age, courses, teacher, duration);
		}
			
			try {
				
				if (updateMode) {
					connect.updateStudent(tempStudent);
				} else {
					connect.addStudent(tempStudent);
				}

				setVisible(false);
				dispose();

				appLauncher.refreshStudentsView();

				// show success message
				JOptionPane.showMessageDialog(appLauncher,
						"Student saved succesfully.", "Student Saved",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(appLauncher,
						"Error saving Student: " + exc.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
