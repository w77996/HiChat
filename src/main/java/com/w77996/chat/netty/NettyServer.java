package com.w77996.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyServer implements InitializingBean, DisposableBean {
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;

    @Autowired
    private NettyServerChannelInitializer serverChannelInitializer;

    public void start(int port) {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(serverChannelInitializer);

        // ChannelFuture future;
        try {
            log.info("===start socket:" + port);
            b.bind(port).sync();
            // future = b.bind(port).sync();
            // System.out.println("????222");
            // future.channel();
            // System.out.println("????3333");
            // future.channel().closeFuture();
            // System.out.println("????4444");
            // future.channel().closeFuture().sync();
            // System.out.println("????5555");
        } catch (InterruptedException e) {
            log.error("socket InterruptedException.", e);
        }
    }

    public void afterPropertiesSet() throws Exception {
        start(7777);
    }

    public void destroy() throws Exception {
        // 释放资源
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }

        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
}
