package com.luoxiang.weibo.utils;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.utils
 * className:	        ShaderUtil
 * author:	            Luoxiang
 * time:	            2016/12/16	10:43
 * desc:	            着色器工具类
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2016/12/16
 * upDateDesc:	        TODO
 */

public class ShaderUtil {

    private static final String TAG = "ShaderUtil";

    /**
     * 加载指定的着色器方法
     * @param shaderType 着色器类型
     * @param source 着色器脚本字符串源码
     * @return
     */
    public static int loadShader(int shaderType, String source) {
        //创建一个shader并记录ID
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0){
            /**
             * 创建成功进入
             * 加载着色器代码
             */
            GLES20.glShaderSource(shader , source);
            //编译着色器代码
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            //获取编译的情况
            GLES20.glGetShaderiv(shader , GLES20.GL_COMPILE_STATUS , compiled , 0);
            if (compiled[0] == 0){
                /**
                 * 如果编译失败
                 * 显示错误日志
                 * 删除该shader
                 */

                Log.e(TAG, "loadShader: can not compile shader" + shaderType + " : " );
                Log.e(TAG, "loadShader: " + GLES20.glGetShaderInfoLog(shader) );
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    /**
     * 创建着色器程序的方法
     * @param vertexSource 顶点源码
     * @param fragmentSource 片元着色器
     * @return
     */
    public static int createProgram(String vertexSource, String fragmentSource) {
        //加载顶点的着色器
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER , vertexSource);
        if (vertexShader == 0){
            return 0;
        }
        //加载片元着色器
        int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER , fragmentSource);
        if (pixelShader == 0){
            return 0;
        }

        //创建程序
        int program = GLES20.glCreateProgram();
        if (program != 0){
            /**
             * 如果程序创建成功
             * 就在程序中加入顶点着色器和片元着色器
             */

            //向程序中加入定点着色器
            GLES20.glAttachShader(program , vertexShader);
            checkGlError("glAttachShader");

            //向程序中加入片元着色器
            GLES20.glAttachShader(program , pixelShader);
            checkGlError("glAttachShader");

            //连接程序
            GLES20.glLinkProgram(program);
            //存储连接成功的状态
            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program , GLES20.GL_LINK_STATUS , linkStatus , 0);
            if (linkStatus[0] != GLES20.GL_TRUE){
                /**
                 * 创建失败进入
                 */
                Log.e(TAG, "createProgram: Could not link program");
                Log.e(TAG, "createProgram: " + GLES20.glGetProgramInfoLog(program) );
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    /**
     * 检查每一步操作是否有误
     * @param op 操作
     */
    public static void checkGlError(String op) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, op + " glGetError: " + error);
            throw new RuntimeException(op + " glGetError: " + error);
        }
    }

    /**
     * 从资产目录加载
     * @param fName 文件名字 着色器代码
     * @param resources 资源
     * @return 结果
     */
    public static String loadFromAeertsFile(String fName, Resources resources) {
        String result = null;
        try {
            InputStream           inputStream = resources.getAssets()
                                                         .open(fName);
            int                   ch          = 0;
            ByteArrayOutputStream baos        = new ByteArrayOutputStream();
            while ((ch = inputStream.read()) != -1) {
                baos.write(ch);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            inputStream.close();

            result = new String(bytes, "UTF-8");
            result = result.replaceAll("\\r\\n", "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
