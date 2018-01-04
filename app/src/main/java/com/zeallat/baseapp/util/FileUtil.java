package com.zeallat.baseapp.util;

import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;

/**
 * FileUtil.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-21.
 */
public class FileUtil {

    public static String getMimeType(Uri uri, ContentResolver contentResolver) {
        String mimeType;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            mimeType = contentResolver.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

//    public static String fixPhotoDegree(Context context, String path) {
//        int degree = RichUtils.getPhotoOrientationDegree(path);
//
//        Bitmap bitmap = null;
//        if (RichUtils.getPhotoWidth(path) > 3000) {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 2;
//            bitmap = BitmapFactory.decodeFile(path, options);
//        } else {
//            bitmap = BitmapFactory.decodeFile(path);
//        }
//
//        bitmap = RichUtils.rotate(bitmap, degree);
//        path = RichUtils.saveBitmapToFile(context, bitmap);
//        return path;
//    }
}
