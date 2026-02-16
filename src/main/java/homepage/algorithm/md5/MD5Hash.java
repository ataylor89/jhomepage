package homepage.algorithm.md5;

import homepage.algorithm.ByteBuffer;
import homepage.algorithm.ByteOrder;

public class MD5Hash {

    private int A, B, C, D;
    private int digest;
    private String hexdigest;

    public MD5Hash(int A, int B, int C, int D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.digest = A + (B << 32) + (C << 64) + (D << 96);
        this.hexdigest = hexstring(digest);
    }

    private String hexstring(int digest) {
        ByteBuffer buffer = ByteBuffer(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(digest);
        byte[] bytes = buffer.array();
        StringBuilder hexstring = new StringBuilder();
        for (byte b : bytes) {
            hexstring.append(String.format("%02X", b));
        }
        return hexstring.toString();
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getA() {
        return A;
    }

    public void setB(int B) {
        this.B = B;
    }

    public int getB() {
        return B;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getC() {
        return C;
    }

    public void setD(int D) {
        this.D = D;
    }

    public int getD() {
        return D;
    }

    public void setDigest(int digest) {
        this.digest = digest;
    }

    public int getDigest() {
        return digest;
    }

    public void setHexdigest(String hexdigest) {
        this.hexdigest = hexdigest;
    }

    public String getHexdigest() {
        return hexdigest;
    }

}
