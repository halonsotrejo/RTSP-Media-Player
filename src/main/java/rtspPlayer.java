
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class rtspPlayer {

    static class VideoStreaming {

        private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
        private final JFrame frame;
        private JMenu jmenu;
        private JMenuItem menuItem;
        private JMenuBar menuBar;


        VideoStreaming() {
            /* Frame */
            frame = new JFrame("Hydra Media Player");
            frame.setBounds(100, 100, 600, 400);

            /* Jmenu */
            menuBar = new JMenuBar();
            menuBar.setVisible(true);
            jmenu = new JMenu("Archivo");
            menuItem = new JMenuItem("Open URL");

            jmenu.add(menuItem);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

            menuBar.add(jmenu);
            frame.setJMenuBar(menuBar);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mediaPlayerComponent.release();
                    System.exit(0);
                }
            });
        }

        void stream() {
            frame.setContentPane(mediaPlayerComponent);
            frame.setVisible(true);
            mediaPlayerComponent.mediaPlayer().media().play("rtsp://192.168.1.44:554/live/10002.sdp");



        }
    }

    public static void main(String[] args) {
        VideoStreaming streaming = new VideoStreaming();
        streaming.stream();
    }


}
