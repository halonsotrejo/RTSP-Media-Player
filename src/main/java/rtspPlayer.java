
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;

public class rtspPlayer {

    public static void main(String[] args) {
        VideoStreaming streaming = new VideoStreaming();
        streaming.stream();
    }


    static class VideoStreaming {

        private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
        private final JFrame frame;

        VideoStreaming() {
            frame = new JFrame("Hydra Media Player");
            frame.setBounds(100, 100, 600, 400);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        }

        void stream() {
            frame.setContentPane(mediaPlayerComponent);
            frame.setVisible(true);
            mediaPlayerComponent.mediaPlayer().media().play("rtsp://192.168.1.44:554/live/10002.sdp");
        }
    }
}
