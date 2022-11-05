package com.yl.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author:letg(pz)
 * @Date: 2022/11/5 16:20
 * @Description:
 */


public class HelloServer {
    public static void main(String[] args) {
        //服务端的启动器,组装netty组件
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                //处理各种操作的处理器
                .childHandler(
                        //和客户端进行读写的通道
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    //连接调用后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //解码的，将传输过来的ByteBuffer转为String
                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {//自定义handler
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8999);
    }
}
