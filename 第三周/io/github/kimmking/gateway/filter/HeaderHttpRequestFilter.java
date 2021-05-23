package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeaderHttpRequestFilter implements HttpRequestFilter {
    List<String> urls = new ArrayList<>();

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        urls.add(fullRequest.getUri());
        fullRequest.headers().set("mao", "soul");
    }
}
