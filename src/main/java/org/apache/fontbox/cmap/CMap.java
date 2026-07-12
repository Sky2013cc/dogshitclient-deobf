package org.apache.fontbox.cmap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/fontbox/cmap/CMap.class */
public class CMap {
    private static final Log LOG = LogFactory.getLog(CMap.class);
    private int maxCodeLength;
    private static final String SPACE = " ";
    private int wmode = 0;
    private String cmapName = null;
    private String cmapVersion = null;
    private int cmapType = -1;
    private String registry = null;
    private String ordering = null;
    private int supplement = 0;
    private int minCodeLength = 4;
    private int minCidLength = 4;
    private int maxCidLength = 0;
    private final List<CodespaceRange> codespaceRanges = new ArrayList();
    private final Map<Integer, String> charToUnicodeOneByte = new HashMap();
    private final Map<Integer, String> charToUnicodeTwoBytes = new HashMap();
    private final Map<Integer, Map<Integer, Integer>> codeToCid = new HashMap();
    private final List<CIDRange> codeToCidRanges = new ArrayList();
    private final Map<String, byte[]> unicodeToByteCodes = new HashMap();
    private int spaceMapping = -1;

    public boolean hasCIDMappings() {
        return (this.codeToCid.isEmpty() && this.codeToCidRanges.isEmpty()) ? false : true;
    }

    public boolean hasUnicodeMappings() {
        return (this.charToUnicodeOneByte.isEmpty() && this.charToUnicodeTwoBytes.isEmpty()) ? false : true;
    }

    public String toUnicode(int code) {
        String unicode = code < 256 ? toUnicode(code, 1) : null;
        if (unicode == null) {
            unicode = toUnicode(code, 2);
        }
        return unicode;
    }

    public String toUnicode(int code, int length) {
        if (length == 1) {
            return this.charToUnicodeOneByte.get(Integer.valueOf(code));
        }
        if (length == 2) {
            return this.charToUnicodeTwoBytes.get(Integer.valueOf(code));
        }
        LOG.warn("Mappings with more than 2 bytes aren't supported");
        return null;
    }

    public String toUnicode(byte[] code) {
        return toUnicode(toInt(code), code.length);
    }

