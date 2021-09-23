package com.lh.rpc.frame.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Header implements Serializable {

    /**
     * 魔数 2字节
     */
    private short magic;
    /**
     * 序列化类型  1个字节
     */
    private byte serialType;
    /**
     * 消息类型  1个字节
     */
    private byte reqType;
    /**
     * 请求id  8个字节
     */
    private long requestId;
    /**
     * 消息体长度  4个字节
     */
    private int length;

}
