package com.luoxiang.weibo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.utils
 * className:	        CpuUtil
 * author:	            Luoxiang
 * time:	            2017/3/1	9:38
 * desc:	            cpu工具类
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2017/3/1
 * upDateDesc:	        TODO
 */

public class CpuUtil {
    //CPU个数
    private int getNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if(Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch(Exception e) {
            //Print exception
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }

    private final static String kCpuInfoMaxFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    //获取CPU最大频率：
    public static int getMaxCpuFreq()
    {
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(kCpuInfoMaxFreqFilePath);
            br = new BufferedReader(fr);
            String text = br.readLine();
            result = Integer.parseInt(text.trim());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fr != null)
                try
                {
                    fr.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (br != null)
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return result;
    }

    private final static String kCpuInfoMinFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";

    /* 获取CPU最小频率（单位KHZ） */
    public static int getMinCpuFreq()
    {
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(kCpuInfoMinFreqFilePath);
            br = new BufferedReader(fr);
            String text = br.readLine();
            result = Integer.parseInt(text.trim());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fr != null)
                try
                {
                    fr.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (br != null)
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return result;
    }

    private final static String kCpuInfoCurFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";

    /* 实时获取CPU当前频率（单位KHZ） */
    public static int getCurCpuFreq()
    {
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(kCpuInfoCurFreqFilePath);
            br = new BufferedReader(fr);
            String text = br.readLine();
            result = Integer.parseInt(text.trim());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fr != null)
                try
                {
                    fr.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (br != null)
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return result;
    }

}
