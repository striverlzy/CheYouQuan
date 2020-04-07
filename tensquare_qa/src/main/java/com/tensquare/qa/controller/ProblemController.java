//package com.tensquare.qa.controller;
//import java.util.List;
//import java.util.Map;
//
//import com.tensquare.qa.client.LabelClient;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tensquare.qa.pojo.Problem;
//import com.tensquare.qa.service.ProblemService;
//
//import entity.PageResult;
//import entity.Result;
//import entity.StatusCode;
///**
// * 控制器层
// * @author Administrator
// *
// */
//@Api(tags = "ProblemController",description = "问答操作")
//@RestController
//@CrossOrigin
//@RequestMapping("/problem")
//public class ProblemController {
//
//	@Autowired
//	private ProblemService problemService;
//
//
//	@Autowired
//	private LabelClient labelClient;
//
//	@ApiOperation(value = "根据标签ID查询",notes = "根据标签ID查询")
//	@RequestMapping(value = "/label/{labelid}")
//	public Result findLabelById(@PathVariable String labelid){
//		Result result = labelClient.findById(labelid);
//		return result;
//	}
//
//	/**
//	 * 根据标签ID查询最新问题列表
//	 * @param labelid
//	 * @return
//	 */
//	@ApiOperation(value = "根据标签ID查询最新问题列表",notes = "根据标签ID查询最新问题列表")
//	@RequestMapping(value="/newlist/{labelid}/{page}/{size}",method=RequestMethod.GET)
//	public Result findNewListByLabelId(@PathVariable String labelid,@PathVariable int page,@PathVariable int size ){
//		Page<Problem> pageList = problemService.findNewListByLabelId(labelid, page, size);
//		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
//		return new Result(true, StatusCode.OK, "查询成功",pageResult);
//	}
//
//	/**
//	 * 根据标签ID查询热门问题列表
//	 * @param labelid
//	 * @param page
//	 * @param size
//	 * @return
//	 */
//	@ApiOperation(value = "根据标签ID查询热门问题列表",notes = "根据标签ID查询热门问题列表")
//	@RequestMapping(value="/hotlist/{labelid}/{page}/{size}",method=RequestMethod.GET)
//	public Result findHotListByLabelId(@PathVariable String labelid,@PathVariable int page,@PathVariable int size ){
//		Page<Problem> pageList = problemService.findHotListByLabelId(labelid, page, size);
//		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
//		return new Result(true, StatusCode.OK, "查询成功",pageResult);
//	}
//
//	/**
//	 * 根据标签ID查询等待回答列表
//	 * @param labelid
//	 * @param page
//	 * @param size
//	 * @return
//	 */
//	@ApiOperation(value = "根据标签ID查询等待回答列表",notes = "根据标签ID查询等待回答列表")
//	@RequestMapping(value="/waitlist/{labelid}/{page}/{size}",method=RequestMethod.GET)
//	public Result findWaitListByLabelId(@PathVariable String labelid,@PathVariable int page,@PathVariable int size ){
//		Page<Problem> pageList = problemService.findWaitListByLabelId(labelid, page, size);
//		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
//		return new Result(true, StatusCode.OK, "查询成功",pageResult);
//	}
//
//
//	/**
//	 * 查询全部数据
//	 * @return
//	 */
//	@ApiOperation(value = "查询全部数据",notes = "查询全部数据")
//	@RequestMapping(method= RequestMethod.GET)
//	public Result findAll(){
//		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
//	}
//
//	/**
//	 * 根据ID查询
//	 * @param id ID
//	 * @return
//	 */
//	@ApiOperation(value = "根据ID查询",notes = "根据ID查询")
//	@RequestMapping(value="/{id}",method= RequestMethod.GET)
//	public Result findById(@PathVariable String id){
//		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
//	}
//
//
//	/**
//	 * 分页+多条件查询
//	 * @param searchMap 查询条件封装
//	 * @param page 页码
//	 * @param size 页大小
//	 * @return 分页结果
//	 */
//	@ApiOperation(value = "分页+多条件查询",notes = "分页+多条件查询")
//	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
//	public Result findAll(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
//		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
//		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
//	}
//
//	/**
//     * 根据条件查询
//     * @param searchMap
//     * @return
//     */
//	@ApiOperation(value = "根据条件查询",notes = "根据条件查询")
//    @RequestMapping(value="/search",method = RequestMethod.POST)
//    public Result findSearch( @RequestBody Map searchMap){
//        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
//    }
//
//	/**
//	 * 增加
//	 * @param problem
//	 */
//	@ApiOperation(value = "增加",notes = "增加")
//	@RequestMapping(method=RequestMethod.POST)
//	public Result add(@RequestBody Problem problem  ){
//		problemService.add(problem);
//		return new Result(true,StatusCode.OK,"增加成功");
//	}
//
//	/**
//	 * 修改
//	 * @param problem
//	 */
//	@ApiOperation(value = "修改",notes = "修改")
//	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
//	public Result update(@RequestBody Problem problem, @PathVariable String id ){
//		problem.setId(id);
//		problemService.update(problem);
//		return new Result(true,StatusCode.OK,"修改成功");
//	}
//
//	/**
//	 * 删除
//	 * @param id
//	 */
//	@ApiOperation(value = "删除",notes = "删除")
//	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
//	public Result delete(@PathVariable String id ){
//		problemService.deleteById(id);
//		return new Result(true,StatusCode.OK,"删除成功");
//	}
//
//}
