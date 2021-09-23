package com.lh.rpc.frame.codec;

import com.lh.rpc.frame.core.Header;
import com.lh.rpc.frame.core.RpcProtocol;
import com.lh.rpc.frame.serial.ISerializer;
import com.lh.rpc.frame.serial.SerializerManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<Object>> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol<Object> msg, ByteBuf out) throws Exception {
        log.info("========== begin RpcEncoder ==========");
        Header header = msg.getHeader();
        out.writeShort(header.getMagic());
        out.writeByte(header.getSerialType());
        out.writeByte(header.getReqType());
        out.writeLong(header.getRequestId());
        ISerializer serializer = SerializerManager.getSerializer(header.getSerialType());
        byte[] data = serializer.serialize(msg.getContent());
        header.setLength(data.length);
        out.writeInt(data.length);
        out.writeBytes(data);
    }
}
