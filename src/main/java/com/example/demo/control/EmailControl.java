package com.example.demo.control;

import org.jboss.logging.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;





@Slf4j
@RestController
public class EmailControl {
	
	
	@RequestMapping(value = "emailSend")
    public String emailSendTest(@RequestParam String tos,@RequestParam String subject,@RequestParam String content) throws Exception{
			log.info(tos+subject+content);
        	String[] tolist = tos.split(",");
        	System.out.println("这个方法要执行了");
        	SendEmailUtil.sendMail("testSubject", tolist, new String []{}, content);
        return null;
    }
}
