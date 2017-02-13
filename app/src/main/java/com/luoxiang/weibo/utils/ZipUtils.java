package com.luoxiang.weibo.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * packageName:	    com.luoxiang.weibo.utils
 * className:	    ZipUtils
 * author:	        Luoxiang
 * time:	        2017/2/13	9:46
 * desc:	        压缩和解压缩
 *
 * svnVersion:
 * upDateAuthor:    Vincent
 * upDate:          2017/2/13
 * upDateDesc:      TODO
 */


public class ZipUtils {
	
	//通过文件路径解压缩
	public static void unZip(String zipPath,String path) throws IOException {
		File zipFile = new File(zipPath);
		File file = new File(path);
		unZip(zipFile, file);
	}
	
	//通过文件解压缩
	public static void unZip(File zipFile, File file) throws IOException {
		InputStream is = new FileInputStream(zipFile);
		OutputStream os = new FileOutputStream(file);
		unZip(is, os);
	}
	
	//通过流解压缩
	public static void unZip(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[8192];
		int len = -1;
		GZIPInputStream gzis = new GZIPInputStream(is);
		try {
			while ((len = gzis.read(buffer)) != -1) {
				os.write(buffer, 0, len);
				os.flush();
			} 
		} finally {
			closeIO(gzis);
			closeIO(os);
		}
	}

	// 通过路径压缩
	public static void zip(String path, String zipPath) throws IOException {
		File file = new File(path);
		File zipFile = new File(zipPath);
		zip(file, zipFile);
	}

	// 通过文件压缩
	public static void zip(File file, File zipFile) throws IOException {
		InputStream is = new FileInputStream(file);
		OutputStream os = new FileOutputStream(zipFile);
		zip(is, os);
	}

	// 通过流进行压缩
	public static void zip(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[8192];
		int len = -1;
		GZIPOutputStream gzos = new GZIPOutputStream(os);
		try {
			while ((len = is.read(buffer)) != -1) {
				gzos.write(buffer, 0, len);
				gzos.flush();
			}
		} finally {
			closeIO(is);
			closeIO(gzos);
		}
	}

	public static void closeIO(Closeable io) throws IOException {
		if (io != null) {
			io.close();
		}
	}
}
