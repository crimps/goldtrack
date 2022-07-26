package com.crimps.goldtrack.util;

import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * <p>标题： 忽略SSL证书工具类 </p>
 * <p>功能： 忽略SSL证书工具类 </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>类全名：com.crimps.goldtrack.util.OkHttpClient</p>
 * <p>
 * 作者：crimps
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class OkHttpClientUtils {
    /**
     * 获取OkHttpClient
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // 过时方法，单构造参数方法过时，多传一个X509TrustManager就可以了
            //builder.sslSocketFactory(sslSocketFactory);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);

            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.connectTimeout(30, TimeUnit.SECONDS);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}