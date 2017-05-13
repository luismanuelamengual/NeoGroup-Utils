package org.neogroup.util.encoding;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP compression utilities
 */
public class GZIPCompression {

    /**
     * Compresses an array of bytes using gzip compression
     * @param bytes array of bytes to compress
     * @return bytes compressed
     * @throws IOException
     */
    public static byte[] compress(final byte[] bytes) throws IOException {

        try (ByteArrayOutputStream bout = new ByteArrayOutputStream(); GZIPOutputStream gzipper = new GZIPOutputStream(bout)) {
            gzipper.write(bytes, 0, bytes.length);
            gzipper.close();
            return bout.toByteArray();
        }
    }

    /**
     * Decompress an array of bytes compressed using gzip
     * @param compressed compressed array of bytes
     * @return bytes decompressed
     * @throws IOException
     */
    public static byte[] decompress(final byte[] compressed) throws IOException {

        try (ByteArrayInputStream bin = new ByteArrayInputStream(compressed); GZIPInputStream gzipper = new GZIPInputStream(bin)) {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len;
            while ((len = gzipper.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            gzipper.close();
            out.close();
            return out.toByteArray();
        }
    }
}