package real.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import real.springmvc.basic.HelloData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @ResponseBody    - 클래스 레벨에 ResponseBody를 붙이면 내부 전체 메소드에 붙게 되는 효과를 가진다.
// @RestController - @Controller + @ReponseBody
@Slf4j
@Controller
public class ResponseBodyController {

    /** 이건 그냥 서블릿 때 처럼 **/
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }


    /**
     *  [Http 응답 메시지 Body에 그냥 단순히 text를 넣기 - 방법 1]
     *  return시  HttpEntity나 ResponseEntity를 쓰면 쉽게 가능.
     *
     *
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * [Http 응답 메시지 Body에 그냥 단순히 text 넣기 - 방법 2]
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return"ok";
    }


    /**
     * [Http 응답 메세지에 JSON 형식 넣기 - 방법 1]
     * ResponseEntity 이용
     *
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");             // "userName : userA"
        helloData.setAge(20);                       // "Age : 20"

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * [Http 응답 메세지에 JSON 형식 넣기 - 방법 2]
     * ResponseBody 이용
     *
     * Rest Api 형식일때 해당 형태 많이 씀
     */
    @ResponseStatus(HttpStatus.OK)          // 애노태에션은 상태코드를 동적으로 변경할 수는 없다.
    @ResponseBody                           // 클래스 레벨에 ResponseBody를 붙이면 내부 전체 메소드에 붙게 되는 효과를 가진다.
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}
