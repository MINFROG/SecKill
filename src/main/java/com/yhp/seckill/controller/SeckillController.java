package com.yhp.seckill.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhp.seckill.bo.SeckillBo;
import com.yhp.seckill.enums.SeckillStatEnum;
import com.yhp.seckill.exception.RepeatKillException;
import com.yhp.seckill.exception.SeckillCloseException;
import com.yhp.seckill.vo.Exposer;
import com.yhp.seckill.vo.Seckill;
import com.yhp.seckill.vo.SeckillExecution;
import com.yhp.seckill.vo.SeckillResult;
/**
 * @desc 秒杀接入层
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年9月27日下午1:38:05
 */
//@RestController
@Controller
@RequestMapping("/seckill")//url:模块/资源/{}/细分
public class SeckillController {
	
	@Autowired
	private SeckillBo seckillBo;
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET )
	public String queryforList(Model model){
		List<Seckill> list = seckillBo.querySeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value = "/{seckillId}/detail" , method = RequestMethod.GET )
	public String queryforDetail(@PathVariable("seckillId")Long seckillId,Model model){
		 if (seckillId == null){
            return "redirect:/seckill/list";
        }
		Seckill seckill = seckillBo.queryById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	 //ajax ,json暴露秒杀接口的方法
	@ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId)
    {
        SeckillResult<Exposer> result;
        try{
            Exposer exposer=seckillBo.exportSeckillUrl(seckillId);
            result=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e)
        {
            e.printStackTrace();
            result=new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }
    
    
    
    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone",required = false) Long userPhone){
        if (userPhone==null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution execution = seckillBo.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (RepeatKillException e1)
        {
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e2)
        {
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch (Exception e)
        {
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }

    }

    //获取系统时间
    @ResponseBody
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time(){
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
    

}

