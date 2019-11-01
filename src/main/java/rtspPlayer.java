
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

public class rtspPlayer {

    static class VideoStreaming {

        private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
        private final JFrame frame;
        private final JMenu jmenu;
        private final JMenuItem menuItem;
        private final JMenuBar menuBar;
        private final JButton pauseButton;


        VideoStreaming() {
            /* Frame */
            frame = new JFrame("Hydra Media Player");
            frame.setBounds(100, 100, 600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mediaPlayerComponent.release();
                    System.exit(0);
                }
            });

            /* Panel */
            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BorderLayout());

            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
            contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

            JPanel controlPane = new JPanel();
            pauseButton = new JButton("Pause");
            controlPane.add(pauseButton);
            contentPane.add(controlPane, BorderLayout.SOUTH);

            /* Jmenu */
            menuBar = new JMenuBar();
            menuBar.setVisible(true);
            jmenu = new JMenu("Archivo");
            menuItem = new JMenuItem("Open URL");

            jmenu.add(menuItem);
            menuBar.add(jmenu);

            frame.setJMenuBar(menuBar);

            /* App Icon */
            ImageIcon img = new ImageIcon("C:\\Users\\Hector Trejo\\IdeaProjects\\rtspPlayer\\src\\main\\img\\icon2.png");
            frame.setIconImage(img.getImage());

            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String streamURL = JOptionPane.showInputDialog(frame, "Ingresa la dirección URL");
                    mediaPlayerComponent.mediaPlayer().media().play(streamURL);
                }
            });

            frame.setContentPane(contentPane);
            frame.setVisible(true);
        }

    }

    public static void main(String[] args) {
        VideoStreaming streaming = new VideoStreaming();
        //streaming.stream();
    }


}
