package com.test.springtest.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.test.springtest.jsonvalidate.JsonSchemaValidator;
import com.test.springtest.test.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonSchemaController {

    /**
     * 网址 https://www.jianshu.com/p/2e02212b59e3
     * @param user
     * @return
     */
    @RequestMapping(value = "/json-schema/validate", method = RequestMethod.POST)
    public Map<String, Object> jsonSchemaValidate(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();

        JsonNode jsonNode = JsonSchemaValidator.getJsonNodeFromString(JSONObject.toJSONString(user));
        if (jsonNode == null) {
            result.put("success", false);
            result.put("message", "json报文格式错误");
            return result;
        }

        String filePath = "static/json/format.json";


        JsonNode schemaNode = JsonSchemaValidator.getJsonNodeFromFile(filePath);
        if (schemaNode == null) {
            result.put("success", false);
            result.put("message", "json Schema文件不存在，无法校验！");
            return result;
        }
        return JsonSchemaValidator.validateJsonByFgeByJsonNode(jsonNode, schemaNode);
    }

}