package real.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        /**        뷰의 이름은 response/hello 그리고 addObject()메소드로 data에 hello값 실어서..
         *          그러면 hello.html의 <p th:text="${data}">empty</p> 문구에서 data 부분이 hello로 바뀐다. (타임리프)
         */
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
        return mav;
    }

    /**
     * [반환 타입이 String 일 때]
     * ResponseBody가 있다 -> response/hello의 뷰를 찾고 렌더링 (뷰 리졸버가..)
     * ResponseBody가 없다 -> 그냥 HTTP 메세지 바디에 바로 문자로 response/hello가 들어감 (뷰 리졸버 동작x)
     *
     * #예시
     * return "response/hello"      =====>       templates/response/hello.html 렌더링 ㄱㄱ
     */
    //@ResponseBody 쓰면 return에 있는게 그냥 Http응답 - Body 자리에 들어가는거.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        return "response/hello";    // 클래스에 @controller가 있으면서 string 반환하면 viewName이 되는거.
    }

    /**
     * [반환 타입이 void 일 때   (잘 권장되지 않는 방법 - 명시성이 떨어짐)]
     * 기존에 해왔던 HttpServletResponse나 OutputStream(Writer) 같은 게 없을 경우
     * 요청 들어온 URL 경로로 논리 뷰 이름을 씀.
     *
     * #예시
     * 요청 URL : "response/hello"      =====>       templates/response/hello.html 렌더링 ㄱㄱ
     */
    // 반환형이 void 이면서 Controller의 경로 이름과 뷰의 논리 이름이 같으면 그냥 여기서 요청들어온게 논리 뷰로 ㄱㄱ
    @RequestMapping("response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
