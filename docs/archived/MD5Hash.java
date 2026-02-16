package homepage.algorithm.md5;

import homepage.algorithm.ByteBuffer;
import homepage.algorithm.ByteOrder;

public class MD5Hash {

    private int A, B, C, D;
    private int digest;
    private String hexdigest;

    /*
     * A, B, C, and D are variables that are used to create the message digest.
     *
     * The message digest is the output of the hash function.
     * A hash function accepts a message as an input, and produces a message digest as an output.
     *
     * The hexdigest variable is a little endian, hexadecimal, string representation of the message digest.
     *
     * Now, what exactly is a hash function?
     *
     * A hash function is a mathematical function that takes a variable-size input and produces a fixed-size output.
     * The MD5 hash function takes a variable-size input (a string of any length) and produces a 128-bit output.
     *
     * The SHA-256 hash function is similar. It takes a variable-size input and produces a 256-bit output.
     * The SHA-256 output is twice as long as the MD5 output.
     *
     * The MD5 and SHA-256 algorithms are useful in password security.
     *
     * Instead of storing a plaintext password in a database, you can store an MD5 password hash or a SHA-256 password hash.
     * You can salt the password before hashing it, to make sure it's random and unpredictable. This makes it even more secure.
     *
     * I think that many popular servers hash a password from a login attempt and compare it to the password hash in their database for that user.
     *
     * They can retrieve the salt value for that user, salt the password from the login attempt, hash it,
     * and then compare it to the password hash in their database for that user.
     *
     * The hashing takes place on the server-side, not on the client-side.
     * The password is sent from the client to the server over an encrypted connection, so that it's secure while in transit.
     *
     * I find this subject very interesting. I wanted to talk about it at length.
     */
    public MD5Hash(int A, int B, int C, int D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.digest = A + (B << 32) + (C << 64) + (D << 96);
        this.hexdigest = hexstring(digest);
    }

    /*
     * This method returns a little endian, hexadecimal, string representation of the digest.
     *
     * The term "little endian" means little end first.
     *
     * If we were to add the number 0x1234 to a byte buffer that has a little endian order,
     * then the result would be [0x34, 0x12], because the little end (0x34) goes in first.
     *
     * In other words, we would see that buffer[0] == 0x34 and buffer[1] == 0x12,
     * where buffer is the name of our byte buffer.
     *
     * Why is 0x34 considered to be the little end in the above example?
     * It's because 0x1200 > 0x0034, since the 0x12 is weighted as 1 * 16^3 + 2 * 16^2 = 4608.
     *
     * The little end is the byte that has the smallest numeric value, which in this case is 0x34.
     */
    private String hexstring(int digest) {
        ByteBuffer buffer = ByteBuffer();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
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
