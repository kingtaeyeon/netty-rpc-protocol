package com.lh.rpc.frame.serial;

import com.alibaba.fastjson.JSON;
import com.lh.rpc.frame.contants.SerialTypeEnum;

public class JsonSerializer implements ISerializer{
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }

    @Override
    public byte getType() {
        return SerialTypeEnum.JSON_SERIAL.code();
    }
}
