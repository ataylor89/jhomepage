package homepage.util;

import java.util.ArrayList;
import java.util.List;

public class ByteBuffer {

    private List<Byte> buffer;
    private ByteOrder order;

    public ByteBuffer() {
        buffer = new ArrayList<Byte>();
        order = ByteOrder.BIG_ENDIAN;
    }

    public ByteBuffer(byte[] bytes) {
        this();
        put(bytes);
    }

    public void order(ByteOrder order) {
        this.order = order;
    }

    public void put(byte b) {
        buffer.add(b);
    }

    public void put(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            buffer.add(bytes[i]);
        }
    }

    public void put(ByteBuffer buffer) {
        for (int i = 0; i < buffer.size(); i++) {
            byte b = buffer.get(i);
            this.buffer.add(b);
        }
    }

    public void putShort(short s) {
        if (order == ByteOrder.BIG_ENDIAN) {
            buffer.add((byte) ((s >> 8) & 0xFF));
            buffer.add((byte) (s & 0xFF));
        }
        else if (order == ByteOrder.LITTLE_ENDIAN) {
            buffer.add((byte) (s & 0xFF));
            buffer.add((byte) ((s >> 8) & 0xFF));
        }
    }

    public void putInt(int i) {
        if (order == ByteOrder.BIG_ENDIAN) {
            buffer.add((byte) ((i >> 24) & 0xFF));
            buffer.add((byte) ((i >> 16) & 0xFF));
            buffer.add((byte) ((i >> 8) & 0xFF));
            buffer.add((byte) (i & 0xFF));
        }
        else if (order == ByteOrder.LITTLE_ENDIAN) {
            buffer.add((byte) (i & 0xFF));
            buffer.add((byte) ((i >> 8) & 0xFF));
            buffer.add((byte) ((i >> 16) & 0xFF));
            buffer.add((byte) ((i >> 24) & 0xFF));
        }
    }

    public void putLong(long l) {
        if (order == ByteOrder.BIG_ENDIAN) {
            buffer.add((byte) ((l >> 56) & 0xFF));
            buffer.add((byte) ((l >> 48) & 0xFF));
            buffer.add((byte) ((l >> 40) & 0xFF));
            buffer.add((byte) ((l >> 32) & 0xFF));
            buffer.add((byte) ((l >> 24) & 0xFF));
            buffer.add((byte) ((l >> 16) & 0xFF));
            buffer.add((byte) ((l >> 8) & 0xFF));
            buffer.add((byte) (l & 0xFF));
        }
        else if (order == ByteOrder.LITTLE_ENDIAN) {
            buffer.add((byte) (l & 0xFF));
            buffer.add((byte) ((l >> 8) & 0xFF));
            buffer.add((byte) ((l >> 16) & 0xFF));
            buffer.add((byte) ((l >> 24) & 0xFF));
            buffer.add((byte) ((l >> 32) & 0xFF));
            buffer.add((byte) ((l >> 40) & 0xFF));
            buffer.add((byte) ((l >> 48) & 0xFF));
            buffer.add((byte) ((l >> 56) & 0xFF));
        }
    }

    public byte get(int index) {
        return buffer.get(index);
    }

    public short getShort(int index) {
        if (buffer.size() < index + 2) {
            throw new IndexOutOfBoundsException("The buffer is not large enough to retrieve a short at index " + index);
        }
        short s = 0;
        if (order == ByteOrder.BIG_ENDIAN) {
            s += buffer.get(index) << 8;
            s += buffer.get(index + 1);
        }
        else if (order == ByteOrder.LITTLE_ENDIAN) {
            s += buffer.get(index);
            s += buffer.get(index + 1) << 8;
        }
        return s;
    }

    public short getInt(int index) {
        if (buffer.size() < index + 4) {
            throw new IndexOutOfBoundsException("The buffer is not large enough to retrieve an int at index " + index);
        }
        int i = 0;
        if (order == ByteOrder.BIG_ENDIAN) {
            i += buffer.get(index) << 24;
            i += buffer.get(index + 1) << 16;
            i += buffer.get(index + 2) << 8;
            i += buffer.get(index + 3);
        }
        else if (order == ByteOrder.LITTLE_ENDIAN) {
            i += buffer.get(index);
            i += buffer.get(index + 1) << 8;
            i += buffer.get(index + 2) << 16;
            i += buffer.get(index + 3) << 24;
        }
        return i;
    }

    public short getLong(int index) {
        if (buffer.size() < index + 8) {
            throw new IndexOutOfBoundsException("The buffer is not large enough to retrieve a long at index " + index);
        }
        long l = 0;
        if (order == ByteOrder.BIG_ENDIAN) {
            l += buffer.get(index) << 56;
            l += buffer.get(index + 1) << 48;
            l += buffer.get(index + 2) << 40;
            l += buffer.get(index + 3) << 32;
            l += buffer.get(index + 4) << 24;
            l += buffer.get(index + 5) << 16;
            l += buffer.get(index + 6) << 8;
            l += buffer.get(index + 7);
        }
        else if (order == ByteOrder.LITTLE_ENDIAN) {
            l += buffer.get(index);
            l += buffer.get(index + 1) << 8;
            l += buffer.get(index + 2) << 16;
            l += buffer.get(index + 3) << 24;
            l += buffer.get(index + 4) << 32;
            l += buffer.get(index + 5) << 40;
            l += buffer.get(index + 6) << 48;
            l += buffer.get(index + 7) << 56;
        }
        return l;
    }

    public byte[] array() {
        byte[] bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
        return bytes;
    }

    public int size() {
        return buffer.size();
    }
}
