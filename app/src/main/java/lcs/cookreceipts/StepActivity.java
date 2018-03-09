package lcs.cookreceipts;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import lcs.cookreceipts.data.ReceiptDatabase;
import lcs.cookreceipts.data.Step;

import static lcs.cookreceipts.StepListActivity.RECIPE_ID;
import static lcs.cookreceipts.utils.Constants.ARG_STEP;
import static lcs.cookreceipts.utils.Constants.ARG_TITLE;

public class StepActivity extends AppCompatActivity {

    private SimpleExoPlayer mExoPlayer;
    @BindView(R.id.exo_play)  SimpleExoPlayerView mPlayerView;
    @BindView(R.id.tv_description)  TextView textView;
    @BindView(R.id.toolbar)  Toolbar toolbar;
    private int recipeId;
    private String title;
    private String step;
    private Step stepInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        recipeId = getIntent().getIntExtra(RECIPE_ID, 0);
        title = getIntent().getStringExtra(ARG_TITLE);
        step = getIntent().getStringExtra(ARG_STEP);
        ButterKnife.bind(this);
        //setSupportActionBar(toolbar);
     //   toolbar.setTitle(title);

        stepInstance = ReceiptDatabase.getInstance(this).stepDao().getStep(recipeId, step);
        initializePlayer(Uri.parse(stepInstance.getVideoURL()));
        textView.setText(stepInstance.getDescription());

    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(this, "CookReceipts");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                                        this, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
