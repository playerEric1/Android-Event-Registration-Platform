package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.Contacts;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;

/* loaded from: classes.dex */
public class EmailAddressAdapter extends ResourceCursorAdapter {
    private static final boolean DEBUG = false;
    public static final int PRE_HONEYCOMB_DATA_INDEX = 2;
    public static final int PRE_HONEYCOMB_NAME_INDEX = 1;
    private static String SORT_ORDER = null;
    private static final String TAG = "EmailAddressAdapter";
    private ContentResolver contentResolver;
    private Context context;
    private static final String[] PRE_HONEYCOMB_PROJECTION = {"_id", "name", "data"};
    private static final String[] POST_HONEYCOMB_PROJECTION = HoneycombUtil.getEmailAdapterProjection();

    public EmailAddressAdapter(Context context) {
        super(context, 17367050, null);
        this.contentResolver = context.getContentResolver();
        this.context = context;
        if (SdkLevel.getLevel() >= 12) {
            SORT_ORDER = HoneycombUtil.getTimesContacted() + " DESC, " + HoneycombUtil.getDisplayName();
        } else {
            SORT_ORDER = "times_contacted DESC, name";
        }
    }

    @Override // android.widget.CursorAdapter
    public final String convertToString(Cursor cursor) {
        String name;
        String address;
        int POST_HONEYCOMB_NAME_INDEX = cursor.getColumnIndex(HoneycombUtil.getDisplayName());
        int POST_HONEYCOMB_EMAIL_INDEX = cursor.getColumnIndex(HoneycombUtil.getEmailAddress());
        if (SdkLevel.getLevel() >= 12) {
            name = cursor.getString(POST_HONEYCOMB_NAME_INDEX);
            address = cursor.getString(POST_HONEYCOMB_EMAIL_INDEX);
        } else {
            name = cursor.getString(1);
            address = cursor.getString(2);
        }
        return new Rfc822Token(name, address, null).toString();
    }

    private final String makeDisplayString(Cursor cursor) {
        String name;
        String address;
        int POST_HONEYCOMB_NAME_INDEX = cursor.getColumnIndex(HoneycombUtil.getDisplayName());
        int POST_HONEYCOMB_EMAIL_INDEX = cursor.getColumnIndex(HoneycombUtil.getEmailAddress());
        StringBuilder s = new StringBuilder();
        boolean flag = false;
        if (SdkLevel.getLevel() >= 12) {
            name = cursor.getString(POST_HONEYCOMB_NAME_INDEX);
            address = cursor.getString(POST_HONEYCOMB_EMAIL_INDEX);
        } else {
            name = cursor.getString(1);
            address = cursor.getString(2);
        }
        if (!TextUtils.isEmpty(name)) {
            s.append(name);
            flag = true;
        }
        if (flag) {
            s.append(" <");
        }
        s.append(address);
        if (flag) {
            s.append(">");
        }
        return s.toString();
    }

    @Override // android.widget.CursorAdapter
    public final void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view).setText(makeDisplayString(cursor));
    }

    @Override // android.widget.CursorAdapter
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        Uri db = null;
        StringBuilder s = new StringBuilder();
        if (constraint != null) {
            String filter = DatabaseUtils.sqlEscapeString(constraint.toString() + '%');
            if (SdkLevel.getLevel() >= 12) {
                db = HoneycombUtil.getDataContentUri();
                s.append("(" + HoneycombUtil.getDataMimeType() + "='" + HoneycombUtil.getEmailType() + "')");
                s.append(" AND ");
                s.append("(display_name LIKE ");
                s.append(filter);
                s.append(")");
            } else {
                db = Contacts.ContactMethods.CONTENT_EMAIL_URI;
                s.append("(name LIKE ");
                s.append(filter);
                s.append(") OR (display_name LIKE ");
                s.append(filter);
                s.append(")");
            }
        }
        String where = s.toString();
        if (SdkLevel.getLevel() >= 12) {
            return this.contentResolver.query(db, POST_HONEYCOMB_PROJECTION, where, null, SORT_ORDER);
        }
        return this.contentResolver.query(db, PRE_HONEYCOMB_PROJECTION, where, null, SORT_ORDER);
    }
}
