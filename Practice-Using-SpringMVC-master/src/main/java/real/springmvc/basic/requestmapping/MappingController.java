package real.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    //private Logger log = LoggerFactory.getLogger(getClass());

    /** @RequestMapping에 method 직접 추가하는 방식 - @GetMapping이나 똑같음) **/
    @RequestMapping(value = "/method-mapping", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("method mapping");
        return "ok";
    }

    /** 경로변수 2개 사용 방식 - Http api에서 자주 쓰임 **/
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /** Get 쿼리 파라미터에서 mode == debug 일때만 아래 메소드 실행 **/
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /** Http 요청메세지의 헤더 부분에서 mode == debug 일때만 아래 메소드 실행 **/
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /** Http 요청 메세지에서 Content-Type이 json 일때만 아래 메소드 실행 **/
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /** 컨트롤러가 만들어내는 결과물의 Content-Type이 HTML 형식일 때 아래 메소드 실행
     *  Http 요청 메세지에서 Accept 헤더를 기반으로 매핑됨                       **/
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
