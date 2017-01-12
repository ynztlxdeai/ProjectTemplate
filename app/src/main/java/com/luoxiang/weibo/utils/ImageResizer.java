package com.luoxiang.weibo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * projectName: 	    ImageLoaderTest
 * packageName:	        com.vincent.imageloadertest
 * className:	        ImageResizer
 * author:	            Luoxiang
 * time:	            2016/11/8	14:42
 * desc:	            图片压缩功能的实现
 */
public class ImageResizer {
    public Bitmap decodeSampleBitmapFromResource(Resources res , int resId , int reqWidth , int reqHeight){
        //首先通过设置inJustDecodeBounds = true;获取大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res , resId , options);

        //计算缩放率
        options.inSampleSize = calculateInSampleSize(options , reqWidth , reqHeight);

        //设置缩放率
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res , resId , options);
    }

    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fd , int reqWidth , int reqHeight ){
        //首先通过设置inJustDecodeBounds = true;获取大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd , null , options);

        //计算缩放率
        options.inSampleSize = calculateInSampleSize(options , reqWidth , reqHeight);

        //设置缩放率
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd , null , options);

    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0){
            return 1;
        }

        //计算图片的宽高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
