package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class Rpcfx {

    public static <T> T create(final Class<T> serviceClass, final String url) {
        // 0. 替换动态代理 -> AOP
        //return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url));
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new AopMethodInterceptor(url));
        return (T) enhancer.create();
    }

    public static class AopMethodInterceptor implements MethodInterceptor {

        private final String url;

        public <T> AopMethodInterceptor(String url) {
            this.url = url;
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
            /*OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MediaType.get("application/json; charset=utf-8"), JSON.toJSONString(req)))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: " + respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);*/
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: " + reqJson + "   url=" + url);
            URI uri = null;
            HttpSnoopClientHandler httpSnoopClientHandler = new HttpSnoopClientHandler();
            try {
                uri = new URI(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            // 2.尝试使用httpclient或者netty client

            // Configure the client.
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new HttpSnoopClientInitializer(httpSnoopClientHandler));

                // Make the connection attempt.
                Channel ch = null;
                ch = b.connect(uri.getHost(), uri.getPort()).sync().channel();
                // Prepare the HTTP request.
                FullHttpRequest request = new DefaultFullHttpRequest(
                        HttpVersion.HTTP_1_1, HttpMethod.POST, uri.getRawPath());
                request.headers().set(HttpHeaderNames.HOST, uri.getHost());
                request.headers().add(HttpHeaderNames.CONTENT_TYPE, "application/json");
                request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
                ByteBuf bbuf = Unpooled.copiedBuffer(reqJson, StandardCharsets.UTF_8);
                request.headers().set(HttpHeaderNames.CONTENT_LENGTH, bbuf.readableBytes());
                request.content().writeBytes(bbuf);

                // Send the HTTP request.
                ch.writeAndFlush(request);

                // Wait for the server to close the connection.
                ch.closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return JSON.parseObject(httpSnoopClientHandler.getRespStr(), RpcfxResponse.class);
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(o.getClass().getName().split("\\$\\$")[0]);
            request.setMethod(method.getName());
            request.setParams(objects);
            RpcfxResponse response = post(request, url);
            return JSON.parse(response.getResult().toString());
        }
    }

    public static class RpcfxInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;

        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            RpcfxResponse response = post(request, url);

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException, URISyntaxException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: " + reqJson + "   url=" + url);
            URI uri = new URI(url);
            // 2.尝试使用httpclient或者netty client

            // Configure the client.
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                HttpSnoopClientHandler httpSnoopClientHandler = new HttpSnoopClientHandler();
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new HttpSnoopClientInitializer(httpSnoopClientHandler));

                // Make the connection attempt.
                Channel ch = b.connect(uri.getHost(), uri.getPort()).sync().channel();
                // Prepare the HTTP request.
                FullHttpRequest request = new DefaultFullHttpRequest(
                        HttpVersion.HTTP_1_1, HttpMethod.POST, uri.getRawPath());
                request.headers().set(HttpHeaderNames.HOST, uri.getHost());
                request.headers().add(HttpHeaderNames.CONTENT_TYPE, "application/json");
                request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
                ByteBuf bbuf = Unpooled.copiedBuffer(reqJson, StandardCharsets.UTF_8);
                request.headers().set(HttpHeaderNames.CONTENT_LENGTH, bbuf.readableBytes());
                request.content().writeBytes(bbuf);

                // Send the HTTP request.
                ch.writeAndFlush(request);

                // Wait for the server to close the connection.
                ch.closeFuture().sync();

                return JSON.parseObject(httpSnoopClientHandler.getRespStr(), RpcfxResponse.class);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Shut down executor threads to exit.
                group.shutdownGracefully();
            }
            return null;

//            // 1.可以复用client
//            // 2.尝试使用httpclient或者netty client
//            OkHttpClient client = new OkHttpClient();
//            final Request request = new Request.Builder()
//                    .url(url)
//                    .post(RequestBody.create(JSONTYPE, reqJson))
//                    .build();
//            String respJson = client.newCall(request).execute().body().string();
//            System.out.println("resp json: " + respJson);
//            return JSON.parseObject(respJson, RpcfxResponse.class);
        }
    }
}
