import java.awt.*;
import java.awt.event.*;
//import java.beans.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

public final class MainPanel extends JPanel {
    private final JTextArea area     = new JTextArea();
    private final JPanel statusPanel = new JPanel(new BorderLayout());
    private final JButton runButton  = new JButton("run");
    private final JButton canButton  = new JButton("cancel");
    private final JProgressBar bar1  = new JProgressBar(0, 100);
    private final JProgressBar bar2  = new JProgressBar(0, 100);
    private transient SwingWorker<String, Progress> worker;

    private MainPanel() {
        super(new BorderLayout(5, 5));
        area.setEditable(false);
        runButton.addActionListener(e -> {
            initStatusPanel(true);
            executeWorker();
        });
        canButton.addActionListener(e -> {
            if (Objects.nonNull(worker) && !worker.isDone()) {
                worker.cancel(true);
            }
            worker = null;
        });
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(runButton);
        box.add(Box.createHorizontalStrut(2));
        box.add(canButton);
        add(new JScrollPane(area));
        add(box, BorderLayout.NORTH);
        add(statusPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(320, 240));
    }

    protected void executeWorker() {
        if (Objects.isNull(worker)) {
            worker = new ProgressTask();
        }
        worker.execute();
    }

    protected void initStatusPanel(boolean start) {
        if (start) {
            runButton.setEnabled(false);
            canButton.setEnabled(true);
            bar1.setValue(0);
            bar2.setValue(0);
            statusPanel.add(bar1, BorderLayout.NORTH);
            statusPanel.add(bar2, BorderLayout.SOUTH);
        } else {
            runButton.setEnabled(true);
            canButton.setEnabled(false);
            statusPanel.removeAll();
        }
        statusPanel.revalidate();
    }

    class ProgressTask extends BackgroundTask {
        @Override protected void process(List<Progress> chunks) {
            if (isCancelled()) {
                return;
            }
            if (!isDisplayable()) {
                cancel(true);
                return;
            }
            processChunks(chunks);
        }
        @Override public void done() {
            if (!isDisplayable()) {
                cancel(true);
                return;
            }
            initStatusPanel(false);
            try {
                appendLine(isCancelled() ? "\nCancelled\n" : get() + "\n");
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
                appendLine("\nException\n");
            }
        }
    }

    protected void processChunks(List<Progress> chunks) {
        chunks.forEach(s -> {
            switch (s.component) {
                case TOTAL:
                    bar1.setValue((Integer) s.value);
                    break;
                case FILE:
                    bar2.setValue((Integer) s.value);
                    break;
                case LOG:
                    area.append((String) s.value);
                    break;
                default:
                    throw new AssertionError("Unknown Progress");
            }
        });
    }

    protected void appendLine(String str) {
        area.append(str);
        area.setCaretPosition(area.getDocument().getLength());
    }

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        JFrame frame = new JFrame("@title@");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

enum Component { TOTAL, FILE, LOG }

class Progress {
    public final Object value;
    public final Component component;
    protected Progress(Component component, Object value) {
        this.component = component;
        this.value = value;
    }
}

class BackgroundTask extends SwingWorker<String, Progress> {
    private final Random r = new Random();
    @Override public String doInBackground() {
        //System.out.println("doInBackground() is EDT?: " + EventQueue.isDispatchThread());
        int current = 0;
        int lengthOfTask = 12; //filelist.size();
        publish(new Progress(Component.LOG, "Length Of Task: " + lengthOfTask));
        publish(new Progress(Component.LOG, "\n------------------------------\n"));
        while (current < lengthOfTask && !isCancelled()) {
            publish(new Progress(Component.LOG, "*"));
            publish(new Progress(Component.TOTAL, 100 * current / lengthOfTask));
            try {
                convertFileToSomething();
            } catch (InterruptedException ex) {
                return "Interrupted";
            }
            current++;
        }
        publish(new Progress(Component.LOG, "\n"));
        return "Done";
    }
    private void convertFileToSomething() throws InterruptedException {
        int current = 0;
        int lengthOfTask = 10 + r.nextInt(50); //long lengthOfTask = file.length();
        while (current <= lengthOfTask && !isCancelled()) {
            int iv = 100 * current / lengthOfTask;
            Thread.sleep(20); // dummy
            publish(new Progress(Component.FILE, iv + 1));
            current++;
        }
    }
}
