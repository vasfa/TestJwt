package ir.vasfa.testjwt;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JWPlayerView mPlayerView;
    PlayerView exoPlayerView;

    boolean isJW = true;

    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayerView = findViewById(R.id.jwplayer);
        exoPlayerView = findViewById(R.id.video_view);

        isJW = getIntent().getStringExtra("type").equals("jw")?true:false;
        link = getIntent().getStringExtra("link");

        if (isJW) {
            startJWPlayer();
        }
        else {
            startExoPlayer();
            mPlayerView.setVisibility(View.GONE);
        }
    }

    SimpleExoPlayer player;
    public void startExoPlayer() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        final ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        TrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DataSource.Factory dateSourceFactory = new
                DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getPackageName()), (TransferListener) bandwidthMeter);

        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(link)
                , dateSourceFactory, extractorsFactory, new Handler(), new ExtractorMediaSource.EventListener() {
            @Override
            public void onLoadError(IOException error) {

            }
        });

        player = ExoPlayerFactory.newSimpleInstance(this
                , new DefaultTrackSelector(trackSelectionFactory));
        player.prepare(mediaSource);

        exoPlayerView.setControllerShowTimeoutMs(0);
        exoPlayerView.setControllerHideOnTouch(false);
        exoPlayerView.setPlayer(player);
        player.setPlayWhenReady(true);
//        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
//        exoPlayerView.setPlayer(player);
//
//        // Produces DataSource instances through which media data is loaded.
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, "yourApplicationName"));
////        Uri uri = Uri.parse("http://api.bepors.me/voice/919c68c5-5820-4a03-933f-d531ae575256");
////        Uri uri = Uri.parse("http://wizlock.ir/files/2018_02_14_14_02_07___729990fa-6454-481f-84fa-ca2ac25a9e38___monaliza.MP3");
//        Uri uri = Uri.parse(link);
//// This is the MediaSource representing the media to be played.
//        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(uri);
//// Prepare the player with the source.
//        player.prepare(videoSource);
    }

    public void startJWPlayer(){

        PlaylistItem playlistItem = new PlaylistItem.Builder()
//                .file("http://api.bepors.me/voice/919c68c5-5820-4a03-933f-d531ae575256")
//                .file("http://wizlock.ir/files/2018_02_14_14_02_07___729990fa-6454-481f-84fa-ca2ac25a9e38___monaliza.MP3")
//                .file("https://hw4.cdn.asset.aparat.com/aparat-video/bd333f4470d4a97f16d00918db6df36316945220-144p__64646.apt?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjQxOTZlZDA4ODFiNzM5ODlkNzZlOGE3Y2M5ZDkyZmJiIiwiZXhwIjoxNTY4NTc4ODk4LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.K_Ur3nR-QCctwL45REbOVdMri4IY3Dhne-4EECkTonk")
                .file(link)
                .mediaId("123acb4e")
                .build();

        List<PlaylistItem> playlist = new ArrayList<>();
        playlist.add(playlistItem);
        PlayerConfig config = new PlayerConfig.Builder()
                .playlist(playlist)
//                .file("http://api.bepors.me/voice/919c68c5-5820-4a03-933f-d531ae575256")
                .build();
        mPlayerView.setup(config);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isJW)
            mPlayerView.onStart();
        else
            exoPlayerView.onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isJW)
            mPlayerView.onResume();
        else
            exoPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isJW)
           mPlayerView.onPause();
        else
            releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isJW)
            mPlayerView.onStop();
        else
            releasePlayer();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isJW)
             mPlayerView.onDestroy();
        else
            releasePlayer();

    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}