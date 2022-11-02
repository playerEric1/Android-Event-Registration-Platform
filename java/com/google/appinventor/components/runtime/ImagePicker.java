package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE")
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A special-purpose button. When the user taps an image picker, the device's image gallery appears, and the user can choose an image. After an image is picked, it is saved on the SD card and the <code>ImageFile</code> property will be the name of the file where the image is stored. In order to not fill up storage, a maximum of 10 images will be stored.  Picking more images will delete previous images, in order from oldest to newest.", version = 5)
@SimpleObject
/* loaded from: classes.dex */
public class ImagePicker extends Picker implements ActivityResultListener {
    private static final String FILE_PREFIX = "picked_image";
    private static final String LOG_TAG = "ImagePicker";
    private static final String imagePickerDirectoryName = "/Pictures/_app_inventor_image_picker";
    private static int maxSavedFiles = 10;
    private String selectionSavedImage;
    private String selectionURI;

    public ImagePicker(ComponentContainer container) {
        super(container);
        this.selectionSavedImage = "";
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Path to the file containing the image that was selected.")
    public String Selection() {
        return this.selectionSavedImage;
    }

    @Override // com.google.appinventor.components.runtime.Picker
    protected Intent getIntent() {
        return new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    }

    @Override // com.google.appinventor.components.runtime.ActivityResultListener
    public void resultReturned(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode && resultCode == -1) {
            Uri selectedImage = data.getData();
            this.selectionURI = selectedImage.toString();
            Log.i(LOG_TAG, "selectionURI = " + this.selectionURI);
            ContentResolver cR = this.container.$context().getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String extension = "." + mime.getExtensionFromMimeType(cR.getType(selectedImage));
            Log.i(LOG_TAG, "extension = " + extension);
            saveSelectedImageToExternalStorage(extension);
            AfterPicking();
        }
    }

    private void saveSelectedImageToExternalStorage(String extension) {
        this.selectionSavedImage = "";
        try {
            java.io.File tempFile = MediaUtil.copyMediaToTempFile(this.container.$form(), this.selectionURI);
            Log.i(LOG_TAG, "temp file path is: " + tempFile.getPath());
            copyToExternalStorageAndDeleteSource(tempFile, extension);
        } catch (IOException e) {
            Log.i(LOG_TAG, "copyMediaToTempFile failed: " + e.getMessage());
            this.container.$form().dispatchErrorOccurredEvent(this, LOG_TAG, ErrorMessages.ERROR_CANNOT_COPY_MEDIA, e.getMessage());
        }
    }

    private void copyToExternalStorageAndDeleteSource(java.io.File source, String extension) {
        InputStream inStream;
        OutputStream outStream;
        java.io.File dest = null;
        String fullDirname = Environment.getExternalStorageDirectory() + imagePickerDirectoryName;
        java.io.File destDirectory = new java.io.File(fullDirname);
        try {
            destDirectory.mkdirs();
            dest = java.io.File.createTempFile(FILE_PREFIX, extension, destDirectory);
            this.selectionSavedImage = dest.getPath();
            Log.i(LOG_TAG, "saved file path is: " + this.selectionSavedImage);
            inStream = new FileInputStream(source);
            try {
                outStream = new FileOutputStream(dest);
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int length = inStream.read(buffer);
                if (length <= 0) {
                    break;
                }
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            Log.i(LOG_TAG, "Image was copied to " + this.selectionSavedImage);
        } catch (IOException e3) {
            e = e3;
            String err = "destination is " + this.selectionSavedImage + ": error is " + e.getMessage();
            Log.i(LOG_TAG, "copyFile failed. " + err);
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveImage", ErrorMessages.ERROR_CANNOT_SAVE_IMAGE, err);
            this.selectionSavedImage = "";
            dest.delete();
            source.delete();
            trimDirectory(maxSavedFiles, destDirectory);
        }
        source.delete();
        trimDirectory(maxSavedFiles, destDirectory);
    }

    private void trimDirectory(int maxSavedFiles2, java.io.File directory) {
        java.io.File[] files = directory.listFiles();
        Arrays.sort(files, new Comparator<java.io.File>() { // from class: com.google.appinventor.components.runtime.ImagePicker.1
            @Override // java.util.Comparator
            public int compare(java.io.File f1, java.io.File f2) {
                return Long.valueOf(f1.lastModified()).compareTo(Long.valueOf(f2.lastModified()));
            }
        });
        int excess = files.length - maxSavedFiles2;
        for (int i = 0; i < excess; i++) {
            files[i].delete();
        }
    }
}
