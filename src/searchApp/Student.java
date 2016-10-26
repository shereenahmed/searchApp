package searchApp;

public class Student {

	private String id, firstName, lastName, age, courses, teacher, duration;
	
	public Student(String id, String firstName, String lastName, String age, 
			String courses, String teacher, String duration ){
		super();
		this.id = id;
		this. firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.courses = courses;
		this.teacher = teacher;
		this.duration = duration;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return String.format("Student [id=%s, firstName=%s, lastName=%s, age=%s,"
				+ " courses=%s, teacher=%s, duration=%s]",
						id, firstName, lastName, age, courses, teacher, duration);
	}
}
