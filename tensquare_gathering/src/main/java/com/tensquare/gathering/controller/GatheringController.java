package com.tensquare.gathering.controller;

import com.tensquare.gathering.dto.GatheringDTO;
import com.tensquare.gathering.dto.SignUpRecordDTO;
import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.pojo.SignUpRecord;
import com.tensquare.gathering.service.GatheringService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
/**
 * 控制器层
 * @author Administrator
 *
 */
@Api(tags = "GatheringController",description = "活动操作")
@RestController
@RequestMapping("/gathering")
public class GatheringController {

	@Autowired
	private GatheringService gatheringService;

	/**
	 * 根据ID查询
	 * @param gatheringId
	 * @return
	 */
	@ApiOperation(value = "根据ID查询",notes = "根据ID查询")
	@GetMapping(value="/findGathingById")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "gatheringId", value = "活动Id", paramType = "query")
	})
	public Result findById(@RequestParam String gatheringId){
		return new Result(true,StatusCode.OK,"查询成功",gatheringService.findByGatheringId(gatheringId));
	}


	/**
	 * 分页+多条件查询
	 * @param param
	 * @return 分页结果
	 */
	@ApiOperation(value = "分页+多条件查询",notes = "分页+多条件查询")
	@PostMapping(value="/search")
	public Result findSearch(@RequestBody GatheringDTO param){
		Page<Gathering> pageList = gatheringService.findSearch(param);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Gathering>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
	 * 发布活动
	 * @param gathering
	 */
	@ApiOperation(value = "发布活动",notes = "发布活动")
	@PostMapping(value="/publish")
	public Result add(@RequestBody GatheringDTO gathering){
		gatheringService.add(gathering);
		return new Result(true,StatusCode.OK,"活动发布成功");
	}

	/**
	 * 修改
	 * @param gathering
	 */
	@ApiOperation(value = "发布活动",notes = "发布活动")
	@PostMapping(value="/update")
	public Result update(@RequestBody GatheringDTO gathering){
		gatheringService.update(gathering);
		return new Result(true,StatusCode.OK,"修改成功");
	}

    /**
     * 报名更新
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "报名更新",notes = "报名更新")
    @PostMapping(value="/signUp")
    public Result signUp(@RequestBody SignUpRecordDTO param) { // 加try catch
        gatheringService.addGatheringRecord(param);
        gatheringService.signUp(param.getGatheringId());
        return new Result(true,StatusCode.OK,"报名成功");
    }


    /**
	 * 查看报名记录
	 *
	 * @param param
	 * @return
	 */
    @ApiOperation(value = "查看报名记录",notes = "查看报名记录")
    @PostMapping(value="/findRecordByUserId")
	public Result findRecordByUserId(@RequestBody SignUpRecordDTO param) {
		return new Result(true,StatusCode.OK,"查询成功",gatheringService.findRecordByUserId(param));
	}

	/**
	 * 删除
	 * @param gatheringId
	 */
	@ApiOperation(value = "删除活动",notes = "删除活动")
	@GetMapping(value="/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "gatheringId", value = "活动Id", paramType = "query")
	})
	public Result delete(@RequestParam String gatheringId  ){
		gatheringService.delete(gatheringId);
		return new Result(true,StatusCode.OK,"删除成功");
	}

}
