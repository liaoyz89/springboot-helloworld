package com.gazgeek.helloworld.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    String home() {
        Long id = this.genRegId("9999");
        return "Hello from " + id;
        //return "Hello from GazGeek!";
    }
    
    public Long genRegId(String acctNo){
                logger.debug("----------generateRegId acctNo={}----------", acctNo);
                DateFormat df = new SimpleDateFormat("yyDS");
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);
                StringBuffer sb = new StringBuffer(acctNo.length() > 7 ?acctNo.substring(acctNo.length()-7):acctNo);
                sb.append(hour*60*60+minute*60+second);

                sb.append(df.format(c.getTime()).substring(1));
                String regId = sb.toString();
                
                logger.debug("---------生产registerId={}", regId);
                return Long.parseLong(regId);
        }

}
