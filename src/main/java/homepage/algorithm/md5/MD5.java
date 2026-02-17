/*
package homepage.algorithm.md5;

import homepage.util.ByteBuffer;
import homepage.util.ByteOrder;
import java.math.BigDecimal;
import static java.lang.Math.abs;
import static java.lang.Math.sin;
import org.springframework.stereotype.Component;

@Component
public class MD5 {

    private long[] table;

    public MD5() {
        table = new long[64];
        populateTable();
    }

    private void populateTable() {
        BigDecimal constant = new BigDecimal("4294967296");
        for (int i = 0; i < table.length; i++) {
            BigDecimal factor = new BigDecimal(abs(sin(i+1)));
            BigDecimal result = constant.multiply(factor).remainder(constant);
            table[i] = result.longValue();
            // System.out.printf("table[%d] = %d\n", i, table[i]);
        }
    }

    private ByteBuffer pad(String message) {
        ByteBuffer buffer = new ByteBuffer(message.getBytes(), ByteOrder.LITTLE_ENDIAN);
        buffer.put((byte) 0x80);
        while (buffer.size() % 64 != 56) {
            buffer.put((byte) 0);
        }
        long bitLength = message.getBytes().length * 8L;
        buffer.putLong(bitLength);
        return buffer;
    }   

    private long F(long x, long y, long z) {
        long result = (x & y) | (~x & z);
        return result & 0xFFFFFFFFL;
    }

    private long G(long x, long y, long z) {
        long result = (x & z) | (y & ~z);
        return result & 0xFFFFFFFFL;
    }

    private long H(long x, long y, long z) {
        long result = x ^ y ^ z;
        return result & 0xFFFFFFFFL;
    }

    private long I(long x, long y, long z) {
        long result = y ^ (x | ~z);
        return result & 0xFFFFFFFFL;
    }

    private long rotateLeft(long x, int n) {
        x &= 0xFFFFFFFFL;
        return (x << n | x >> (32 - n)) & 0xFFFFFFFFL;
    }

    private long op(long a, long b, long c, long d, int k, int s, int i, Operation f, long[] x) {
        a = b + rotateLeft(a + f.execute(b, c, d) + x[k] + table[i-1], s);
        return a & 0xFFFFFFFFL;
    }

    public MD5Hash md5(String message) {
        ByteBuffer buffer = pad(message);
        long A = 0x67452301;
        long B = 0xefcdab89;
        long C = 0x98badcfe;
        long D = 0x10325476;
        Operation F = this::F;
        Operation G = this::G;
        Operation H = this::H;
        Operation I = this::I;
        for (int i = 0; i < buffer.size() / 64; i++) {
            long[] X = new long[16];
            for (int j = 0; j < 16; j++) {
                int offset = 4 * (i * 16 + j);
                int word = buffer.getInt(offset);
                X[j] = Integer.toUnsignedLong(word);                
            }
            long AA = A;
            long BB = B;
            long CC = C;
            long DD = D;
            // Round 1
            A = op(A, B, C, D, 0, 7, 1, F, X);
            D = op(D, A, B, C, 1, 12, 2, F, X);
            C = op(C, D, A, B, 2, 17, 3, F, X);
            B = op(B, C, D, A, 3, 22, 4, F, X);
            A = op(A, B, C, D, 4, 7, 5, F, X);
            D = op(D, A, B, C, 5, 12, 6, F, X);
            C = op(C, D, A, B, 6, 17, 7, F, X);
            B = op(B, C, D, A, 7, 22, 8, F, X);
            A = op(A, B, C, D, 8, 7, 9, F, X);
            D = op(D, A, B, C, 9, 12, 10, F, X);
            C = op(C, D, A, B, 10, 17, 11, F, X);
            B = op(B, C, D, A, 11, 22, 12, F, X);
            A = op(A, B, C, D, 12, 7, 13, F, X);
            D = op(D, A, B, C, 13, 12, 14, F, X);
            C = op(C, D, A, B, 14, 17, 15, F, X);
            B = op(B, C, D, A, 15, 22, 16, F, X);
            // Round 2
            A = op(A, B, C, D, 1, 5, 17, G, X);
            D = op(D, A, B, C, 6, 9, 18, G, X);
            C = op(C, D, A, B, 11, 14, 19, G, X);
            B = op(B, C, D, A, 0, 20, 20, G, X);
            A = op(A, B, C, D, 5, 5, 21, G, X);
            D = op(D, A, B, C, 10, 9, 22, G, X);
            C = op(C, D, A, B, 15, 14, 23, G, X);
            B = op(B, C, D, A, 4, 20, 24, G, X);
            A = op(A, B, C, D, 9, 5, 25, G, X);
            D = op(D, A, B, C, 14, 9, 26, G, X);
            C = op(C, D, A, B, 3, 14, 27, G, X);
            B = op(B, C, D, A, 8, 20, 28, G, X);
            A = op(A, B, C, D, 13, 5, 29, G, X);
            D = op(D, A, B, C, 2, 9, 30, G, X);
            C = op(C, D, A, B, 7, 14, 31, G, X);
            B = op(B, C, D, A, 12, 20, 32, G, X);
            // Round 3
            A = op(A, B, C, D, 5, 4, 33, H, X);
            D = op(D, A, B, C, 8, 11, 34, H, X);
            C = op(C, D, A, B, 11, 16, 35, H, X);
            B = op(B, C, D, A, 14, 23, 36, H, X);
            A = op(A, B, C, D, 1, 4, 37, H, X);
            D = op(D, A, B, C, 4, 11, 38, H, X);
            C = op(C, D, A, B, 7, 16, 39, H, X);
            B = op(B, C, D, A, 10, 23, 40, H, X);
            A = op(A, B, C, D, 13, 4, 41, H, X);
            D = op(D, A, B, C, 0, 11, 42, H, X);
            C = op(C, D, A, B, 3, 16, 43, H, X);
            B = op(B, C, D, A, 6, 23, 44, H, X);
            A = op(A, B, C, D, 9, 4, 45, H, X);
            D = op(D, A, B, C, 12, 11, 46, H, X);
            C = op(C, D, A, B, 15, 16, 47, H, X);
            B = op(B, C, D, A, 2, 23, 48, H, X);
            // Round 4
            A = op(A, B, C, D, 0, 6, 49, I, X);
            D = op(D, A, B, C, 7, 10, 50, I, X);
            C = op(C, D, A, B, 14, 15, 51, I, X);
            B = op(B, C, D, A, 5, 21, 52, I, X);
            A = op(A, B, C, D, 12, 6, 53, I, X);
            D = op(D, A, B, C, 3, 10, 54, I, X);
            C = op(C, D, A, B, 10, 15, 55, I, X);
            B = op(B, C, D, A, 1, 21, 56, I, X);
            A = op(A, B, C, D, 8, 6, 57, I, X);
            D = op(D, A, B, C, 15, 10, 58, I, X);
            C = op(C, D, A, B, 6, 15, 59, I, X);
            B = op(B, C, D, A, 13, 21, 60, I, X);
            A = op(A, B, C, D, 4, 6, 61, I, X);
            D = op(D, A, B, C, 11, 10, 62, I, X);
            C = op(C, D, A, B, 2, 15, 63, I, X);
            B = op(B, C, D, A, 9, 21, 64, I, X);
            // Four additions
            A = (A + AA) & 0xFFFFFFFFL;
            B = (B + BB) & 0xFFFFFFFFL;
            C = (C + CC) & 0xFFFFFFFFL;
            D = (D + DD) & 0xFFFFFFFFL;
        }
        return new MD5Hash((int) A, (int) B, (int) C, (int) D);
    }
    
}
*/
