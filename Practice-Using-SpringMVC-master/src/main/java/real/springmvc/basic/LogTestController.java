package real.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**

 그냥 @Controller 붙였으면 string 반환했을때  view의 이름이 봔환되는거.
 반면에, @RestController 붙였으면 그냥 순수하게 string 반환됨.

 *log.method(); 했을 때
 [쓰레드가 실행한거] 무슨 컨트롤러인지 : 메시지 내용
 이러한 format으로 출력
 
 작성 중 {} 가 각각 변수로 치환됨. 마치 printf에서 서식문자처럼

 레벨 별로 출력도 가능
 **/

@Slf4j
@RestController
public class LogTestController {

    /** 여기 로그 선언문 **/
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        log.debug("String concat log=" + name);
        return "ok";
    }

}
