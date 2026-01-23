package Tb;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    public static void main(String[] args) {
        Controller c = new Controller();
        c.parseActionTypesStr("Login,updated,deleted,logout,added");
    }

    private List<ActionType> parseActionTypesStr(String actionTypesStr){
        System.out.println(actionTypesStr);
        List<ActionType> result = null;
        if(StringUtils.isNoneBlank(actionTypesStr)){
            String[] tmp = actionTypesStr.split(",");
            result = Arrays.stream(tmp).map(at ->  ActionType.valueOf(at.toUpperCase())).collect(Collectors.toList());
        }
        System.out.println(result);
        return result;
    }
}
