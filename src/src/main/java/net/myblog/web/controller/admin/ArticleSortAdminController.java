package net.myblog.web.controller.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import net.myblog.entity.ArticleSort;
import net.myblog.service.ArticleSortService;
import net.myblog.web.controller.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @description 博客标签(类别)信息的控制类
 * @author Nicky
 * @date 2017年3月7日
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/articleSort")
public class ArticleSortAdminController extends BaseController{

	Logger LOGGER = LoggerFactory.getLogger(ArticleSortAdminController.class);

	@Autowired ArticleSortService articleSortService;

	/**
	 * 跳转到博客类别列表页面
	 * @return
	 */
	@RequestMapping("/listAll")
	public String listAllSort(Model model){
		List<ArticleSort> articleSorts = articleSortService.findAll();
		model.addAttribute("articleSorts", articleSorts);
		return "myblog/index";
	}
	
	/**
	 * 获取所有的博客标签(分类)信息到后台管理系统
	 * @param model
	 * @return
	 */
	@RequestMapping("/labellist")
	public String labelList(Model model){
		List<ArticleSort> articleSorts = articleSortService.findAll();
		model.addAttribute("articleSorts", articleSorts);
		return "admin/label/label_list";
	}
	
	/**
	 * 加载所有的博客类别
	 * @return
	 */
	@RequestMapping(value = "/listArticleCategory" ,method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String listArticleCategory() {
		List<ArticleSort> categoryList = this.articleSortService.findAll();
		LOGGER.info("博客类别:{}"+categoryList.size());
		//ObjectMapper mapper = new ObjectMapper();
		for(ArticleSort a : categoryList) {
			System.out.println(a.getName());
		}
		String result = JSONArray.toJSONString(categoryList);
		return result;
	}
	
}
