package real.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import real.springmvc.basic.HelloData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    /** 이건 거의 기존에 했던 방식 - HttpServlet의 getParameter() 메소드 사용 **/
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /** @RequestParam을 이용.
     * ()내부에는 필드 이름. 없을거면 변수명과 필드이름이 일치해야함. **/
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /** @RequestParam을 이용.
     * 변수명과 필드이름이 일치하여 "( ~~ )"를 생략하였음. **/
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /** 여기서는 @RequestParam 마저 없앰.
     * 이를 위한 전제 조건은 파라미터 변수명이 헤더의 파라미터 필드명과 일치해야함. **/
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /** @ModelAttribute 이거 요청 파라미터를 객체에 알아서 넣어줌
     *   대신 전제조건은 해당 클래스의 게터, 세터 등이 먼저 있어야함 **/
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={} age={}", helloData.getUsername(), helloData.getAge());
        //log.info("helloname={}", helloData);
        return "ok";
    }

    /** @ModelAttribute 마저 생략 가능 **/
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={} age={}", helloData.getUsername(), helloData.getAge());
        //log.info("helloname={}", helloData);
        return "ok";
    }
}
