package com.gazgeek.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    String home() {
        Long id = this.genRegId("123456");
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
