import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.net.URL;


public class a_window_tentang extends JPanel {

   /**
    * The main routine simply opens a window that shows a a_window_tentang panel.
    */
   public static void main(String[] args) {
      JFrame a_window_tentang_JFrame = new JFrame("JavaPHPJS");

      a_window_tentang content = new a_window_tentang();
      a_window_tentang_JFrame.setContentPane(content);
      a_window_tentang_JFrame.setSize(1024, 768);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      a_window_tentang_JFrame.setLocation( (screenSize.width  - a_window_tentang_JFrame.getWidth())  / 2,
                          (screenSize.height - a_window_tentang_JFrame.getHeight()) / 2 );
      a_window_tentang_JFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      a_window_tentang_JFrame.setVisible(true);

      // JOptionPane.showMessageDialog(null, "file:///"+ System.getProperty("user.dir") +"/Templates/assets/index.html" );
   }


   /**
    * The pane in which documents are displayed.
    */
   private JEditorPane WebBrowser;

   
   /**
    * An input box where the user enters the URL of a document
    * to be loaded into the edit pane.  A value URL string has
    * to contain the substring "://".  If the string in the box
    * does not contain this substring, then "http://" is 
    * prepended to the string.
    */
   private JTextField locationInput;
   
   
   /**
    * Defines a listner that responds when the user clicks on
    * a link in the document.
    */
   private class LinkListener implements HyperlinkListener {
      public void hyperlinkUpdate(HyperlinkEvent evt) {
         if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            loadURL(evt.getURL());
         }
      }
   }
   
   
   /**
    * Defines a listener that loads a new page when the user
    * clicks the "Go" button or presses return in the location
    * input box.
    */
   private class GoListener implements ActionListener {
      public void actionPerformed(ActionEvent evt) {
         URL url;
         try {
            String location = locationInput.getText().trim();
            if (location.length() == 0)
               throw new Exception();
            // if (! location.contains("://")) location = "http://" + location;
            url = new URL(location);
         }
         catch (Exception e) {
            JOptionPane.showMessageDialog(a_window_tentang.this,
                  "The Location input box does not\nccontain a legal URL.");
            return;
         }
         loadURL(url);
         locationInput.selectAll();
         locationInput.requestFocus();
      }
   }

   
   /**
    * Construct a panel that contains a JEditorPane in a JScrollPane,
    * with a tool bar that has a Location input box and a Go button.
    */
   public a_window_tentang() {

      setBackground(Color.BLACK);
      setLayout(new BorderLayout(1,1));
      setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

      WebBrowser = new JEditorPane();
      WebBrowser.setEditable(false);
      try   {
          // WebBrowser.setPage("file:///E:/Program%20Files/eclipse/Proyek/Java/JavaPHPJS/Templates/assets/index.html");
             WebBrowser.setPage(new URL("file:///E:/Program%20Files/eclipse/Proyek/Java/JavaPHPJS/Templates/assets/application/a_window_tentang.html"));
            }
      catch (Exception e) {
             WebBrowser.setContentType("text/html");
             WebBrowser.setText("<html>File atau URL Gagal Diload</html>");
      }
      WebBrowser.addHyperlinkListener(new LinkListener());

      add(new JScrollPane(WebBrowser),BorderLayout.CENTER);

      JToolBar main_toolbar = new JToolBar();
               main_toolbar.setFloatable(false);
      add(main_toolbar,BorderLayout.NORTH);

      ActionListener goListener = new GoListener();
      locationInput = new JTextField("file:///E:/Program%20Files/eclipse/Proyek/Java/JavaPHPJS/Templates/assets/application/a_window_tentang.html", 40);
      locationInput.addActionListener(goListener);

      JButton goButton = new JButton(" Go ");
              goButton.addActionListener( goListener );

      main_toolbar.add( new JLabel(" Location: "));
      main_toolbar.add(locationInput);
      main_toolbar.addSeparator(new Dimension(5,0));
      main_toolbar.add(goButton);

   } // end public a_window_tentang() {

   
   /**
    * Loads the document at the specified URL into the edit pane.
    */
   private void loadURL(URL url) {
      try {
         WebBrowser.setPage(url);
      }
      catch (Exception e) {
         WebBrowser.setContentType("text/plain");
         WebBrowser.setText( "Sorry, the requested document was not found\n" + "or cannot be displayed.\n\nError:" + e);
      }
   } // end private void loadURL(URL url) {
   
}
