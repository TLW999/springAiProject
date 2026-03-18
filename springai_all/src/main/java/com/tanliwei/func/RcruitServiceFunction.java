package com.tanliwei.func;


import java.util.function.Function;

public class RcruitServiceFunction implements Function<RcruitServiceFunction.Request, RcruitServiceFunction.Response> {
    @Override
    public Response apply(Request request) {
        String position="未知";
        if(request.name.contains("张三")){
            position="算法工程师";
        }
        return new Response(position);
    }

    public record Request(String name){ }
    public record Response(String position){ }
}
