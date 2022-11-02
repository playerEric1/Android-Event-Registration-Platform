package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import java.io.IOException;

@UsesPermissions(permissionNames = "android.permission.RECORD_AUDIO")
@DesignerComponent(category = ComponentCategory.MEDIA, description = "<p>Multimedia component that records audio.</p>", iconName = "images/soundRecorder.png", nonVisible = true, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public final class SoundRecorder extends AndroidNonvisibleComponent implements Component, MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
    private static final String TAG = "SoundRecorder";
    private RecordingController controller;

    /* loaded from: classes.dex */
    private class RecordingController {
        final String file = FileUtil.getRecordingFile("3gp").getAbsolutePath();
        final MediaRecorder recorder = new MediaRecorder();

        RecordingController() throws IOException {
            this.recorder.setAudioSource(1);
            this.recorder.setOutputFormat(1);
            this.recorder.setAudioEncoder(1);
            Log.i(SoundRecorder.TAG, "Setting output file to " + this.file);
            this.recorder.setOutputFile(this.file);
            Log.i(SoundRecorder.TAG, "preparing");
            this.recorder.prepare();
            this.recorder.setOnErrorListener(SoundRecorder.this);
            this.recorder.setOnInfoListener(SoundRecorder.this);
        }

        void start() {
            Log.i(SoundRecorder.TAG, "starting");
            this.recorder.start();
        }

        void stop() {
            this.recorder.setOnErrorListener(null);
            this.recorder.setOnInfoListener(null);
            this.recorder.stop();
            this.recorder.reset();
            this.recorder.release();
        }
    }

    public SoundRecorder(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction
    public void Start() {
        if (this.controller != null) {
            Log.i(TAG, "Start() called, but already recording to " + this.controller.file);
            return;
        }
        Log.i(TAG, "Start() called");
        if (!Environment.getExternalStorageState().equals("mounted")) {
            this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE, new Object[0]);
            return;
        }
        try {
            this.controller = new RecordingController();
            try {
                this.controller.start();
                StartedRecording();
            } catch (Throwable t) {
                this.controller.stop();
                this.controller = null;
                this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_SOUND_RECORDER_CANNOT_CREATE, t.getMessage());
            }
        } catch (Throwable t2) {
            this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_SOUND_RECORDER_CANNOT_CREATE, t2.getMessage());
        }
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder affectedRecorder, int what, int extra) {
        if (this.controller == null || affectedRecorder != this.controller.recorder) {
            Log.w(TAG, "onError called with wrong recorder. Ignoring.");
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "onError", ErrorMessages.ERROR_SOUND_RECORDER, new Object[0]);
        try {
            this.controller.stop();
        } catch (Throwable e) {
            try {
                Log.w(TAG, e.getMessage());
            } finally {
                this.controller = null;
                StoppedRecording();
            }
        }
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder affectedRecorder, int what, int extra) {
        if (this.controller == null || affectedRecorder != this.controller.recorder) {
            Log.w(TAG, "onInfo called with wrong recorder. Ignoring.");
            return;
        }
        Log.i(TAG, "Recoverable condition while recording. Will attempt to stop normally.");
        this.controller.recorder.stop();
    }

    @SimpleFunction
    public void Stop() {
        if (this.controller == null) {
            Log.i(TAG, "Stop() called, but already stopped.");
            return;
        }
        try {
            Log.i(TAG, "Stop() called");
            Log.i(TAG, "stopping");
            this.controller.stop();
            Log.i(TAG, "Firing AfterSoundRecorded with " + this.controller.file);
            AfterSoundRecorded(this.controller.file);
        } catch (Throwable th) {
            try {
                this.form.dispatchErrorOccurredEvent(this, "Stop", ErrorMessages.ERROR_SOUND_RECORDER, new Object[0]);
            } finally {
                this.controller = null;
                StoppedRecording();
            }
        }
    }

    @SimpleEvent(description = "Provides the location of the newly created sound.")
    public void AfterSoundRecorded(String sound) {
        EventDispatcher.dispatchEvent(this, "AfterSoundRecorded", sound);
    }

    @SimpleEvent(description = "Indicates that the recorder has started, and can be stopped.")
    public void StartedRecording() {
        EventDispatcher.dispatchEvent(this, "StartedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has stopped, and can be started again.")
    public void StoppedRecording() {
        EventDispatcher.dispatchEvent(this, "StoppedRecording", new Object[0]);
    }
}
