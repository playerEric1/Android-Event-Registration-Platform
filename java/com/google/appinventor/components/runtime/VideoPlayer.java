package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import gnu.expr.Declaration;
import java.io.IOException;

@UsesPermissions(permissionNames = "android.permission.INTERNET")
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A multimedia component capable of playing videos. When the application is run, the VideoPlayer will be displayed as a rectangle on-screen.  If the user touches the rectangle, controls will appear to play/pause, skip ahead, and skip backward within the video.  The application can also control behavior by calling the <code>Start</code>, <code>Pause</code>, and <code>SeekTo</code> methods.  <p>Video files should be in 3GPP (.3gp) or MPEG-4 (.mp4) formats.  For more details about legal formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>App Inventor for Android only permits video files under 1 MB and limits the total size of an application to 5 MB, not all of which is available for media (video, audio, and sound) files.  If your media files are too large, you may get errors when packaging or installing your application, in which case you should reduce the number of media files or their sizes.  Most video editing software, such as Windows Movie Maker and Apple iMovie, can help you decrease the size of videos by shortening them or re-encoding the video into a more compact format.</p><p>You can also set the media source to a URL that points to a streaming video, but the URL must point to the video file itself, not to a program that plays the video.", version = 5)
@SimpleObject
/* loaded from: classes.dex */
public final class VideoPlayer extends AndroidViewComponent implements OnDestroyListener, Deleteable, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private boolean delayedStart;
    private boolean inFullScreen;
    private MediaPlayer mPlayer;
    private boolean mediaReady;
    private String sourcePath;
    private final ResizableVideoView videoView;

    public VideoPlayer(ComponentContainer container) {
        super(container);
        this.inFullScreen = false;
        this.mediaReady = false;
        this.delayedStart = false;
        container.$form().registerForOnDestroy(this);
        this.videoView = new ResizableVideoView(container.$context());
        this.videoView.setMediaController(new MediaController(container.$context()));
        this.videoView.setOnCompletionListener(this);
        this.videoView.setOnErrorListener(this);
        this.videoView.setOnPreparedListener(this);
        container.$add(this);
        container.setChildWidth(this, ComponentConstants.VIDEOPLAYER_PREFERRED_WIDTH);
        container.setChildHeight(this, ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT);
        container.$form().setVolumeControlStream(3);
        this.sourcePath = "";
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.videoView;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The \"path\" to the video.  Usually, this will be the name of the video file, which should be added in the Designer.")
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void Source(String path) {
        if (this.inFullScreen) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, this, path);
            return;
        }
        if (path == null) {
            path = "";
        }
        this.sourcePath = path;
        this.videoView.invalidateMediaPlayer(true);
        if (this.videoView.isPlaying()) {
            this.videoView.stopPlayback();
        }
        this.videoView.setVideoURI(null);
        this.videoView.clearAnimation();
        if (this.sourcePath.length() > 0) {
            Log.i("VideoPlayer", "Source path is " + this.sourcePath);
            try {
                this.mediaReady = false;
                MediaUtil.loadVideoView(this.videoView, this.container.$form(), this.sourcePath);
                Log.i("VideoPlayer", "loading video succeeded");
            } catch (IOException e) {
                this.container.$form().dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
            }
        }
    }

    @SimpleFunction(description = "Starts playback of the video.")
    public void Start() {
        Log.i("VideoPlayer", "Calling Start");
        if (this.inFullScreen) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, this, null);
        } else if (this.mediaReady) {
            this.videoView.start();
        } else {
            this.delayedStart = true;
        }
    }

    @SimpleProperty(description = "Sets the volume to a number between 0 and 100. Values less than 0 will be treated as 0, and values greater than 100 will be treated as 100.")
    @DesignerProperty(defaultValue = "50", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void Volume(int vol) {
        int vol2 = Math.min(Math.max(vol, 0), 100);
        if (this.mPlayer != null) {
            this.mPlayer.setVolume(vol2 / 100.0f, vol2 / 100.0f);
        }
    }

    public void delayedStart() {
        this.delayedStart = true;
        Start();
    }

    @SimpleFunction(description = "Pauses playback of the video.  Playback can be resumed at the same location by calling the <code>Start</code> method.")
    public void Pause() {
        Log.i("VideoPlayer", "Calling Pause");
        if (this.inFullScreen) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, this, null);
            this.delayedStart = false;
            return;
        }
        this.delayedStart = false;
        this.videoView.pause();
    }

    @SimpleFunction(description = "Seeks to the requested time (specified in milliseconds) in the video. Note that if the video is paused, the frame shown will not be updated by the seek. ")
    public void SeekTo(int ms) {
        Log.i("VideoPlayer", "Calling SeekTo");
        if (ms < 0) {
            ms = 0;
        }
        if (this.inFullScreen) {
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, this, Integer.valueOf(ms));
        } else {
            this.videoView.seekTo(ms);
        }
    }

    @SimpleFunction(description = "Returns duration of the video in milliseconds.")
    public int GetDuration() {
        Log.i("VideoPlayer", "Calling GetDuration");
        if (this.inFullScreen) {
            Bundle result = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, this, null);
            if (result.getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
                return result.getInt(FullScreenVideoUtil.ACTION_DATA);
            }
            return 0;
        }
        return this.videoView.getDuration();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer m) {
        Completed();
    }

    @SimpleEvent
    public void Completed() {
        EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer m, int what, int extra) {
        this.videoView.invalidateMediaPlayer(true);
        this.delayedStart = false;
        this.mediaReady = false;
        Log.e("VideoPlayer", "onError: what is " + what + " 0x" + Integer.toHexString(what) + ", extra is " + extra + " 0x" + Integer.toHexString(extra));
        this.container.$form().dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer newMediaPlayer) {
        this.mediaReady = true;
        this.delayedStart = false;
        this.mPlayer = newMediaPlayer;
        this.videoView.setMediaPlayer(this.mPlayer, true);
        if (this.delayedStart) {
            Start();
        }
    }

    @SimpleEvent(description = "The VideoPlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public void VideoPlayerError(String message) {
    }

    @Override // com.google.appinventor.components.runtime.OnDestroyListener
    public void onDestroy() {
        prepareToDie();
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.videoView.isPlaying()) {
            this.videoView.stopPlayback();
        }
        this.videoView.setVideoURI(null);
        this.videoView.clearAnimation();
        this.delayedStart = false;
        this.mediaReady = false;
        if (this.inFullScreen) {
            Bundle data = new Bundle();
            data.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, false);
            this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, data);
        }
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public int Width() {
        return super.Width();
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty(userVisible = true)
    public void Width(int width) {
        super.Width(width);
        this.videoView.changeVideoSize(width, this.videoView.forcedHeight);
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public int Height() {
        return super.Height();
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty(userVisible = true)
    public void Height(int height) {
        super.Height(height);
        this.videoView.changeVideoSize(this.videoView.forcedWidth, height);
    }

    @SimpleProperty
    public boolean FullScreen() {
        return this.inFullScreen;
    }

    @SimpleProperty(userVisible = true)
    public void FullScreen(boolean value) {
        if (value && SdkLevel.getLevel() <= 4) {
            this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen(true)", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_UNSUPPORTED, new Object[0]);
        } else if (value != this.inFullScreen) {
            if (value) {
                Bundle data = new Bundle();
                data.putInt(FullScreenVideoUtil.VIDEOPLAYER_POSITION, this.videoView.getCurrentPosition());
                data.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_PLAYING, this.videoView.isPlaying());
                this.videoView.pause();
                data.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, true);
                data.putString(FullScreenVideoUtil.VIDEOPLAYER_SOURCE, this.sourcePath);
                if (this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, data).getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
                    this.inFullScreen = true;
                    return;
                }
                this.inFullScreen = false;
                this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_UNAVAILBLE, "");
                return;
            }
            Bundle values = new Bundle();
            values.putBoolean(FullScreenVideoUtil.VIDEOPLAYER_FULLSCREEN, false);
            Bundle result = this.container.$form().fullScreenVideoAction(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, this, values);
            if (result.getBoolean(FullScreenVideoUtil.ACTION_SUCESS)) {
                fullScreenKilled(result);
                return;
            }
            this.inFullScreen = true;
            this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", ErrorMessages.ERROR_VIDEOPLAYER_FULLSCREEN_CANT_EXIT, "");
        }
    }

    public void fullScreenKilled(Bundle data) {
        this.inFullScreen = false;
        String newSource = data.getString(FullScreenVideoUtil.VIDEOPLAYER_SOURCE);
        if (!newSource.equals(this.sourcePath)) {
            Source(newSource);
        }
        this.videoView.setVisibility(0);
        this.videoView.requestLayout();
        SeekTo(data.getInt(FullScreenVideoUtil.VIDEOPLAYER_POSITION));
        if (data.getBoolean(FullScreenVideoUtil.VIDEOPLAYER_PLAYING)) {
            Start();
        }
    }

    public int getPassedWidth() {
        return this.videoView.forcedWidth;
    }

    public int getPassedHeight() {
        return this.videoView.forcedHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ResizableVideoView extends VideoView {
        public int forcedHeight;
        public int forcedWidth;
        private Boolean mFoundMediaPlayer;
        private MediaPlayer mVideoPlayer;

        public ResizableVideoView(Context context) {
            super(context);
            this.mFoundMediaPlayer = false;
            this.forcedWidth = -1;
            this.forcedHeight = -1;
        }

        @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
        public void onMeasure(int specwidth, int specheight) {
            Log.i("VideoPlayer..onMeasure", "AI setting dimensions as:" + this.forcedWidth + ":" + this.forcedHeight);
            Log.i("VideoPlayer..onMeasure", "Dimenions from super>>" + View.MeasureSpec.getSize(specwidth) + ":" + View.MeasureSpec.getSize(specheight));
            int width = ComponentConstants.VIDEOPLAYER_PREFERRED_WIDTH;
            int height = ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT;
            switch (this.forcedWidth) {
                case -2:
                    switch (View.MeasureSpec.getMode(specwidth)) {
                        case Integer.MIN_VALUE:
                        case Declaration.MODULE_REFERENCE /* 1073741824 */:
                            width = View.MeasureSpec.getSize(specwidth);
                            break;
                        case 0:
                            try {
                                width = ((View) getParent()).getMeasuredWidth();
                                break;
                            } catch (ClassCastException e) {
                                width = ComponentConstants.VIDEOPLAYER_PREFERRED_WIDTH;
                                break;
                            } catch (NullPointerException e2) {
                                width = ComponentConstants.VIDEOPLAYER_PREFERRED_WIDTH;
                                break;
                            }
                    }
                case -1:
                    if (this.mFoundMediaPlayer.booleanValue()) {
                        try {
                            width = this.mVideoPlayer.getVideoWidth();
                            Log.i("VideoPlayer.onMeasure", "Got width from MediaPlayer>" + width);
                            break;
                        } catch (NullPointerException nullVideoPlayer) {
                            Log.e("VideoPlayer..onMeasure", "Failed to get MediaPlayer for width:\n" + nullVideoPlayer.getMessage());
                            width = ComponentConstants.VIDEOPLAYER_PREFERRED_WIDTH;
                            break;
                        }
                    }
                    break;
                default:
                    width = this.forcedWidth;
                    break;
            }
            switch (this.forcedHeight) {
                case -2:
                    switch (View.MeasureSpec.getMode(specheight)) {
                        case Integer.MIN_VALUE:
                        case Declaration.MODULE_REFERENCE /* 1073741824 */:
                            height = View.MeasureSpec.getSize(specheight);
                            break;
                    }
                case -1:
                    if (this.mFoundMediaPlayer.booleanValue()) {
                        try {
                            height = this.mVideoPlayer.getVideoHeight();
                            Log.i("VideoPlayer.onMeasure", "Got height from MediaPlayer>" + height);
                            break;
                        } catch (NullPointerException nullVideoPlayer2) {
                            Log.e("VideoPlayer..onMeasure", "Failed to get MediaPlayer for height:\n" + nullVideoPlayer2.getMessage());
                            height = ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT;
                            break;
                        }
                    }
                    break;
                default:
                    height = this.forcedHeight;
                    break;
            }
            Log.i("VideoPlayer.onMeasure", "Setting dimensions to:" + width + "x" + height);
            getHolder().setFixedSize(width, height);
            setMeasuredDimension(width, height);
        }

        public void changeVideoSize(int newWidth, int newHeight) {
            this.forcedWidth = newWidth;
            this.forcedHeight = newHeight;
            forceLayout();
            invalidate();
        }

        public void invalidateMediaPlayer(boolean triggerRedraw) {
            this.mFoundMediaPlayer = false;
            this.mVideoPlayer = null;
            if (triggerRedraw) {
                forceLayout();
                invalidate();
            }
        }

        public void setMediaPlayer(MediaPlayer newMediaPlayer, boolean triggerRedraw) {
            this.mVideoPlayer = newMediaPlayer;
            this.mFoundMediaPlayer = true;
            if (triggerRedraw) {
                forceLayout();
                invalidate();
            }
        }
    }
}
