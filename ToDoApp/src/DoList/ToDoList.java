package DoList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoList extends JFrame {
    private JTextField taskInputField;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JButton addTaskButton;
    private JButton deleteTaskButton;

    public ToDoList() {
        setTitle("Simple To-Do-App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskInputField = new JTextField(25);
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addTaskButton = new JButton("Add Task");
        deleteTaskButton = new JButton("Delete Task");

        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("New Task:"));
        inputPanel.add(taskInputField);
        inputPanel.add(addTaskButton);

        // Scroll pane for the task list
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("My Tasks"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteTaskButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        // Make the frame visible
        setVisible(true);
    }
    private void addTask() {
        String task = taskInputField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskInputField.setText("");
        } else {
            // Show a warning if the input field is empty
            JOptionPane.showMessageDialog(this,
                    "Task cannot be empty",
                    "Input Wrong",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            // Show a warning if no task is selected for deletion
            JOptionPane.showMessageDialog(this,
                    "Please select a task.",
                    "Selection Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoList();
            }
        });
    }
}
