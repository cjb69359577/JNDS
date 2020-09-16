package com.jiuqi.jnds.cfpl6.util;

import java.util.UUID;

/**
 * <h1>UUIDUtils</h1>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 17:29
 */
public class UUIDUtils {


    public static UUID fromStringToUuid(String str) {
        if (str.length() != 32) {
            return UUID.fromString(str);
        } else {

            String s1 = "0x" + str.substring(0, 8);
            String s2 = "0x" + str.substring(9, 12);
            String s3 = "0x" + str.substring(13, 16);
            String s4 = "0x" + str.substring(17, 20);
            String s5 = "0x" + str.substring(21, 32);

            long mostSigBits = Long.decode(s1).longValue();
            mostSigBits <<= 16;
            mostSigBits |= Long.decode(s2).longValue();
            mostSigBits <<= 16;
            mostSigBits |= Long.decode(s3).longValue();

            long leastSigBits = Long.decode(s4).longValue();
            leastSigBits <<= 48;
            leastSigBits |= Long.decode(s5).longValue();

            return new UUID(mostSigBits, leastSigBits);
        }
    }
}
