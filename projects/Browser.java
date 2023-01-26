import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLEditorKit;

public class Browser extends JFrame implements ActionListener {

    private JTextField addressBar;
    private JEditorPane display;
    private JButton backButton;
    private JButton forwardButton;
    private ArrayList<String> history = new ArrayList<>();
    private ArrayList<String> forwardHistory = new ArrayList<>();

    // constructor
    public Browser() {
        super("My Browser");

        // set up address bar
        addressBar = new JTextField("Enter a URL or search term");
        addressBar.addActionListener(this);

        // set up display panel
        display = new JEditorPane();
        display.setEditable(false);
        display.addHyperlinkListener(
                e -> {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        loadPage(e.getURL().toString(), false);
                    }
                });
        // allow mixed http and https content (hopefully)
        display.setContentType("text/html");
        ((HTMLEditorKit) display.getEditorKit()).setAutoFormSubmission(false);

        // set up buttons
        backButton = new JButton("<");
        backButton.addActionListener(this);
        forwardButton = new JButton(">");
        forwardButton.addActionListener(this);
        addressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // add components to frame
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(backButton);
        topPanel.add(forwardButton);
        topPanel.add(Box.createHorizontalGlue()); // add glue to push address bar to right
        topPanel.add(addressBar);

        // add components to frame
        setLayout(new BorderLayout());
        add(new JScrollPane(display), "North");
        add(topPanel, "North");


        setSize(500, 300);
        setVisible(true);
    }

    // load a page from a given URL
    private void loadPage(String url, boolean fromHistory) {
        try {
            display.setPage(new URL(url));
        } catch (IOException e) {
            System.out.println("Invalid URL");
        }
        addressBar.setText(url);
        history.add(url);
    }

    // navigate back one page
    private void navigateBack() {
        if (history.size() > 1) {
            forwardHistory.add(history.remove(history.size() - 1));
            loadPage(history.get(history.size() - 1), true);
        }
    }

    // navigate forward one page
    private void navigateForward() {
        if (forwardHistory.size() > 0) {
            history.add(forwardHistory.remove(forwardHistory.size() - 1));
            loadPage(history.get(history.size() - 1), true);
        }
    }

    // handle events from the address bar and buttons
    public void actionPerformed(ActionEvent e) {
        String url = e.getActionCommand();
        if (url.startsWith("http://") || url.startsWith("https://")) {
            loadPage(url, false);
        } else {
            loadPage("https://google.com/?q=" + url, false); //https://start.duckduckgo.com/?q=
        }
    }

    public static void main(String[] args) {
        new Browser();
    }
}