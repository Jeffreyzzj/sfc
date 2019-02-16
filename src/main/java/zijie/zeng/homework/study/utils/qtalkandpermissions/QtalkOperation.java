package zijie.zeng.homework.study.utils.qtalkandpermissions;

import org.apache.ibatis.annotations.Param;

public interface QtalkOperation {
    /**
     * 根据qtalk判断权限
     * 如果返回-1，则说明没有此qtalk
     * @param qtalkOne
     * @return
     */
    int searchPermissionsByQtalk(String qtalkOne);
}
