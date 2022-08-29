package org.apache.xmlrpc.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * filter invalid XML character and replace
 * @Author cysong
 * @Date 2021/8/30 14:20
 **/
public class XmlBufferedReader extends BufferedReader {
    public XmlBufferedReader(Reader in) {
        super(in);
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        int ret = super.read(cbuf, off, len);

        for (int i = 0; i < ret; i++) {
            char current = cbuf[i];

            if (!((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF)))) {
                cbuf[i] = 'r';
            }
        }
        return ret;
    }
}
