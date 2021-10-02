package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{

    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }
    public void setUrl(String url) {
        this.url = url;
    }
    //서비스 시작 시 호출.
    @PostConstruct
    public void init() {
        System.out.println("connect: " + url);
    }
    // 서버에 메시지 던지는 역할
    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }
    @PreDestroy
    public void close() {
        System.out.println("close : " + url);
    }


}
