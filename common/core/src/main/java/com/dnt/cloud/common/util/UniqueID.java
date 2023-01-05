package com.dnt.cloud.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnt.cloud.common.lang.StringUtil;

/**
 * <p>唯一ID生成器</p>
 * @author Eric.fu
 * @version $Id: UniqueID.java, v 0.1 2012-11-23 下午1:45:46 fuyangbiao Exp $
 */
public class UniqueID {
    private static final Logger log    = LoggerFactory.getLogger(UniqueID.class);
    private static char[]       digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f'   };

    private static UniqueID     me     = new UniqueID();
    private String              hostAddr;
    private Random              random = new SecureRandom();
    private MessageDigest       mHasher;
    private UniqueTimer         timer  = new UniqueTimer();

    private UniqueID() {
        try {
            InetAddress addr = InetAddress.getLocalHost();

            hostAddr = addr.getHostAddress();
        } catch (IOException e) {
            log.error("[UniqID] Get HostAddr Error", e);
            hostAddr = String.valueOf(System.currentTimeMillis());
        }

        if (StringUtil.isBlank(hostAddr) || "127.0.0.1".equals(hostAddr)) {
            hostAddr = String.valueOf(System.currentTimeMillis());
        }

        if (log.isDebugEnabled()) {
            log.debug("[UniqID]hostAddr is:" + hostAddr);
        }

        try {
            mHasher = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nex) {
            mHasher = null;
            log.error("[UniqID]new MD% Hasher error", nex);
        }
    }

    /**
     * 获取实例
     * @return
     */
    public static UniqueID getInstance() {
        return me;
    }

    /**
     * 获取唯一ID
     * @return
     */
    public String getUniqueID() {
        StringBuffer sb = new StringBuffer();
        long t = timer.getCurrentTime();

        sb.append(t);

        sb.append("-");

        sb.append(random.nextInt(8999) + 1000);

        sb.append("-");
        sb.append(hostAddr);

        sb.append("-");
        sb.append(Thread.currentThread().hashCode());

        if (log.isDebugEnabled()) {
            log.debug("[UniqID.getUniqID]" + sb.toString());
        }

        return sb.toString();
    }

    /**
     * 获取唯一ID哈希码
     * @return
     */
    public String getUniqueIDHash() {
        String id = getUniqueID();

        if (mHasher != null) {
            synchronized (mHasher) {
                byte[] bt = mHasher.digest(id.getBytes());
                int l = bt.length;

                char[] out = new char[l << 1];

                for (int i = 0, j = 0; i < l; i++) {
                    out[j++] = digits[(0xF0 & bt[i]) >>> 4];
                    out[j++] = digits[0x0F & bt[i]];
                }

                if (log.isDebugEnabled()) {
                    log.debug("[UniqID.getuniqIDHash]" + (new String(out)));
                }

                return new String(out);
            }
        } else {
            return id;
        }
    }

    private static class UniqueTimer {
        private long lastTime = System.currentTimeMillis();

        public synchronized long getCurrentTime() {
            long currTime = System.currentTimeMillis();

            lastTime = Math.max(lastTime + 1, currTime);

            return lastTime;
        }
    }
}
