package com.DockerTest.sjoh;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ServerHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);


    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {
            System.out.printf("New Client Connect! Connected IP : %s, Port : %d\n",
                    socket.getInetAddress(), socket.getPort());
                OutputStream out = socket.getOutputStream();

            InputStream in = socket.getInputStream();

            //일반적으로 헤더는 라인단위로 구성이 되있기때문에 라인단위로 데이터를 읽어야한다.
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            //byte[] httpBody = GetHttpUrl.getHttpBf(br);
//            if(httpBody == null){ //404에러일때
//                DataOutputStream dos = new DataOutputStream(out);
//                response404Header(dos, "/errorPage/error404.html");

            //}else{
                //DataOutputStream dos = new DataOutputStream(out);
                //response200Header(dos, httpBody.length);
                //responseBody(dos, httpBody);
            //}

                DataOutputStream dos = new DataOutputStream(out);
                byte[] body = "Hello World".getBytes();

                dos.writeBytes("HTTP/1.1 200 OK \r\n");
                dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
                dos.writeBytes("Content-Length: " + body.length + "\r\n");
                dos.writeBytes("\r\n");

                dos.write(body, 0, body.length);
                dos.writeBytes("\r\n");
                dos.flush();


            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }



    private void response404Header(DataOutputStream dos, String location) {
        try {
            dos.writeBytes("HTTP/1.1 404 Not Found \r\n");
            dos.writeBytes("Connection: close\r\n");
            dos.writeBytes("\r\n");
            dos.writeBytes("<h1>404 Not Found</h1>");
//            dos.writeBytes("Location: /errorPage/error404.html\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    }

