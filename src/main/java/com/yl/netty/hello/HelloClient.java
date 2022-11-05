package com.yl.netty.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author:letg(pz)
 * @Date: 2022/11/5 16:52
 * @Description:
 */


public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端启动器
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                //处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    //连接调用后被调用
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //对发送的数据编码
                        ch.pipeline().addLast(new StringEncoder());

                    }
                })
                .connect("localhost", 8999)
                .sync()
                .channel()
                .writeAndFlush("hello world");
        ;

    }
}
