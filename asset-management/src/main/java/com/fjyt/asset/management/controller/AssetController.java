package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.service.AssetService;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.utils.AssetUtils;
import com.fjyt.asset.management.utils.JwtUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.fjyt.asset.management.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月17日 14:59
 * 资产信息Controller
 */
@RestController
@RequestMapping("/assetInfo")
public class AssetController {
//    @Value("${applicationCredentials.appkey}")
//    private String appkey;
//    @Value("${applicationCredentials.appsecret}")
//    private String appsecret;
//
//    @GetMapping("/getFormNacos")
//    public ConcurrentHashMap<String,String> testNacos(){
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String,String>();
//        map.put("appkey",appkey);
//        map.put("appsecret",appsecret);
//        System.out.println("钉钉应用appkey:"+appkey+"\n"+
//                "钉钉应用appsecret:"+appsecret);
//        return map;
//    }

    @Autowired
    private AssetService assetService;
    /**
     * 获取资产信息列表
     * @param assetDTO
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(AssetDTO assetDTO){
        PageHelper.startPage(assetDTO.getPageNum(),assetDTO.getPageSize());
        List<AssetVO>  list = assetService.list(assetDTO);
        return new PageHelperUtil().resultInfo(list);
    }

    /**
     * 根据id获取资产信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getAssetById(@PathVariable Long id){
        return assetService.getAssetById(id);
    }

    /**
     * 新增资产
     * @param assetDTO
     * @return
     */
    @PostMapping
    public R add(@RequestBody AssetDTO assetDTO){
        return assetService.add(assetDTO);
    }

    /**
     * 编辑资产
     * @param assetDTO
     * @return
     */
    @PutMapping
    public R update(@RequestBody AssetDTO assetDTO){
        return assetService.update(assetDTO);
    }

    /**
     * 删除资产
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        return assetService.delete(id);
    }
    /**
     * 获取资产状态列表
     */
    @GetMapping("/status")
    public R statusList(){
        return R.ok(AssetUtils.statusList());
    }

    /**
     * 获取资产创建方式列表
     * @return
     */
    @GetMapping("/createWay")
    public R createWayList(){
        return R.ok(AssetUtils.createWayList());
    }
}
