

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class rtspPlayer {

    static class VideoStreaming {

        private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
        private final JFrame frame;
        private final JMenu jmenu;
        private final JMenuItem menuItem;
        private final JMenuBar menuBar;
        private final JButton pauseButton,playButton,recordButton;
        private String streamURL;
        private boolean OnOff = false;

        private final File dir = new File(System.getProperty("user.home"), "Videos");
        //private  dir.mkdir();
        private final DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        private final String fileName = dir.getAbsolutePath() + "/Capture-" + df.format(new Date()) + ".mpg";
        private final String[] options = {
                ":sout=#transcode{vcodec=h264,vb=4096,scale=1,acodec=mpga,ab=128,channels=2,samplerate=44100}:duplicate{dst=file{dst=" + fileName + "},dst=display}"
        };

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
            controlPane.setBackground(Color.BLACK);
            pauseButton = new JButton("Pause");
            controlPane.add(pauseButton);
            playButton = new JButton("Play");
            controlPane.add(playButton);
            recordButton = new JButton("Grabar");
            controlPane.add(recordButton);
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
                    streamURL = JOptionPane.showInputDialog(frame, "Ingresa la dirección URL");
                    mediaPlayerComponent.mediaPlayer().media().play(streamURL);
                }
            });

            /* Events */
            pauseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mediaPlayerComponent.mediaPlayer().controls().pause();
                }
            });
            playButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mediaPlayerComponent.mediaPlayer().controls().play();
                }
            });
            recordButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (OnOff != true){
                        mediaPlayerComponent.mediaPlayer().media().start(streamURL,options);
                        mediaPlayerComponent.setBorder(new LineBorder(Color.RED));
                        OnOff = true;
                    }
                    else{
                        mediaPlayerComponent.mediaPlayer().media().play(streamURL);
                        mediaPlayerComponent.setBorder(new LineBorder(Color.BLACK));
                        OnOff = false;
                    }
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
