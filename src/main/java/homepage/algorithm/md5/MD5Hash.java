/*
package homepage.algorithm.md5;

import homepage.util.ByteBuffer;
import homepage.util.ByteOrder;

public class MD5Hash {

    private int A, B, C, D;
    private String hexdigest;

    public MD5Hash(int A, int B, int C, int D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.hexdigest = hexstring(A, B, C, D);
    }

    private String hexstring(int A, int B, int C, int D) {
        ByteBuffer buffer = new ByteBuffer(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(A);
        buffer.putInt(B);
        buffer.putInt(C);
        buffer.putInt(D);
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

    public void setHexdigest(String hexdigest) {
        this.hexdigest = hexdigest;
    }

    public String getHexdigest() {
        return hexdigest;
    }

}
*/
