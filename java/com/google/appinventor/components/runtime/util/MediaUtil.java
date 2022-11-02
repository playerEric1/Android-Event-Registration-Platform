package com.google.appinventor.components.runtime.util;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.provider.Contacts;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MediaUtil {
    private static final String LOG_TAG = "MediaUtil";
    private static final String REPL_ASSET_DIR = "/sdcard/AppInventor/assets/";
    private static final Map<String, File> tempFileMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum MediaSource {
        ASSET,
        REPL_ASSET,
        SDCARD,
        FILE_URL,
        URL,
        CONTENT_URI,
        CONTACT_URI
    }

    private MediaUtil() {
    }

    private static String replAssetPath(String assetName) {
        return REPL_ASSET_DIR + assetName;
    }

    static String fileUrlToFilePath(String mediaPath) throws IOException {
        try {
            return new File(new URL(mediaPath).toURI()).getAbsolutePath();
        } catch (IllegalArgumentException e) {
            throw new IOException("Unable to determine file path of file url " + mediaPath);
        } catch (Exception e2) {
            throw new IOException("Unable to determine file path of file url " + mediaPath);
        }
    }

    private static MediaSource determineMediaSource(Form form, String mediaPath) {
        MediaSource mediaSource;
        if (mediaPath.startsWith("/sdcard/") || mediaPath.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            return MediaSource.SDCARD;
        }
        if (mediaPath.startsWith("content://contacts/")) {
            return MediaSource.CONTACT_URI;
        }
        if (mediaPath.startsWith("content://")) {
            return MediaSource.CONTENT_URI;
        }
        try {
            new URL(mediaPath);
            if (mediaPath.startsWith("file:")) {
                mediaSource = MediaSource.FILE_URL;
            } else {
                mediaSource = MediaSource.URL;
            }
            return mediaSource;
        } catch (MalformedURLException e) {
            if (form instanceof ReplForm) {
                if (((ReplForm) form).isAssetsLoaded()) {
                    return MediaSource.REPL_ASSET;
                }
                return MediaSource.ASSET;
            }
            return MediaSource.ASSET;
        }
    }

    private static String findCaseinsensitivePath(Form form, String mediaPath) throws IOException {
        String[] mediaPathlist = form.getAssets().list("");
        int l = Array.getLength(mediaPathlist);
        for (int i = 0; i < l; i++) {
            String temp = mediaPathlist[i];
            if (temp.equalsIgnoreCase(mediaPath)) {
                return temp;
            }
        }
        return null;
    }

    private static InputStream getAssetsIgnoreCaseInputStream(Form form, String mediaPath) throws IOException {
        try {
            return form.getAssets().open(mediaPath);
        } catch (IOException e) {
            if (findCaseinsensitivePath(form, mediaPath) == null) {
                throw e;
            }
            String path = findCaseinsensitivePath(form, mediaPath);
            return form.getAssets().open(path);
        }
    }

    private static InputStream openMedia(Form form, String mediaPath, MediaSource mediaSource) throws IOException {
        InputStream is;
        switch (mediaSource) {
            case ASSET:
                return getAssetsIgnoreCaseInputStream(form, mediaPath);
            case REPL_ASSET:
                return new FileInputStream(replAssetPath(mediaPath));
            case SDCARD:
                return new FileInputStream(mediaPath);
            case FILE_URL:
            case URL:
                return new URL(mediaPath).openStream();
            case CONTENT_URI:
                return form.getContentResolver().openInputStream(Uri.parse(mediaPath));
            case CONTACT_URI:
                if (SdkLevel.getLevel() >= 12) {
                    is = HoneycombUtil.openContactPhotoInputStreamHelper(form.getContentResolver(), Uri.parse(mediaPath));
                } else {
                    is = Contacts.People.openContactPhotoInputStream(form.getContentResolver(), Uri.parse(mediaPath));
                }
                if (is != null) {
                    return is;
                }
                throw new IOException("Unable to open contact photo " + mediaPath + ".");
            default:
                throw new IOException("Unable to open media " + mediaPath + ".");
        }
    }

    public static InputStream openMedia(Form form, String mediaPath) throws IOException {
        return openMedia(form, mediaPath, determineMediaSource(form, mediaPath));
    }

    public static File copyMediaToTempFile(Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        return copyMediaToTempFile(form, mediaPath, mediaSource);
    }

    private static File copyMediaToTempFile(Form form, String mediaPath, MediaSource mediaSource) throws IOException {
        InputStream in = openMedia(form, mediaPath, mediaSource);
        File file = null;
        try {
            try {
                file = File.createTempFile("AI_Media_", null);
                file.deleteOnExit();
                FileUtil.writeStreamToFile(in, file.getAbsolutePath());
                return file;
            } catch (IOException e) {
                if (file != null) {
                    Log.e(LOG_TAG, "Could not copy media " + mediaPath + " to temp file " + file.getAbsolutePath());
                    file.delete();
                } else {
                    Log.e(LOG_TAG, "Could not copy media " + mediaPath + " to temp file.");
                }
                throw e;
            }
        } finally {
            in.close();
        }
    }

    private static File cacheMediaTempFile(Form form, String mediaPath, MediaSource mediaSource) throws IOException {
        File tempFile = tempFileMap.get(mediaPath);
        if (tempFile == null || !tempFile.exists()) {
            Log.i(LOG_TAG, "Copying media " + mediaPath + " to temp file...");
            File tempFile2 = copyMediaToTempFile(form, mediaPath, mediaSource);
            Log.i(LOG_TAG, "Finished copying media " + mediaPath + " to temp file " + tempFile2.getAbsolutePath());
            tempFileMap.put(mediaPath, tempFile2);
            return tempFile2;
        }
        return tempFile;
    }

    public static BitmapDrawable getBitmapDrawable(Form form, String mediaPath) throws IOException {
        if (mediaPath == null || mediaPath.length() == 0) {
            return null;
        }
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        try {
            InputStream is2 = openMedia(form, mediaPath, mediaSource);
            try {
                BitmapFactory.Options options = getBitmapOptions(form, is2);
                is2.close();
                is2 = openMedia(form, mediaPath, mediaSource);
                try {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(decodeStream(is2, null, options));
                } finally {
                    if (is2 != null) {
                    }
                }
            } finally {
                is2.close();
            }
        } catch (IOException e) {
            if (mediaSource == MediaSource.CONTACT_URI) {
                return new BitmapDrawable(BitmapFactory.decodeResource(form.getResources(), 17301606, null));
            }
            throw e;
        }
    }

    private static Bitmap decodeStream(InputStream is, Rect outPadding, BitmapFactory.Options opts) {
        return BitmapFactory.decodeStream(new FlushedInputStream(is), outPadding, opts);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0;
            while (totalBytesSkipped < n) {
                long bytesSkipped = this.in.skip(n - totalBytesSkipped);
                if (bytesSkipped == 0) {
                    if (read() < 0) {
                        break;
                    }
                    bytesSkipped = 1;
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }

    private static BitmapFactory.Options getBitmapOptions(Form form, InputStream is) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeStream(is, null, options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        Display display = ((WindowManager) form.getSystemService("window")).getDefaultDisplay();
        int maxWidth = display.getWidth() * 2;
        int maxHeight = display.getHeight() * 2;
        int sampleSize = 1;
        while (imageWidth / sampleSize > maxWidth && imageHeight / sampleSize > maxHeight) {
            sampleSize *= 2;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = sampleSize;
        return options2;
    }

    private static AssetFileDescriptor getAssetsIgnoreCaseAfd(Form form, String mediaPath) throws IOException {
        try {
            return form.getAssets().openFd(mediaPath);
        } catch (IOException e) {
            if (findCaseinsensitivePath(form, mediaPath) == null) {
                throw e;
            }
            String path = findCaseinsensitivePath(form, mediaPath);
            return form.getAssets().openFd(path);
        }
    }

    public static int loadSoundPool(SoundPool soundPool, Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        switch (mediaSource) {
            case ASSET:
                return soundPool.load(getAssetsIgnoreCaseAfd(form, mediaPath), 1);
            case REPL_ASSET:
                return soundPool.load(replAssetPath(mediaPath), 1);
            case SDCARD:
                return soundPool.load(mediaPath, 1);
            case FILE_URL:
                return soundPool.load(fileUrlToFilePath(mediaPath), 1);
            case URL:
            case CONTENT_URI:
                File tempFile = cacheMediaTempFile(form, mediaPath, mediaSource);
                return soundPool.load(tempFile.getAbsolutePath(), 1);
            case CONTACT_URI:
                throw new IOException("Unable to load audio for contact " + mediaPath + ".");
            default:
                throw new IOException("Unable to load audio " + mediaPath + ".");
        }
    }

    public static void loadMediaPlayer(MediaPlayer mediaPlayer, Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        switch (mediaSource) {
            case ASSET:
                AssetFileDescriptor afd = getAssetsIgnoreCaseAfd(form, mediaPath);
                try {
                    FileDescriptor fd = afd.getFileDescriptor();
                    long offset = afd.getStartOffset();
                    long length = afd.getLength();
                    mediaPlayer.setDataSource(fd, offset, length);
                    return;
                } finally {
                    afd.close();
                }
            case REPL_ASSET:
                mediaPlayer.setDataSource(replAssetPath(mediaPath));
                return;
            case SDCARD:
                mediaPlayer.setDataSource(mediaPath);
                return;
            case FILE_URL:
                mediaPlayer.setDataSource(fileUrlToFilePath(mediaPath));
                return;
            case URL:
                mediaPlayer.setDataSource(mediaPath);
                return;
            case CONTENT_URI:
                mediaPlayer.setDataSource(form, Uri.parse(mediaPath));
                return;
            case CONTACT_URI:
                throw new IOException("Unable to load audio or video for contact " + mediaPath + ".");
            default:
                throw new IOException("Unable to load audio or video " + mediaPath + ".");
        }
    }

    public static void loadVideoView(VideoView videoView, Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        switch (mediaSource) {
            case ASSET:
            case URL:
                File tempFile = cacheMediaTempFile(form, mediaPath, mediaSource);
                videoView.setVideoPath(tempFile.getAbsolutePath());
                return;
            case REPL_ASSET:
                videoView.setVideoPath(replAssetPath(mediaPath));
                return;
            case SDCARD:
                videoView.setVideoPath(mediaPath);
                return;
            case FILE_URL:
                videoView.setVideoPath(fileUrlToFilePath(mediaPath));
                return;
            case CONTENT_URI:
                videoView.setVideoURI(Uri.parse(mediaPath));
                return;
            case CONTACT_URI:
                throw new IOException("Unable to load video for contact " + mediaPath + ".");
            default:
                throw new IOException("Unable to load video " + mediaPath + ".");
        }
    }
}
