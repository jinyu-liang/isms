package com.is.pms.msginteract.io.policy;

import java.io.InputStream;

public class PolicyFileLoader
{
    public static final String POLICY_FILE_REQUEST_HEADER = "<policy-file-request/>";

    public static String       POLICY_FILE_PATH           = "policy/policyfile.xml";

    private static String      __prolicyFileContent       = "";

    private static InputStream file;

    public static String getPolicyFileContent()
    {
        return __prolicyFileContent + "\0";
    }

    public static void loadPolicyFile() throws Exception
    {
        if (file == null)
        {
            //            file = new File(POLICY_FILE_PATH);
            file = Thread.currentThread().getContextClassLoader().getResourceAsStream(POLICY_FILE_PATH);
        }
        byte[] bytes = new byte[2048];
        //        new FileInputStream(file).read(bytes);
        file.read(bytes);
        __prolicyFileContent = new String(bytes).trim();
    }

    public static boolean hasPolicyFile()
    {
        if (file == null)
        {

            //            file = new File(POLICY_FILE_PATH);
            file = Thread.currentThread().getContextClassLoader().getResourceAsStream(POLICY_FILE_PATH);
        }
        //        return file.exists();
        return file != null;
    }
}
