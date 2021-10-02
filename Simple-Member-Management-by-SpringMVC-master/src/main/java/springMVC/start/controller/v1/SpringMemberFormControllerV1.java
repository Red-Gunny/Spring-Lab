package springMVC.start.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 빈 등록
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")       // 해당 URL에 요청이 들어오면
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
