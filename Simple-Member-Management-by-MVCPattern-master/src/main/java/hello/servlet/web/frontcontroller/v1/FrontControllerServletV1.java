package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    이게 프론트 컨트롤러
    URL 들어왔을때 처음 처리.
    urlPattern 보면 끝에 * 있는거. 주목
    일단 얘가 호출되게 끔

    [종속관계]
    FrontController  ->  ControllerV1
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 맵으로써 맞는 거 맵핑되게 끔
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    // 일단 생성자에 맵핑정보 저장~
    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청이 와서 URI를 꺼내본다
        String requestURI = request.getRequestURI();

        // 요청온 URI와 맵핑 정보에 맞는 걸 함 꺼내본다.
        ControllerV1 controller = controllerV1Map.get(requestURI);
        if(controller == null) {        // 맞는게 없으면
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);       // 404 상태코드로 응답
            return;
        }

        // 맞는걸 찾았으니 컨트롤러의 process() 메소드 실행
        controller.process(request, response);
    }
}
