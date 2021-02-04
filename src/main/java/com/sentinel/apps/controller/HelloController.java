package com.sentinel.apps.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * sentinel用法演示
 *
 * @author Lenovo
 */
@RestController
@RequestMapping("/sents")
@Slf4j
public class HelloController {
    /**
     * 演示sentinel的用法
     *
     * @return 欢迎字符串.
     */
    @ResponseBody
    @GetMapping("/show")
    @SentinelResource(value = "show", fallback = "showHelloFallBack", blockHandler = "showHelloBlock")
    public String show() {
        return "hello sentinel";
    }

    /**
     * fallback方法
     *
     * @return showHelloFallBack.
     */
    public String showHelloFallBack() {
        log.info("执行showHelloFallBack()方法");
        return "showHelloFallBack";
    }

    /**
     * fallback方法
     *
     * @return showHelloFallBack.
     */
    public String showHelloBlock(BlockException ex) {
        log.error("执行showHelloBlock()方法:{}", ex);
        return ex.getMessage();
    }

}
