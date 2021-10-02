package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    // Http 요청 메시지가 오면 아래 메소드가 실행됨.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // URI 정보 꺼내고

        // map에서 맞는 Controller를 꺼낸다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //  <------------ 이하 V2 -> V3 달라진 부분 -------------->

        // 1. 개별 컨트롤러로 갔다오기. ( controller.process(papramMap); )
        // 그런데 여기서 paramMap의 이유는 개별 컨트롤러에서 HttpServletRequest 이런거 안 쓰려고
        // request에 있는걸 다 넣음
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap); // ㄹㅇ 개별 controller로 가서 ModelView 받아오는거

        // -- 여기 전후로 Controller 갔다가 ModelView 반환받고
        // -- viewResolver 호출해서 끝에 주소만 활용해 전체 주소를 받아옴.

        String viewName = mv.getViewName(); // 이름 꺼내고 (끝에 이름)
        MyView view = viewResolver(viewName); // ㄹㅇ 이름 변환 메소드 실행

        // 이제 보여주기.
        // 넘길때 모델까지 같이 넘김.
        view.render(mv.getModel(), request, response);
    }

    // request에 있는걸 다 꺼내서 Map으로 전달하기위해서
    // Map 객체 자체를 만들기 위한 메소드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        // request 객체에 들어있는 key와 value를 Map에 전부다 넣는다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

    // 끝에 주소만 알고있던걸 ㄹㅇ 주소로 바꿔주는 메소드
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
