package com.example.commonutils;

/**
 * Created by lw on 18-1-11.
 */

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LogCollectUtils {
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HHMMSS = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());

    public static final String COMMAND_LOGCAT = "/system/bin/logcat -b system -b main ";
    public static final String COMMAND_SU = "su ";
    public static final String COMMAND_CAT = "/system/bin/cat ";
    public static final String COMMAND_LS = "/system/bin/ls ";
    public static final String COMMAND_ECHO = "/system/bin/echo ";
    public static final String COMMAND_BIN_SH = "/system/bin/sh ";
    public static final String COMMAND_DUMPSYS = "/system/bin/dumpsys ";
    public static final String COMMAND_DMSG = "/system/bin/dmesg ";

    public static final String LOGCAT_GET_NOTE = "-d ";
    public static final String LOGCAT_CLEAR_NOTE = "-c ";
    public static final String LOGCAT_GET_SIZE_NOTE = "-g ";
    public static final String LOGCAT_FORMAT_SET = "-v ";
    public static final String LOGCAT_OUTPUT_TO_FILE = "-f ";
    public static final String LOGCAT_FILTER_BY = "-s ";

    public static final String TAG_FORMAT_BRIEF = "brief ";
    public static final String TAG_FORMAT_PROCESS = "process ";
    public static final String TAG_FORMAT_TAG = "tag ";
    public static final String TAg_FORMAT_THREAD = "thread ";
    public static final String TAG_FORMAT_RAW = "raw ";
    public static final String TAG_FORMAT_TIME = "time ";
    public static final String TAG_FORMAT_THREADTIME = "threadtime ";
    public static final String TAG_FORMAT_LONG = " long";

    //tag:W Log.w(tag, xxxx); W/System.err
    public static final String TAG_FILTER_SYSTEM_ERR = "System.err:* ";
    public static final String TAG_FILTER_AVOS_CLOUD = "===AVOS*:* ";

    public static final String DIR_SDCARD = "/mnt/sdcard/";
    public static final String DIR_PROC = "/proc/";
    public static final String VERSION = "version";
    public static final String LAST_KMSG = "last_kmsg";

    public static final String POWER = "power";
    public static final String BATTERY = "battery";
    public static final String BATTERY_STATS = "batterystats";
    public static final String ALARM = "alarm";
    public static final String ANR_TRACES_PATH = "/data/anr/traces.txt";

    public static final String SEPARATOR_NEW_LINE = "\r\n";
    public static final String SEPARATOR_BLANK = " ";
    public static final String SEPARATOR_STAR = "\r\n***********************************\r\n\r\n";

    public static final String LOGCAT_FILTER_ARGS = TAG_FILTER_SYSTEM_ERR + TAG_FILTER_AVOS_CLOUD;

    public static final String PROGRAM_EXEC_LOGCAT_GET_NOTE = COMMAND_LOGCAT + LOGCAT_GET_NOTE
            + LOGCAT_FORMAT_SET + TAG_FORMAT_TAG
            + LOGCAT_FORMAT_SET + TAG_FORMAT_TIME;
    public static final String PROGRAM_EXEC_CAT_LINUX_VERSION = COMMAND_CAT + DIR_PROC + VERSION;
    public static final String PROGRAM_EXEC_CAT_LINUX_LAST_KMSG = COMMAND_CAT + DIR_PROC + LAST_KMSG;
    public static final String PROGRAM_EXEC_CAT_LINUX_DMSG = COMMAND_DMSG;
    public static final String PROGRAM_EXEC_LS_SDCARD = COMMAND_LS + DIR_SDCARD;
    public static final String PROGRAM_EXEC_LOGCAT_CLEAR_NOTE = COMMAND_LOGCAT + LOGCAT_CLEAR_NOTE;

    public static final String PROGRAM_EXEC_DUMPSYS_ALL = COMMAND_DUMPSYS;
    public static final String PROGRAM_EXEC_DUMPSYS_POWER = COMMAND_DUMPSYS + POWER;
    public static final String PROGRAM_EXEC_DUMPSYS_BATTERY = COMMAND_DUMPSYS + BATTERY;
    public static final String PROGRAM_EXEC_DUMPSYS_BATTERY_STATS = COMMAND_DUMPSYS + BATTERY_STATS;
    public static final String PROGRAM_EXEC_DUMPSYS_ALARM = COMMAND_DUMPSYS + ALARM;

    public static final String PROGRAM_EXEC_WAKEUP_REASON_SUSPEND_HISTORY = COMMAND_CAT + "/sys/kernel/wakeup_reasons/suspend_history";
    public static final String PROGRAM_EXEC_CAT_ANR = COMMAND_CAT + ANR_TRACES_PATH;


    public static final String ENCODING_TYPE = "utf-8";
    public static final String OUTPUT_FILE_ZIP_PREFIX = "feedback_";
    public static final String OUTPUT_FILE_LOGCAT_PREFIX = "logcat_";
    public static final String OUTPUT_FILE_KERNEL_PREFIX = "kernel_";
    public static final String OUTPUT_FILE_DUMPSYS_PREFIX = "dumpsys_";
    public static final String OUTPUT_FILE_DUMPSYS_SPECIFY_PREFIX = "dumpsys_specify_";
    public static final String OUTPUT_FILE_WAKEUP_REASONS_PREFIX = "wakeup_reasons_";
    public static final String OUTPUT_FILE_KMSG_PREFIX = "kmsg_";
    public static final String OUTPUT_FILE_ANR_PREFIX = "anr_";
    public static final String OUTPUT_FILE_TXT_EXTENSION = ".txt";
    public static final String OUTPUT_FILE_ZIP_EXTENSION = ".zip";

    public static final String[] COMMAND_LOGCAT_SET = new String[]{
            PROGRAM_EXEC_LOGCAT_GET_NOTE,
            PROGRAM_EXEC_CAT_LINUX_VERSION,
    };

    public static final String[] COMMAND_KERNEL_SET = new String[]{
            PROGRAM_EXEC_CAT_LINUX_VERSION,
            PROGRAM_EXEC_CAT_LINUX_DMSG
    };

    public static final String[] COMMAND_CLEAR_NOTE_SET = new String[]{
            PROGRAM_EXEC_LOGCAT_CLEAR_NOTE,
    };

    public static final String[] COMMAND_DUMPSYS_SET = new String[]{
            PROGRAM_EXEC_DUMPSYS_ALL
    };

    public static final String[] COMMAND_DUMPSYS_SPECIFY_SET = new String[]{
            PROGRAM_EXEC_DUMPSYS_POWER,
            PROGRAM_EXEC_DUMPSYS_BATTERY,
            PROGRAM_EXEC_DUMPSYS_BATTERY_STATS,
            PROGRAM_EXEC_DUMPSYS_ALARM
    };

    public static final String[] COMMAND_WAKEUP_REASON_SET = new String[]{
            PROGRAM_EXEC_WAKEUP_REASON_SUSPEND_HISTORY
    };

    public static final String[] COMMAND_KMSG_SET = new String[]{
            PROGRAM_EXEC_CAT_LINUX_LAST_KMSG
    };

    public static final String[] COMMAND_ANR_SET = new String[]{
            PROGRAM_EXEC_CAT_ANR
    };

    private static String getFileNameBasedOnDate(String prefix, String suffix) {
        return prefix + DATE_FORMAT_YYYY_MM_DD_HHMMSS.format(new Date()) + suffix;
    }

    public static File generateExceptionFile(Context context, String[] commandSet, String filePrefix) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String command : commandSet) {
            ShellUtils.CommandResult commandResult = ShellUtils.execCommand(command, false, true);
            if (commandResult.successMsg != null) {
                stringBuilder.append(commandResult.successMsg);
            }
            stringBuilder.append(SEPARATOR_STAR);
        }
        File logcatFile = new File(context.getFilesDir(), getFileNameBasedOnDate(filePrefix, OUTPUT_FILE_TXT_EXTENSION));
        FileOutputStream out = new FileOutputStream(logcatFile);
        out.write(stringBuilder.toString().getBytes(ENCODING_TYPE));
        out.flush();
        out.close();
        Log.i(filePrefix + "File success:" + logcatFile.getName(), "" + logcatFile.length());
        return logcatFile;
    }

    // must in work workThread
    public static File generateFeedBackFile(final Context context, List<String> additionalPathList) {
        File zipFile = null;
        List<File> compressFilesList = null;
        try {
            compressFilesList = buildRegularFeedbackFileList(context);
            if (additionalPathList != null && additionalPathList.size() > 0) {
                compressFilesList.addAll(buildAdditionalFeedbackFileList(context, additionalPathList));
            }
            String fileName = getFileNameBasedOnDate(OUTPUT_FILE_ZIP_PREFIX, OUTPUT_FILE_ZIP_EXTENSION);
            zipFile = new File(context.getFilesDir(), fileName);
            boolean isSuccess = ZipUtils.compress(compressFilesList, zipFile);
            if (!isSuccess) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deleteFiles(compressFilesList);
        }
        return zipFile;
    }

    private static List<File> buildAdditionalFeedbackFileList(Context context, List<String> pathList) {
        List<File> resultList = new ArrayList<>();
        for (String path : pathList) {
            File file = new File(path);
            try {
                for (File subFile : getAllSubFile(file)) {
                    FileUtils.copyToDestinationDirectory(subFile, context.getFilesDir().getPath(),
                            File.separator + subFile.getName());
                    resultList.add(new File(context.getFilesDir() + File.separator + subFile.getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private static List<File> getAllSubFile(File file) {
        List<File> resultList = new ArrayList<>();
        filePermissionDetect(file, 200, 2);
        File[] folderSubItems = file.listFiles();
        if (file.isDirectory() && folderSubItems != null) {
            for (File subFile : folderSubItems) {
                if (subFile.isDirectory()) {
                    resultList.addAll(getAllSubFile(subFile));
                } else {
                    resultList.add(subFile);
                }
            }
        } else if (file.isFile()) {
            resultList.add(file);
        }
        return resultList;
    }

    /**
     * for some intent trigger permission scenes, we have to wait permission alter finish.
     * must in work workThread
     */
    private static void filePermissionDetect(File file, int waitTime, int retryCount) {
        while ((!(file.canRead() && file.canExecute()) && retryCount > 0)) {
            retryCount--;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<File> buildRegularFeedbackFileList(Context context) throws IOException {
        List<File> resultList = new ArrayList<>();
        //logcat files
        resultList.add(generateExceptionFile(context, COMMAND_LOGCAT_SET, OUTPUT_FILE_LOGCAT_PREFIX));
        //kernel files
        resultList.add(generateExceptionFile(context, COMMAND_KERNEL_SET, OUTPUT_FILE_KERNEL_PREFIX));
        //dumpsys files
        resultList.add(generateExceptionFile(context, COMMAND_DUMPSYS_SET, OUTPUT_FILE_DUMPSYS_PREFIX));
        //dumpsysSpecify file
        resultList.add(generateExceptionFile(context, COMMAND_DUMPSYS_SPECIFY_SET, OUTPUT_FILE_DUMPSYS_SPECIFY_PREFIX));
        //wakeupReasons file
        resultList.add(generateExceptionFile(context, COMMAND_WAKEUP_REASON_SET, OUTPUT_FILE_WAKEUP_REASONS_PREFIX));
        //kmsg files
        resultList.add(generateExceptionFile(context, COMMAND_KMSG_SET, OUTPUT_FILE_KMSG_PREFIX));
        //anr files
        resultList.add(generateExceptionFile(context, COMMAND_ANR_SET, OUTPUT_FILE_ANR_PREFIX));
        return resultList;
    }

    private static void deleteFiles(List<File> files) {
        if (files == null || files.size() <= 0) {
            return;
        }
        for (File file : files) {
            if (file != null && file.exists()) {
                FileUtils.deleteQuietly(file);
            }
        }
    }
}
