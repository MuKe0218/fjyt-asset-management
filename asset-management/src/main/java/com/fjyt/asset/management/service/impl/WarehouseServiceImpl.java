package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.*;
import com.fjyt.asset.management.POJO.DTO.WarehouseDTO;
import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.WarehouseDetailVO;
import com.fjyt.asset.management.POJO.VO.WarehouseVO;
import com.fjyt.asset.management.constant.Constants;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.exception.WarehouseException;
import com.fjyt.asset.management.mapper.AddressMapper;
import com.fjyt.asset.management.mapper.WarehouseMapper;
import com.fjyt.asset.management.service.WarehouseService;
import com.fjyt.asset.management.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月15日 14:16
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private AddressMapper addressMapper;
    /**
     * 查询仓库列表
     * @param warehouseQueryDTO
     * @return
     */
    @Override
    public List<WarehouseVO> warehouseList(WarehouseQueryDTO warehouseQueryDTO) {

        return warehouseMapper.warehouseList(warehouseQueryDTO);
    }

    /**
     * 查询仓库列表没有分页
     * @return
     */
    @Override
    public R warehouseListWithoutPage() {
        return null;
    }

    /**
     * 根据ID查询仓库信息
     * @param id
     * @return
     */
    @Override
    public WarehouseDetailVO warehouseInfo(Long id) {
        return warehouseMapper.warehouseInfo(id);
    }

    /**
     * 新增仓库
     * @param warehouseDTO
     * @return
     */
    @Override
    public R addWarehouse(WarehouseDTO warehouseDTO) {
        //判断仓库是否已存在
        Warehouse checkWarehouse = warehouseMapper.checkByWarehouseName(warehouseDTO.getWarehouseName());
        if (StringUtils.isNotNull(checkWarehouse)){
            throw new WarehouseException(Constants.FAIL,"此仓库已存在");
        }
        //拼接地址得到详细地址
        StringBuffer address = new StringBuffer();
        if (StringUtils.isNotNull(warehouseDTO.getProvinceId())){
            List<Province> province = addressMapper.getProvince(new Province(warehouseDTO.getProvinceId()));
            if (province.size() < 1){
                throw new WarehouseException(Constants.FAIL,"省份不能为空");
            }
            address.append(province.get(0).getName());
        }
        if (StringUtils.isNotNull(warehouseDTO.getCityId())){
            List<City> city = addressMapper.getCity(new City(warehouseDTO.getCityId()));
            if (city.size() < 1){
                throw new WarehouseException(Constants.FAIL,"城市不能为空");
            }
            address.append(city.get(0).getName());
        }
        if (StringUtils.isNotNull(warehouseDTO.getCountyId())){
            List<County> county = addressMapper.getCounty(new County(warehouseDTO.getCountyId()));
            if(county.size() < 1){
                throw new WarehouseException(Constants.FAIL,"区县不能为空");
            }
            address.append(county.get(0).getName());
        }
        address.append(warehouseDTO.getSpecificPosition());
        //新增仓库
        Warehouse warehouse = new Warehouse();
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        String warehouseCode = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.WAREHOUSE_CK);
        warehouse.setWarehouseCode(warehouseCode);
        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
        warehouse.setProvinceId(warehouseDTO.getProvinceId());
        warehouse.setCityId(warehouseDTO.getCityId());
        warehouse.setCountyId(warehouseDTO.getCountyId());
        warehouse.setSpecificPosition(warehouseDTO.getSpecificPosition());
        warehouse.setAddress(address.toString());
        warehouse.setRemark(warehouseDTO.getRemark());
        warehouse.setCreateUser(userName);
        warehouse.setCreateTime(DateUtils.getNowDate());
        warehouse.setUpdateUser(userName);
        warehouse.setUpdateTime(DateUtils.getNowDate());
        warehouseMapper.addWarehouse(warehouse);
        return R.ok("新增成功");
    }

    /**
     * 修改仓库
     * @param warehouseDTO
     * @return
     */
    @Override
    public R updateWarehouse(WarehouseDTO warehouseDTO) {
        //判断仓库名是否有修改
        String warehouseName = warehouseMapper.warehouseInfo(warehouseDTO.getId()).getWarehouseName();
        if (!warehouseName.equals(warehouseDTO.getWarehouseName())) {
            //判断修改的仓库名是否已存在
            Warehouse checkWarehouse = warehouseMapper.checkByWarehouseName(warehouseDTO.getWarehouseName());
            if (StringUtils.isNotNull(checkWarehouse)){
                throw new WarehouseException(Constants.FAIL,"此仓库已存在");
            }
        }
        //拼接地址得到详细地址
        StringBuffer address = new StringBuffer();
        if (StringUtils.isNotNull(warehouseDTO.getProvinceId())){
            List<Province> province = addressMapper.getProvince(new Province(warehouseDTO.getProvinceId()));
            if (province.size() < 1){
                throw new WarehouseException(Constants.FAIL,"省份不能为空");
            }
            address.append(province.get(0).getName());
        }
        if (StringUtils.isNotNull(warehouseDTO.getCityId())){
            List<City> city = addressMapper.getCity(new City(warehouseDTO.getCityId()));
            if (city.size() < 1){
                throw new WarehouseException(Constants.FAIL,"城市不能为空");
            }
            address.append(city.get(0).getName());
        }
        if (StringUtils.isNotNull(warehouseDTO.getCountyId())){
            List<County> county = addressMapper.getCounty(new County(warehouseDTO.getCountyId()));
            if(county.size() < 1){
                throw new WarehouseException(Constants.FAIL,"区县不能为空");
            }
            address.append(county.get(0).getName());
        }
        address.append(warehouseDTO.getSpecificPosition());
        //修改仓库
        Warehouse warehouse = new Warehouse();
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        warehouse.setId(warehouseDTO.getId());
        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
        warehouse.setProvinceId(warehouseDTO.getProvinceId());
        warehouse.setCityId(warehouseDTO.getCityId());
        warehouse.setCountyId(warehouseDTO.getCountyId());
        warehouse.setSpecificPosition(warehouseDTO.getSpecificPosition());
        warehouse.setAddress(address.toString());
        warehouse.setRemark(warehouseDTO.getRemark());
        warehouse.setUpdateUser(userName);
        warehouse.setUpdateTime(DateUtils.getNowDate());
        warehouseMapper.updateWarehouse(warehouse);
        return R.ok("编辑成功");
    }

    /**
     * 修改状态
     * @param warehouseDTO
     * @return
     */
    @Override
    public R updateStatus(WarehouseDTO warehouseDTO) {
        //判断是否为禁用操作
        if (warehouseDTO.getStatus().equals('1')){
            //判断仓库中是否存在资产
            List<AssetWarehouse> assetWarehouses = warehouseMapper.assetAndWarehouseByWareCode(warehouseDTO.getId());
            if (assetWarehouses.size() > 0){
                throw new WarehouseException(Constants.FAIL,"此仓库中存有资产，无法禁用");
            }
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseDTO.getId());
        warehouse.setStatus(warehouseDTO.getStatus());
        warehouseMapper.updateStatus(warehouse);
        return R.ok("状态修改成功");
    }

    /**
     * 删除仓库
     * @param id
     * @return
     */
    @Override
    public R deleteWarehouse(Long id) {
        //判断仓库中是否存在资产
        List<AssetWarehouse> assetWarehouses = warehouseMapper.assetAndWarehouseByWareCode(id);
        if (assetWarehouses.size() > 0){
            throw new WarehouseException(Constants.FAIL,"此仓库中存有资产，无法删除");
        }
        //删除仓库
        warehouseMapper.deleteWarehouse(id);
        return R.ok("删除成功");
    }
}
