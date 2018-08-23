package wechat.app.exceptionHandler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import wechat.app.constant.ConstantEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 捕获Controller层未捕获的异常
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public JSONObject handle(HttpServletRequest request,Throwable ex){
        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            param.put(key,value);
        }
        result.put("param",param.toJSONString());
        String exception = ex.toString();
        String exceptionType = exception.substring(0, exception.indexOf(":"));
        String exceptionDesc = exception.substring(exception.indexOf(":") + 1);
        result.put("exceptionType",exceptionType);
        result.put("exceptionDesc",exceptionDesc);
        result.put("message","发生未知全局异常");
        result.put("resCode",ConstantEnum.FAIL.getCode());
        logger.error("捕获到全局异常:\r\n参数: {}\r\n异常类型: {}\r\n异常描述: {}",param.toJSONString(),exceptionType,exceptionDesc);
        return result;
    }
}
