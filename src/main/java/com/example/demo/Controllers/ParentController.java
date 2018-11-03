package com.example.demo.Controllers;

import com.example.demo.Beans.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/parent")
public class ParentController {
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Parent postParent(@RequestBody Map<String, Integer> requestBody) {
        return new Parent(requestBody.get("ParentId"));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Parent getParentById(@RequestParam Integer parentId) {
        return new Parent(parentId);
    }

}
