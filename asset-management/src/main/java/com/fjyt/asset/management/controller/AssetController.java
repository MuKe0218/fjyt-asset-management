package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.enums.AssetEnum;
import com.fjyt.asset.management.service.AssetService;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.utils.JwtUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.fjyt.asset.management.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        System.out.println(userName);
        PageHelper.startPage(assetDTO.getPageNum(),assetDTO.getPageSize());
        List<AssetVO>  list = assetService.list(assetDTO);
        return new PageHelperUtil().resultInfo(list);
    }

    /**
     * 新增资产
     * @param asset
     * @return
     */
    @PostMapping
    public R add(@RequestBody Asset asset){
        String assetCode = "1231464644923";
        System.out.println(asset);
        return R.ok(assetCode,"新增资产接口");
    }

    /**
     * 编辑资产
     * @param asset
     * @return
     */
    @PutMapping
    public R update(@RequestBody Asset asset){
        System.out.println(asset);
        return R.ok("修改资产接口");
    }

    /**
     * 删除资产
     * @param assetId
     * @return
     */
    @DeleteMapping("/{assetId}")
    public R delete(@PathVariable Long assetId){
        return R.ok();
    }
    /**
     * 获取资产状态列表
     */
    @GetMapping("/status")
    public R statusList(){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for (int i=0 ;i<6;i++){
            Map map = new Hashtable();
            String label = null;
            String value = null;
            switch (i){
                case 0:
                   value = AssetEnum.IDLE.getCode();
                   label = AssetEnum.IDLE.getInfo();
                   break;
                case 1:
                    value = AssetEnum.USE.getCode();
                    label = AssetEnum.USE.getInfo();
                   break;
                case 2:
                    value = AssetEnum.BORROW.getCode();
                    label = AssetEnum.BORROW.getInfo();
                    break;
                case 3:
                    value = AssetEnum.EXAMINE_AND_APPROVE.getCode();
                    label = AssetEnum.EXAMINE_AND_APPROVE.getInfo();
                    break;
                case 4:
                    value = AssetEnum.MAINTENANCE.getCode();
                    label = AssetEnum.MAINTENANCE.getInfo();
                    break;
                default:
                    value = AssetEnum.SCRAP.getCode();
                    label = AssetEnum.SCRAP.getInfo();
                    break;
            }
            map.put("value",value);
            map.put("label",label);
            list.add(map);
        }
        return R.ok(list);
    }
}
