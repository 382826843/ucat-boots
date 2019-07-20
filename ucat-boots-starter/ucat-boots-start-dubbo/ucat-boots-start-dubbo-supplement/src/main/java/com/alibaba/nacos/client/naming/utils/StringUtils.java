//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.alibaba.nacos.client.naming.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Locale;

public class StringUtils {
    public static final String EMPTY = "";

    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Object[] objects = collection.toArray();

            for(int i = 0; i < collection.size() - 1; ++i) {
                stringBuilder.append(objects[i].toString()).append(separator);
            }

            if (collection.size() > 0) {
                stringBuilder.append(objects[collection.size() - 1]);
            }

            return stringBuilder.toString();
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String escapeJavaScript(String str) {
        return escapeJavaStyleString(str, true, true);
    }

    private static String escapeJavaStyleString(String str, boolean escapeSingleQuotes, boolean escapeForwardSlash) {
        if (str == null) {
            return null;
        } else {
            try {
                StringWriter writer = new StringWriter(str.length() * 2);
                escapeJavaStyleString(writer, str, escapeSingleQuotes, escapeForwardSlash);
                return writer.toString();
            } catch (IOException var4) {
                return null;
            }
        }
    }

    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }

    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote, boolean escapeForwardSlash) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int sz = str.length();

            for(int i = 0; i < sz; ++i) {
                char ch = str.charAt(i);
                if (ch > 4095) {
                    out.write("\\u" + hex(ch));
                } else if (ch > 255) {
                    out.write("\\u0" + hex(ch));
                } else if (ch > 127) {
                    out.write("\\u00" + hex(ch));
                } else if (ch < ' ') {
                    switch(ch) {
                    case '\b':
                        out.write(92);
                        out.write(98);
                        break;
                    case '\t':
                        out.write(92);
                        out.write(116);
                        break;
                    case '\n':
                        out.write(92);
                        out.write(110);
                        break;
                    case '\u000b':
                    default:
                        if (ch > 15) {
                            out.write("\\u00" + hex(ch));
                        } else {
                            out.write("\\u000" + hex(ch));
                        }
                        break;
                    case '\f':
                        out.write(92);
                        out.write(102);
                        break;
                    case '\r':
                        out.write(92);
                        out.write(114);
                    }
                } else {
                    switch(ch) {
                    case '"':
                        out.write(92);
                        out.write(34);
                        break;
                    case '\'':
                        if (escapeSingleQuote) {
                            out.write(92);
                        }

                        out.write(39);
                        break;
                    case '/':
                        if (escapeForwardSlash) {
                            out.write(92);
                        }

                        out.write(47);
                        break;
                    case '\\':
                        out.write(92);
                        out.write(92);
                        break;
                    default:
                        out.write(ch);
                    }
                }
            }

        }
    }
}
