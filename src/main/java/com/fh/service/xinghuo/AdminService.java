package com.fh.service.xinghuo;

import com.fh.entity.huoxing.TbAppKey;
import com.fh.entity.huoxing.TbAppVersion;

import java.util.List;

public interface AdminService {
    List<TbAppKey> addOrUpdateAppKey(List<TbAppKey> tbAppKeyList);
    List<TbAppKey> findAll(String appName);

    String updateAppkeyExpiration(String dateStr, String appKey, String appName);

    String updateAppVersion(String appName, String version, String fileName);

    TbAppVersion findAppVersionByName(String appName);
}
