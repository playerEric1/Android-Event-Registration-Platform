package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FroyoUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;

@UsesPermissions(permissionNames = "android.permission.VIBRATE, android.permission.INTERNET")
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Multimedia component that plays audio and controls phone vibration.  The name of a multimedia field is specified in the <code>Source</code> property, which can be set in the Designer or in the Blocks Editor.  The length of time for a vibration is specified in the Blocks Editor in milliseconds (thousandths of a second).\n<p>For supported audio formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p>\n<p>This component is best for long sound files, such as songs, while the <code>Sound</code> component is more efficient for short files, such as sound effects.</p>", iconName = "images/player.png", nonVisible = true, version = 6)
@SimpleObject
/* loaded from: classes.dex */
public final class Player extends AndroidNonvisibleComponent implements Component, MediaPlayer.OnCompletionListener, OnPauseListener, OnResumeListener, OnDestroyListener, OnStopListener, Deleteable {
    private static final boolean audioFocusSupported;
    private final Activity activity;
    private Object afChangeListener;
    private AudioManager am;
    private boolean focusOn;
    private boolean loop;
    private boolean playOnlyInForeground;
    private MediaPlayer player;
    public int playerState;
    private String sourcePath;
    private final Vibrator vibe;

    static {
        if (SdkLevel.getLevel() >= 8) {
            audioFocusSupported = true;
        } else {
            audioFocusSupported = false;
        }
    }

    public Player(ComponentContainer container) {
        super(container.$form());
        this.activity = container.$context();
        this.sourcePath = "";
        this.vibe = (Vibrator) this.form.getSystemService("vibrator");
        this.form.registerForOnDestroy(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnPause(this);
        this.form.registerForOnStop(this);
        this.form.setVolumeControlStream(3);
        this.loop = false;
        this.playOnlyInForeground = false;
        this.focusOn = false;
        this.am = audioFocusSupported ? FroyoUtil.setAudioManager(this.activity) : null;
        this.afChangeListener = audioFocusSupported ? FroyoUtil.setAudioFocusChangeListener(this) : null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Source() {
        return this.sourcePath;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void Source(String path) {
        if (path == null) {
            path = "";
        }
        this.sourcePath = path;
        if (this.playerState == 1 || this.playerState == 2 || this.playerState == 3) {
            this.player.stop();
            this.playerState = 0;
        }
        if (this.player != null) {
            this.player.release();
            this.player = null;
        }
        if (this.sourcePath.length() > 0) {
            this.player = new MediaPlayer();
            this.player.setOnCompletionListener(this);
            try {
                MediaUtil.loadMediaPlayer(this.player, this.form, this.sourcePath);
                this.player.setAudioStreamType(3);
                if (audioFocusSupported) {
                    requestPermanentFocus();
                }
                prepare();
            } catch (IOException e) {
                this.player.release();
                this.player = null;
                this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, this.sourcePath);
            }
        }
    }

    private void requestPermanentFocus() {
        this.focusOn = FroyoUtil.focusRequestGranted(this.am, this.afChangeListener);
        if (!this.focusOn) {
            this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_FOCUS_MEDIA, this.sourcePath);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports whether the media is playing")
    public boolean IsPlaying() {
        if (this.playerState == 1 || this.playerState == 2) {
            return this.player.isPlaying();
        }
        return false;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the player will loop when it plays. Setting Loop while the player is playing will affect the current playing.")
    public boolean Loop() {
        return this.loop;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Loop(boolean shouldLoop) {
        if (this.playerState == 1 || this.playerState == 2 || this.playerState == 3) {
            this.player.setLooping(shouldLoop);
        }
        this.loop = shouldLoop;
    }

    @SimpleProperty(description = "Sets the volume to a number between 0 and 100")
    @DesignerProperty(defaultValue = "50", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void Volume(int vol) {
        if (this.playerState == 1 || this.playerState == 2 || this.playerState == 3) {
            if (vol > 100 || vol < 0) {
                throw new IllegalArgumentError("Volume must be set to a number between 0 and 100");
            }
            this.player.setVolume(vol / 100.0f, vol / 100.0f);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the player will pause playing when leaving the current screen; if false (default option), the player continues playing whenever the current screen is displaying or not.")
    public boolean PlayOnlyInForeground() {
        return this.playOnlyInForeground;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void PlayOnlyInForeground(boolean shouldForeground) {
        this.playOnlyInForeground = shouldForeground;
    }

    @SimpleFunction
    public void Start() {
        if (audioFocusSupported && !this.focusOn) {
            requestPermanentFocus();
        }
        if (this.playerState == 1 || this.playerState == 2 || this.playerState == 3 || this.playerState == 4) {
            this.player.setLooping(this.loop);
            this.player.start();
            this.playerState = 2;
        }
    }

    @SimpleFunction
    public void Pause() {
        if (this.player != null) {
            boolean wasPlaying = this.player.isPlaying();
            if (this.playerState == 2) {
                this.player.pause();
                if (wasPlaying) {
                    this.playerState = 3;
                }
            }
        }
    }

    public void pause() {
        if (this.player != null && this.playerState == 2) {
            this.player.pause();
            this.playerState = 4;
        }
    }

    @SimpleFunction
    public void Stop() {
        if (audioFocusSupported && this.focusOn) {
            abandonFocus();
        }
        if (this.playerState == 2 || this.playerState == 3 || this.playerState == 4) {
            this.player.stop();
            prepare();
            this.player.seekTo(0);
        }
    }

    private void abandonFocus() {
        FroyoUtil.abandonFocus(this.am, this.afChangeListener);
        this.focusOn = false;
    }

    @SimpleFunction
    public void Vibrate(long milliseconds) {
        this.vibe.vibrate(milliseconds);
    }

    @SimpleEvent(description = "The PlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public void PlayerError(String message) {
    }

    private void prepare() {
        try {
            this.player.prepare();
            this.playerState = 1;
        } catch (IOException e) {
            this.player.release();
            this.player = null;
            this.playerState = 0;
            this.form.dispatchErrorOccurredEvent(this, "Source", ErrorMessages.ERROR_UNABLE_TO_PREPARE_MEDIA, this.sourcePath);
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer m) {
        Completed();
    }

    @SimpleEvent
    public void Completed() {
        EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    @SimpleEvent(description = "This event is signaled when another player has started (and the current player is playing or paused, but not stopped).")
    public void OtherPlayerStarted() {
        EventDispatcher.dispatchEvent(this, "OtherPlayerStarted", new Object[0]);
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        if (this.playOnlyInForeground && this.playerState == 4) {
            Start();
        }
    }

    @Override // com.google.appinventor.components.runtime.OnPauseListener
    public void onPause() {
        if (this.player != null && this.playOnlyInForeground && this.player.isPlaying()) {
            pause();
        }
    }

    @Override // com.google.appinventor.components.runtime.OnStopListener
    public void onStop() {
        if (this.player != null && this.playOnlyInForeground && this.player.isPlaying()) {
            pause();
        }
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
        if (audioFocusSupported && this.focusOn) {
            abandonFocus();
        }
        if (this.playerState != 0) {
            this.player.stop();
        }
        this.playerState = 0;
        if (this.player != null) {
            this.player.release();
            this.player = null;
        }
        this.vibe.cancel();
    }
}
