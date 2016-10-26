package GUI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import searchApp.Student;


	class StudentTableModel extends AbstractTableModel {
 
		public static final int OBJECT_COL = -1;
		private static final int ID_COL = 0;
		private static final int FIRST_NAME_COL = 1;
		private static final int LAST_NAME_COL = 2;
		private static final int AGE_COL = 3;
		private static final int COURSES_COL = 4;
		private static final int TEACHER_COL = 5;
		private static final int  DURATION_COL= 6;

		private String[] columnNames = { "ID", "Fisrt Name", "last Name", "Age",
				"Courses", "Teacher", "Duration"};
			
		private List<Student> students;

		public StudentTableModel(List<Student> theStudents) {
			students = theStudents;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return students.size();
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {

			Student tempStudents = students.get(row);

			switch (col) {
			case ID_COL:
				return tempStudents.getId();
			case FIRST_NAME_COL:
				return tempStudents.getFirstName();
			case AGE_COL:
				return tempStudents.getAge();
			case COURSES_COL:
				return tempStudents.getCourses();
			case TEACHER_COL:
				return tempStudents.getTeacher();
			case DURATION_COL:
				return tempStudents.getDuration();
			case OBJECT_COL:
				return tempStudents;
				
			default:
				return tempStudents.getLastName();
			}
		}
		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

}
