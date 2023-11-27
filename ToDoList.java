import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
class Task extends JPanel {

    JLabel index;
    JTextField taskName;
    JButton done;

    Color cf= new Color(255,153,204);
    Color f = new Color(204, 153, 255);
    Color doneColor = new Color(204,255,255);

    private boolean checked;

    Task() {
        this.setPreferredSize(new Dimension(400, 20));
        this.setBackground(cf);
      this.setLayout(new BorderLayout()); 
        checked = false;

        index = new JLabel(""); 
        index.setPreferredSize(new Dimension(20, 20)); 
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(index, BorderLayout.WEST); 

        taskName = new JTextField("Write something.."); 
        taskName.setBorder(BorderFactory.createEmptyBorder()); 
        taskName.setBackground(cf);

        this.add(taskName, BorderLayout.CENTER);

        done = new JButton("Done");
        done.setPreferredSize(new Dimension(80, 20));
        done.setBorder(BorderFactory.createEmptyBorder());
        done.setBackground(doneColor);
        done.setFocusPainted(false);

        this.add(done, BorderLayout.EAST);

    }

    public void changeIndex(int num) {
        this.index.setText(num + ""); 
        this.revalidate(); 
    }
   public JButton getDone() {
        return done;
    }

    public boolean getState() {
        return checked;
    }

    public void changeState() {
        this.setBackground(f);
        taskName.setBackground(f);
        checked = true;
        revalidate();
    }
}

class List extends JPanel {

    Color lightColor = new Color(204,255,255);

    List() {

        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5); 

        this.setLayout(layout); 
        this.setPreferredSize(new Dimension(400, 560));
        this.setBackground(lightColor);
    }

    public void updateNumbers() {
        Component[] listItems = this.getComponents();

        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] instanceof Task) {
                ((Task) listItems[i]).changeIndex(i + 1);
            }
        }

    }
   public void removeCompletedTasks() {

        for (Component c : getComponents()) {
            if (c instanceof Task) {
                if (((Task) c).getState()) {
                    remove(c); 
                    updateNumbers(); 
                }
            }
        }

    }
}

class Footer extends JPanel {

    JButton addTask;
    JButton clear;

    Color c = new Color(153,51,102);
    Color lightColor = new Color(204,255,204);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    Footer() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(lightColor);

        addTask = new JButton("Add Task"); 
        addTask.setBorder(emptyBorder); 
        addTask.setFont(new Font("Times New Roman", Font.ITALIC, 20)); 
        addTask.setVerticalAlignment(JButton.BOTTOM); 
        addTask.setBackground(c); 
        this.add(addTask); 

        this.add(Box.createHorizontalStrut(20)); 
        clear = new JButton("Clear finished tasks");
        clear.setFont(new Font("Sans-serif", Font.ITALIC, 20)); 
        clear.setBorder(emptyBorder); 
        clear.setBackground(c); 
        this.add(clear); 
    }
   public JButton getNewTask() {
        return addTask;
    }

    public JButton getClear() {
        return clear;
    }
}

class TitleBar extends JPanel {

    Color lightColor = new Color(204,255,204);

    TitleBar() {
        this.setPreferredSize(new Dimension(400, 80)); 
        this.setBackground(lightColor); 
        JLabel titleText = new JLabel("To Do List"); 
        titleText.setPreferredSize(new Dimension(200, 60)); 
        titleText.setFont(new Font("WINTER CALLIGPRAHY", Font.BOLD, 20)); 
        titleText.setHorizontalAlignment(JLabel.CENTER); 
        this.add(titleText); 
    }
}

class AppFrame extends JFrame {

    private TitleBar title;
    private Footer footer;
    private List list;

    private JButton newTask;
    private  JButton clear;

    AppFrame() {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true);

        title = new TitleBar();
        footer = new Footer();
        list = new List();

        this.add(title, BorderLayout.NORTH); 
        this.add(footer, BorderLayout.SOUTH); 
        this.add(list, BorderLayout.CENTER); 

        newTask = footer.getNewTask();
        clear = footer.getClear();

        addListeners();
    }

    public void addListeners() {
	newTask.addMouseListener(new MouseAdapter() {
	@override
	 public void mouseEntered(MouseEvent e){
         	Color f=new Color(153,51,102);
	 	newTask.setBackground(f);
		Color c=new Color(255,255,204);
	 	newTask.setForeground(c);
        newTask.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.updateNumbers(); 
              
              

                task.getDone().addMouseListener(new MouseAdapter() {
                    @override
                    public void mousePressed(MouseEvent e) {

                        task.changeState(); 
                        list.updateNumbers(); 
                        revalidate(); 
                   }
                });
            }

          });
        }
     });
 	clear.addMouseListener(new MouseAdapter() {
            @override
            public void mouseEntered(MouseEvent e) {
		Color f=new Color(153,51,102);
	 	clear.setBackground(f);
		Color c=new Color(255,255,204);
	 	clear.setForeground(c);
                 

        clear.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                list.removeCompletedTasks(); 
                repaint(); 
}
});

           }
        });
    }

}

public class ToDoList {

    public static void main(String args[]) {
        AppFrame frame = new AppFrame(); 
    }
}

@interface override {

}