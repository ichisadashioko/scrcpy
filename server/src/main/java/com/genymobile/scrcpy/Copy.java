package com.genymobile.scrcpy;

import com.genymobile.scrcpy.wrappers.ServiceManager;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public final class Copy {
    private static final ServiceManager SERVICE_MANAGER = new ServiceManager();

    public static void main(String[] args) {

        int returncode = 0;

        try {
            if (args.length < 1) {
                System.err.println("file path of data to be copied was not supplied");
                returncode = 1;
            } else {
                String text_data_filepath = args[0];
                File text_data_file = new File(text_data_filepath);
                if (!text_data_file.exists()) {
                    System.err.println(text_data_filepath + " does not exist");
                    returncode = 1;
                } else {
                    if (!text_data_file.isFile()) {
                        System.err.println(text_data_filepath + " is not a normal file");
                        returncode = 1;
                    } else {
                        int text_data_file_size = (int) text_data_file.length();
                        if (text_data_file_size < 1) {
                            System.err.println(text_data_filepath + " is empty");
                            returncode = 1;
                        } else if (text_data_file_size > (5 * 1024 * 1024)) {
                            throw new Exception(text_data_file_size + " is unreasonably large");
                        } else {
                            byte[] text_file_bytes = new byte[text_data_file_size];
                            FileInputStream file_input_stream = new FileInputStream(text_data_file);
                            file_input_stream.read(text_file_bytes);
                            file_input_stream.close();

                            String text_content =
                                    new String(text_file_bytes, StandardCharsets.UTF_8);
                            System.out.println(text_content);
                            ClipboardManager clipboardManager = SERVICE_MANAGER.getClipboardManager();
            if (clipboardManager != null) {
                clipboardManager.addPrimaryClipChangedListener(new IOnPrimaryClipChangedListener.Stub() {
                    @Override
                    public void dispatchPrimaryClipChanged() {
                        if (isSettingClipboard.get()) {
                            // This is a notification for the change we are currently applying, ignore it
                            return;
                        }
                        synchronized (Device.this) {
                            if (clipboardListener != null) {
                                String text = getClipboardText();
                                if (text != null) {
                                    clipboardListener.onClipboardTextChanged(text);
                                }
                            }
                        }
                    }
                });
            } else {
                System.err.println("No clipboard manager, copy-paste between device and computer will not work");
            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            returncode = -1;
        }

        System.exit(returncode);
    }
}
