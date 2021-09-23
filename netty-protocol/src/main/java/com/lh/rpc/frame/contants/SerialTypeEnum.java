package com.lh.rpc.frame.contants;

public enum SerialTypeEnum {

    JSON_SERIAL((byte)1),
    JAVA_SERIAL((byte)2);

    private byte code;

    SerialTypeEnum(byte code) {
        this.code = code;
    }

    public byte code() {
        return this.code;
    }
}
