import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private ArrayList<Student> students;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.students = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}

class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

public class App extends JFrame {
    private JPanel mainPanel;
    private ArrayList<Course> courses = new ArrayList<>();

    public App() {
        setTitle("Student Course Registration System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        add(mainPanel);

        JButton addCourseButton = createButton("Add Course");
        addCourseButton.addActionListener(e -> showAddCoursePage());
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(addCourseButton, c);

        JButton addStudentButton = createButton("Add Student");
        addStudentButton.addActionListener(e -> showAddStudentPage());
        c.gridy = 1;
        mainPanel.add(addStudentButton, c);

        JButton courseListingButton = createButton("Course Listing Details");
        courseListingButton.addActionListener(e -> showCourseListingPage());
        c.gridy = 2;
        mainPanel.add(courseListingButton, c);

        JButton studentListButton = createButton("Student List of Course Details");
        studentListButton.addActionListener(e -> showStudentListPage());
        c.gridy = 3;
        mainPanel.add(studentListButton, c);

        JButton exitButton = createButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        c.gridy = 4;
        mainPanel.add(exitButton, c);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private JButton createExitButton(JFrame frame) {
        JButton button = new JButton("Exit");
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(e -> frame.dispose());
        return button;
    }

    private void showAddCoursePage() {
        JFrame addCourseFrame = new JFrame("Add Course");
        addCourseFrame.setSize(400, 300);
        JPanel addCoursePanel = new JPanel();
        addCoursePanel.setBackground(Color.PINK);
        addCoursePanel.setLayout(new GridLayout(6, 2, 10, 10));
        addCourseFrame.add(addCoursePanel);

        JTextField courseCodeField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField scheduleField = new JTextField();

        addCoursePanel.add(new JLabel("Course Code:"));
        addCoursePanel.add(courseCodeField);
        addCoursePanel.add(new JLabel("Title:"));
        addCoursePanel.add(titleField);
        addCoursePanel.add(new JLabel("Description:"));
        addCoursePanel.add(descriptionField);
        addCoursePanel.add(new JLabel("Capacity:"));
        addCoursePanel.add(capacityField);
        addCoursePanel.add(new JLabel("Schedule:"));
        addCoursePanel.add(scheduleField);

        JButton submitButton = createButton("Submit");
        submitButton.addActionListener(e -> {
            String courseCode = courseCodeField.getText();
            String title = titleField.getText();
            String description = descriptionField.getText();
            int capacity = Integer.parseInt(capacityField.getText());
            String schedule = scheduleField.getText();
            courses.add(new Course(courseCode, title, description, capacity, schedule));
            JOptionPane.showMessageDialog(addCoursePanel, "Course Added Successfully!");
            addCourseFrame.dispose();
        });

        addCoursePanel.add(submitButton);
        addCoursePanel.add(createExitButton(addCourseFrame));

        addCourseFrame.setVisible(true);
    }

    private void showAddStudentPage() {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setSize(400, 300);
        JPanel addStudentPanel = new JPanel();
        addStudentPanel.setBackground(Color.PINK);
        addStudentPanel.setLayout(new GridLayout(4, 2, 10, 10));
        addStudentFrame.add(addStudentPanel);

        JComboBox<String> courseComboBox = new JComboBox<>();
        for (Course course : courses) {
            courseComboBox.addItem(course.getCourseCode() + " - " + course.getTitle());
        }

        JTextField studentIdField = new JTextField();
        JTextField nameField = new JTextField();

        addStudentPanel.add(new JLabel("Select Course:"));
        addStudentPanel.add(courseComboBox);
        addStudentPanel.add(new JLabel("Student ID:"));
        addStudentPanel.add(studentIdField);
        addStudentPanel.add(new JLabel("Name:"));
        addStudentPanel.add(nameField);

        JButton submitButton = createButton("Submit");
        submitButton.addActionListener(e -> {
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            String studentId = studentIdField.getText();
            String name = nameField.getText();
            for (Course course : courses) {
                if ((course.getCourseCode() + " - " + course.getTitle()).equals(selectedCourse)) {
                    course.addStudent(new Student(studentId, name));
                    break;
                }
            }
            JOptionPane.showMessageDialog(addStudentPanel, "Student Added Successfully!");
            addStudentFrame.dispose();
        });

        addStudentPanel.add(submitButton);
        addStudentPanel.add(createExitButton(addStudentFrame));

        addStudentFrame.setVisible(true);
    }

    private void showCourseListingPage() {
        JFrame courseListingFrame = new JFrame("Course Listing Details");
        courseListingFrame.setSize(400, 300);
        JPanel courseListingPanel = new JPanel();
        courseListingPanel.setBackground(Color.ORANGE);
        courseListingPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        courseListingFrame.add(courseListingPanel);

        if (courses.isEmpty()) {
            courseListingPanel.add(new JLabel("No courses available."), c);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(150, 150));
            buttonPanel.setLayout(new GridBagLayout());

            JButton addCourseButton = createButton("Add Course");
            addCourseButton.addActionListener(e -> showAddCoursePage());
            c.gridy = 0;
            buttonPanel.add(addCourseButton, c);

            JButton exitButton = createButton("Exit");
            exitButton.setPreferredSize(new Dimension(150, 40));  // Ensuring uniform button size
            exitButton.addActionListener(e -> courseListingFrame.dispose());
            c.gridy = 1;
            buttonPanel.add(exitButton, c);

            courseListingPanel.add(buttonPanel);
        } else {
            for (Course course : courses) {
                JLabel courseLabel = new JLabel("<html><b>Code:</b> " + course.getCourseCode() + "<br><b>Title:</b> " + course.getTitle() + "<br><b>Description:</b> " + course.getDescription() + "<br><b>Capacity:</b> " + course.getCapacity() + "<br><b>Schedule:</b> " + course.getSchedule() + "</html>");
                courseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                courseListingPanel.add(courseLabel);
            }
            JButton exitButton = createButton("Exit");
            exitButton.addActionListener(e -> courseListingFrame.dispose());
            courseListingPanel.add(exitButton);
        }

        courseListingFrame.setVisible(true);
    }

    private void showStudentListPage() {
        JFrame studentListFrame = new JFrame("Student List of Course Details");
        studentListFrame.setSize(400, 300);
        JPanel studentListPanel = new JPanel();
        studentListPanel.setBackground(Color.BLUE);
        studentListPanel.setLayout(new GridLayout(courses.size() + 1, 1, 10, 10));
        studentListFrame.add(studentListPanel);

        boolean studentListEmpty = true;
        for (Course course : courses) {
            if (!course.getStudents().isEmpty()) {
                studentListEmpty = false;
                break;
            }
        }

        if (studentListEmpty) {
            studentListPanel.add(new JLabel("No students registered for any course."));
            JButton exitButton = createButton("Exit");
            exitButton.addActionListener(e -> studentListFrame.dispose());
            studentListPanel.add(exitButton);
        } else {
            for (Course course : courses) {
                if (!course.getStudents().isEmpty()) {
                    JLabel courseLabel = new JLabel("<html><b>Course:</b> " + course.getCourseCode() + " - " + course.getTitle() + "</html>");
                    courseLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    studentListPanel.add(courseLabel);
                    for (Student student : course.getStudents()) {
                        JLabel studentLabel = new JLabel("ID: " + student.getStudentId() + " - Name: " + student.getName());
                        studentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        studentListPanel.add(studentLabel);
                    }
                }
            }
            JButton exitButton = createButton("Exit");
            exitButton.addActionListener(e -> studentListFrame.dispose());
            studentListPanel.add(exitButton);
        }

        studentListFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
