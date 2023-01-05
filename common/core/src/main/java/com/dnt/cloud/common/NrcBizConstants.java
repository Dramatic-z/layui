package com.dnt.cloud.common;


/**
 * <p>常量</p>
 * @author hazyhao
 *
 */
public class NrcBizConstants {

    // OAuth2.0 使用
    public static final String OAUTH2_APP_KEY           = "app_key";        // OAuth2的client_id, SDK中的app_id
    public static final String OAUTH2_RESPONSE_TYPE     = "response_type";  // response_type = code
    public static final String OAUTH2_GRANT_TYPE_KEY    = "grant_type";     // grant_type = access_token / grant_type = refresh_token
    public static final String OAUTH2_TYPE_CODE         = "code";           // code key or code value for oauth2 parameter
    public static final String OAUTH2_ACCESS_TOKEN      = "access_token";   // 
    public static final String OAUTH2_EXPIRES_IN        = "expires_in";     // timeout after expires_in
    public static final String OAUTH2_REFRESH_TOKEN     = "refresh_token";  // 
    public static final String OAUTH2_REDIRECT_URI      = "redirect_uri";   // 
    public static final String OAUTH2_RANDOM_CODE       = "random_code";    // 
    public static final String OAUTH2_USERNAME          = "username";       // 
    
    /**
     * 簽名類型
     */
    public static final String SIGN_TYPE        = "signType";

    /**
     * 具體簽名類型
     */
    public static final String SIGN_TYPE_RSA    = "RSA";
    public static final String SIGN_TYPE_MD5    ="MD5";
    public static final String SIGN_ALGORITHMS  = "SHA1WithRSA";

    
    public static final String APP_ID           = "appId";
    public static final String FORMAT           = "format";
    public static final String INFORMAT         = "informat";
    public static final String METHOD           = "method";
    public static final String TIMESTAMP        = "timestamp";
    public static final String RS_TIMESTAMP     = "rs_timestamp";
    public static final String VERSION          = "version";
    public static final String RS_SIGN_TYPE     = "rs_sign_type";
    public static final String SIGN             = "sign";
    public static final String SIGN_CONTENT     = "sign_content";
    public static final String OPEN_SDK         = "vfinopen_sdk";
    public static final String ACCESS_TOKEN     = "auth_token";
    public static final String TERMINAL_TYPE    = "terminal_type";
    public static final String TERMINAL_INFO    = "terminal_info";
    public static final String REMOTHOST        = "remothost";
    /**
     * 解析方式的 把MAP转成BEAN
     */
    public static final String MAPTOBEAN_KEY    = "vop_mapTobean";
    /** 默认时间格式 **/
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    
    public static final String CHARSET          = "charset";
    /**  文件上传，相关信息存放KEY **/
    public static final String FILE_KEY         = "fileBeanList";
    /** Date默认时区 **/
    public static final String DATE_TIMEZONE    = "GMT+8";
    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8     = "UTF-8";
    /** GBK字符集 **/
    public static final String CHARSET_GBK      = "GBK";
    /** JSON 应格式 **/
    public static final String FORMAT_JSON      = "json";
    /** XML 应格式 **/
    public static final String FORMAT_XML       = "xml";
    
    public static final String PROD_CODE        = "prod_code"; 
    public static final String RESPOEN_CODE     = "memo"; 
    public static final String CHANNEL_TYPE_PRODUCER  = "Producer";
    public static final String CHANNEL_TYPE_CONSUMER  = "Consumer";
    
}