    public int readCode(InputStream in) throws IOException {
        byte[] bytes = new byte[this.maxCodeLength];
        in.read(bytes, 0, this.minCodeLength);
        in.mark(this.maxCodeLength);
        for (int i = this.minCodeLength - 1; i < this.maxCodeLength; i++) {
            int byteCount = i + 1;
            if (this.codespaceRanges.stream().anyMatch(r -> {
                return r.isFullMatch(bytes, byteCount);
            })) {
                return toInt(bytes, byteCount);
            }
            if (byteCount < this.maxCodeLength) {
                bytes[byteCount] = (byte) in.read();
            }
        }
        if (LOG.isWarnEnabled()) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < this.maxCodeLength; i2++) {
                sb.append(String.format("0x%02X (%04o) ", Byte.valueOf(bytes[i2]), Byte.valueOf(bytes[i2])));
            }
            LOG.warn("Invalid character code sequence " + ((Object) sb) + "in CMap " + this.cmapName);
        }
        if (in.markSupported()) {
            in.reset();
        } else {
            LOG.warn("mark() and reset() not supported, " + (this.maxCodeLength - 1) + " bytes have been skipped");
        }
        return toInt(bytes, this.minCodeLength);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toInt(byte[] data) {
        return toInt(data, data.length);
    }

    private static int toInt(byte[] data, int dataLen) {
        int code = 0;
        for (int i = 0; i < dataLen; i++) {
            code = (code << 8) | (data[i] & 255);
        }
        return code;
    }

    public int toCID(byte[] code) {
        if (!hasCIDMappings() || code.length < this.minCidLength || code.length > this.maxCidLength) {
            return 0;
        }
        Integer cid = null;
        if (this.codeToCid.containsKey(Integer.valueOf(code.length))) {
            cid = this.codeToCid.get(Integer.valueOf(code.length)).get(Integer.valueOf(toInt(code)));
        }
        if (cid == null) {
            cid = Integer.valueOf(toCIDFromRanges(code));
        }
        return cid.intValue();
    }

    public int toCID(int code) {
        if (!hasCIDMappings()) {
            return 0;
        }
        int cid = 0;
        int length = this.minCidLength;
        while (cid == 0 && length <= this.maxCidLength) {
            int i = length;
            length++;
            cid = toCID(code, i);
        }
        return cid;
    }

    public int toCID(int code, int length) {
        if (!hasCIDMappings() || length < this.minCidLength || length > this.maxCidLength) {
            return 0;
        }
        Integer cid = null;
        if (this.codeToCid.containsKey(Integer.valueOf(length))) {
            cid = this.codeToCid.get(Integer.valueOf(length)).get(Integer.valueOf(code));
        }
        return cid != null ? cid.intValue() : toCIDFromRanges(code, length);
    }

    private int toCIDFromRanges(int code, int length) {
        for (CIDRange range : this.codeToCidRanges) {
            int ch = range.map(code, length);
            if (ch != -1) {
                return ch;
            }
        }
        return 0;
    }

    private int toCIDFromRanges(byte[] code) {
        for (CIDRange range : this.codeToCidRanges) {
            int ch = range.map(code);
            if (ch != -1) {
                return ch;
            }
        }
        return 0;
    }

    private int getCodeFromArray(byte[] data, int offset, int length) {
        int code = 0;
        for (int i = 0; i < length; i++) {
            code = (code << 8) | ((data[offset + i] + 256) % 256);
        }
        return code;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void addCharMapping(byte[] codes, String unicode) {
        this.unicodeToByteCodes.put(unicode, codes.clone());
        int code = getCodeFromArray(codes, 0, codes.length);
        if (codes.length == 1) {
            this.charToUnicodeOneByte.put(Integer.valueOf(code), unicode);
        } else if (codes.length == 2) {
            this.charToUnicodeTwoBytes.put(Integer.valueOf(code), unicode);
        } else {
            LOG.warn("Mappings with more than 2 bytes aren't supported yet");
        }
        if (SPACE.equals(unicode)) {
            this.spaceMapping = code;
        }
    }

    public byte[] getCodesFromUnicode(String unicode) {
        return this.unicodeToByteCodes.get(unicode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCIDMapping(byte[] code, int cid) {
        Map<Integer, Integer> codeToCidMap = this.codeToCid.get(Integer.valueOf(code.length));
        if (codeToCidMap == null) {
            codeToCidMap = new HashMap();
            this.codeToCid.put(Integer.valueOf(code.length), codeToCidMap);
            this.minCidLength = Math.min(this.minCidLength, code.length);
            this.maxCidLength = Math.max(this.maxCidLength, code.length);
        }
        codeToCidMap.put(Integer.valueOf(toInt(code)), Integer.valueOf(cid));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCIDRange(byte[] from, byte[] to, int cid) {
        addCIDRange(this.codeToCidRanges, toInt(from), toInt(to), cid, from.length);
    }

    private void addCIDRange(List<CIDRange> cidRanges, int from, int to, int cid, int length) {
        CIDRange lastRange = null;
        if (!cidRanges.isEmpty()) {
            lastRange = cidRanges.get(cidRanges.size() - 1);
        }
        if (lastRange == null || !lastRange.extend(from, to, cid, length)) {
            cidRanges.add(new CIDRange(from, to, cid, length));
            this.minCidLength = Math.min(this.minCidLength, length);
            this.maxCidLength = Math.max(this.maxCidLength, length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCodespaceRange(CodespaceRange range) {
        this.codespaceRanges.add(range);
        this.maxCodeLength = Math.max(this.maxCodeLength, range.getCodeLength());
        this.minCodeLength = Math.min(this.minCodeLength, range.getCodeLength());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void useCmap(CMap cmap) {
        cmap.codespaceRanges.forEach(this::addCodespaceRange);
        this.charToUnicodeOneByte.putAll(cmap.charToUnicodeOneByte);
        this.charToUnicodeTwoBytes.putAll(cmap.charToUnicodeTwoBytes);
        cmap.charToUnicodeOneByte.forEach((k, v) -> {
            this.unicodeToByteCodes.put(v, new byte[]{(byte) (k.intValue() % 255)});
        });
        cmap.charToUnicodeTwoBytes.forEach((k2, v2) -> {
            this.unicodeToByteCodes.put(v2, new byte[]{(byte) ((k2.intValue() >>> 8) & 255), (byte) (k2.intValue() & 255)});
        });
        cmap.codeToCid.forEach((key, value) -> {
            Map<Integer, Integer> existingMapping = this.codeToCid.putIfAbsent(key, value);
            if (existingMapping != null) {
                existingMapping.putAll(value);
            }
        });
        this.codeToCidRanges.addAll(cmap.codeToCidRanges);
        this.maxCodeLength = Math.max(this.maxCodeLength, cmap.maxCodeLength);
        this.minCodeLength = Math.min(this.minCodeLength, cmap.minCodeLength);
        this.maxCidLength = Math.max(this.maxCidLength, cmap.maxCidLength);
        this.minCidLength = Math.min(this.minCidLength, cmap.minCidLength);
    }

    public int getWMode() {
        return this.wmode;
    }

    public void setWMode(int newWMode) {
        this.wmode = newWMode;
    }

    public String getName() {
        return this.cmapName;
    }

    public void setName(String name) {
        this.cmapName = name;
    }

    public String getVersion() {
        return this.cmapVersion;
    }

    public void setVersion(String version) {
        this.cmapVersion = version;
    }

    public int getType() {
        return this.cmapType;
    }

    public void setType(int type) {
        this.cmapType = type;
    }

    public String getRegistry() {
        return this.registry;
    }

    public void setRegistry(String newRegistry) {
        this.registry = newRegistry;
    }

    public String getOrdering() {
        return this.ordering;
    }

    public void setOrdering(String newOrdering) {
        this.ordering = newOrdering;
    }

    public int getSupplement() {
        return this.supplement;
    }

    public void setSupplement(int newSupplement) {
        this.supplement = newSupplement;
    }

    public int getSpaceMapping() {
        return this.spaceMapping;
    }

    public String toString() {
        return this.cmapName;
    }
}
