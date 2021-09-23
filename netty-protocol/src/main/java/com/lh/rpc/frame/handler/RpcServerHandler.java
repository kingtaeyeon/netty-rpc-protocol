package com.lh.rpc.frame.handler;

import com.lh.rpc.frame.contants.ReqTypeEnum;
import com.lh.rpc.frame.core.Header;
import com.lh.rpc.frame.core.RpcProtocol;
import com.lh.rpc.frame.core.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> msg) throws Exception {
        RpcProtocol resProtocol = new RpcProtocol();
        Header header = msg.getHeader();
        header.setReqType(ReqTypeEnum.RESPONSE.code());

    }

    private Object invoke(RpcRequest request) {
        return null;
    }
}
