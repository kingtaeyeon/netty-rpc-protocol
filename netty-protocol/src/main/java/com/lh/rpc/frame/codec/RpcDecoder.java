package com.lh.rpc.frame.codec;

import com.lh.rpc.frame.contants.ReqTypeEnum;
import com.lh.rpc.frame.contants.RpcContant;
import com.lh.rpc.frame.core.Header;
import com.lh.rpc.frame.core.RpcProtocol;
import com.lh.rpc.frame.core.RpcRequest;
import com.lh.rpc.frame.core.RpcResponse;
import com.lh.rpc.frame.serial.ISerializer;
import com.lh.rpc.frame.serial.SerializerManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RpcDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        log.info("=========begin RpcDecoder=========");

        if(in.readableBytes() < RpcContant.HEAD_TOTAL_LEN) {
            return;
        }
        in.markReaderIndex();
        /* 读取2个字节的magic */
        short magic = in.readShort();
        if(magic != RpcContant.MAGIC) {
            throw new IllegalArgumentException("Illegal request paramter 'magic'," + magic);
        }
        /* 读取1个字节的序列化类型 */
        byte serialType = in.readByte();
        /* 读取1个字节的消息类型 */
        byte reqType = in.readByte();
        /* 读取请求id */
        long requestId = in.readLong();
        /* 读取数据报文长度 */
        int dataLength = in.readInt();

        if(in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] content = new byte[dataLength];
        in.readBytes(content);
        Header header = new Header(magic, serialType, reqType, requestId, dataLength);
        ISerializer serializer = SerializerManager.getSerializer(serialType);
        ReqTypeEnum rt = ReqTypeEnum.findCode(reqType);
        switch (rt) {
            case REQUEST:
                RpcRequest request = serializer.deserialize(content, RpcRequest.class);
                RpcProtocol<RpcRequest> reqProtocol = new RpcProtocol<>();
                reqProtocol.setHeader(header);
                reqProtocol.setContent(request);
                out.add(reqProtocol);
                break;
            case RESPONSE:
                RpcResponse response = serializer.deserialize(content, RpcResponse.class);
                RpcProtocol<RpcResponse> resProtocol = new RpcProtocol<>();
                resProtocol.setHeader(header);
                resProtocol.setContent(response);
                out.add(resProtocol);
                break;
            case HEARTBEAT:
                // TODO
                break;
            default:
                break;
        }
    }
}
