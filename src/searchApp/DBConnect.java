package searchApp;
import java.sql.*;
import java.util.*;
import searchApp.Student;

public class DBConnect {

	private Connection con;
	private Statement st;
	private ResultSet res;
	
	public DBConnect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root", "password");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public  List<Student> getAllStudents() throws Exception {
		List<Student> list = new ArrayList<Student>();
		
	    st = null;
		res = null;
		
		try {
			st = con.createStatement();
			res = st.executeQuery("select * from students");
			
			while (res.next()) {
				Student tempStudent = convertRowToStudents(res);
				list.add(tempStudent);
			}

			return list;		
		}
		finally {
			close(st, res);
		}
	}
	
	public  List<Student> searchStudents(String lastName) throws Exception {
		List<Student> list = new ArrayList<Student>();

		PreparedStatement mySt = null;
		ResultSet res = null;

		try {
			lastName += "%";
			mySt = con.prepareStatement("select * from students where lastname like ?");
			
			mySt.setString(1, lastName);
			
			res = mySt.executeQuery();
			
			while (res.next()) {
				Student tempStudent = convertRowToStudents(res);
				list.add(tempStudent);
			}
			
			return list;
		}
		finally {
			close(mySt, res);
		}
	}
	
	public void deleteStudent(String studentId) throws SQLException {
		PreparedStatement mySt = null;

		try {
		
			mySt = con.prepareStatement("delete from students where id=?");

			mySt.setString(1, studentId);
			
			mySt.executeUpdate();			
		}
		finally {
			close(mySt,res);
		}
	}
	
	public void updateStudent(Student theStudents) throws SQLException {
		PreparedStatement mySt = null;

		try {
			
			mySt = con.prepareStatement("update students"
					+ " set ID=?,firstname=?,lastname=?, age=?, courses=?, teacher=?, duration=?"
					+ " where id=?");
			
			mySt.setString(1, theStudents.getId());
			mySt.setString(2, theStudents.getFirstName());
			mySt.setString(3, theStudents.getLastName());
			mySt.setString(4, theStudents.getAge());
			mySt.setString(5, theStudents.getCourses());
			mySt.setString(6, theStudents.getTeacher());
			mySt.setString(7, theStudents.getDuration());
			
			mySt.executeUpdate();			
		}
		finally {
			close(mySt,res);
		}
		
	}
	
	public void addStudent(Student theStudent) throws Exception {
		PreparedStatement mySt = null;

		try {

			mySt = con.prepareStatement("insert into students"
					+ " (ID, firstname, lastname, age, courses, teacher, duration)"
					+ " values (?, ?, ?, ?, ?, ?, ?)");
			
			mySt.setString(1, theStudent.getId());
			mySt.setString(2, theStudent.getFirstName());
			mySt.setString(3, theStudent.getLastName());
			mySt.setString(4, theStudent.getAge());
			mySt.setString(5, theStudent.getCourses());
			mySt.setString(6, theStudent.getTeacher());
			mySt.setString(7, theStudent.getDuration());
			
			mySt.executeUpdate();			
		}
		finally {
			close(mySt,res);
		}
		
	}
	
	private Student convertRowToStudents(ResultSet res) throws SQLException {
		
		String id = res.getString("ID");
		String firstName = res.getString("firstname");
		String lastName = res.getString("lastname");
		String age = res.getString("age");
		String courses = res.getString("courses");
		String teacher = res.getString("teacher");
		String duration = res.getString("duration");
		
		
		Student tempStudent = new Student(id, firstName, lastName, age, courses, teacher, duration );
		
		return tempStudent;
	}

	
	private static void close(Connection con, Statement st, ResultSet res)
			throws SQLException {

		if (res != null) {
			res.close();
		}

		if (st != null) {
			
		}
		
		if (con != null) {
			con.close();
		}
	}

	private void close(Statement st, ResultSet res) throws SQLException {
		close(null, st, res);		
	}

	public static void main(String[] args) throws Exception {
		
		DBConnect studentConn = new DBConnect();
		System.out.println(studentConn.searchStudents("ahm"));

		System.out.println(studentConn.getAllStudents());
	}
	}



