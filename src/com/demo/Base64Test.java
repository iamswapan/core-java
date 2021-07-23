package com.demo;

import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) {
        String str="eyJkIjp7IkVycm9yIjpbeyJCVHJuUmVmTm8iOm51bGwsIlRyblJlZk5vX1BhcnRuZXIiOiJNU0lMX0xPQU5fMDAwNjQ4IiwiUHJvZHVjdCI6bnVsbCwiZXJyb3JfY29kZSI6IjIwMDQiLCJlcnJvcl9tZXNzYWdlIjoiSW52YWxpZCBUeG4gUmVmIE5vLiB-IEludmFsaWQgVHhuIFJlZiBOby4iLCJTdGF0dXMiOiJGYWlsZWQiLCJ1ZGZfMSI6InVkZl8xIiwidWRmXzIiOiJ1ZGZfMiIsInVkZl8zIjoidWRmXzMiLCJ1ZGZfNCI6InVkZl80IiwidWRmXzUiOiJ1ZGZfNSIsInVkZl82IjoidWRmXzYiLCJ1ZGZfNyI6InVkZl83IiwidWRmXzgiOiJ1ZGZfOCIsInVkZl85IjoidWRmXzkiLCJ1ZGZfMTAiOiJ1ZGZfMTAiLCJ1ZGZfMTEiOiJ1ZGZfMTEiLCJ1ZGZfMTIiOiJ1ZGZfMTIiLCJ1ZGZfMTMiOiJ1ZGZfMTMiLCJ1ZGZfMTQiOiJ1ZGZfMTQiLCJ1ZGZfMTUiOiJ1ZGZfMTUifV19fQ";
        String str1="eyJkIjp7IlJlc3BvbnNlIjpbeyJCVHJuUmVmTm8iOiJIREZDMDAwMDAwNTE4OCIsIlRyblJlZk5vX1BhcnRuZXIiOiIwMDA2NDgiLCJQcm9kdWN0IjoiWkQiLCJlcnJvcl9jb2RlIjoiMjAwMyIsImVycm9yX21lc3NhZ2UiOiJMb2FuIGFscmVhZHkgYXZhaWxlZCBmb3IgUHJvZHVjdCAtIFpEIE1vYmlsZU5vIC0gOTk3OTU4MzY4MSBJZGVudGlmaWVyIE5hbWUgLSBQQU5DYXJkTm8gSWRlbnRpZmllciBWYWx1ZSAtIEFZRVBLMzMzOUEiLCJTdGF0dXMiOiJGYWlsZWQiLCJMb2FuQW1vdW50IjoiIiwiVGVub3IiOiIiLCJFTUkiOiIiLCJNb2JpbGVObyI6IiIsInVkZl8xIjoidWRmXzEiLCJ1ZGZfMiI6InVkZl8yIiwidWRmXzMiOiJ1ZGZfMyIsInVkZl80IjoidWRmXzQiLCJ1ZGZfNSI6InVkZl81IiwidWRmXzYiOiJ1ZGZfNiIsInVkZl83IjoidWRmXzciLCJ1ZGZfOCI6InVkZl84IiwidWRmXzkiOiJ1ZGZfOSIsInVkZl8xMCI6InVkZl8xMCIsInVkZl8xMSI6InVkZl8xMSIsInVkZl8xMiI6InVkZl8xMiIsInVkZl8xMyI6InVkZl8xMyIsInVkZl8xNCI6InVkZl8xNCIsInVkZl8xNSI6InVkZl8xNSJ9XX19";
        System.out.print(new String(Base64.getUrlDecoder().decode(str.getBytes())));
        System.out.println("=====================================================");
        System.out.print(new String(Base64.getUrlDecoder().decode(str1.getBytes())));
        for(char ch:str.toCharArray()){
            try {
//                System.out.print(Base64.getDecoder().decode(str.getBytes("UTF-8")));
//                System.out.print(new String(Base64.getUrlDecoder().decode(str.getBytes())));
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                System.out.println(String.valueOf(ch));
            }
        }
    }
}
