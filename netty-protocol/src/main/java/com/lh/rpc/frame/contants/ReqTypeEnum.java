package com.lh.rpc.frame.contants;

public enum ReqTypeEnum {

    /** 请求 **/
    REQUEST((byte)1),
    /** 响应 **/
    RESPONSE((byte)2),
    /** 心跳 **/
    HEARTBEAT((byte)3);

    private byte code;

    ReqTypeEnum(byte code) {
        this.code = code;
    }

    public byte code() {
        return this.code;
    }

    public static ReqTypeEnum findCode(int code) {
        for(ReqTypeEnum reqTypeEnum : ReqTypeEnum.values()) {
            if(reqTypeEnum.code == code) {
                return reqTypeEnum;
            }
        }
        return null;
    }
}
