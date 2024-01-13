package cn.doanything.basic.application.md.builder;

import cn.doanything.basic.types.md.DataItem;
import cn.doanything.basic.facade.md.dto.DataItemAddRequest;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/13
 */
@Service
public class DataItemBuilder {

    public DataItem build(DataItemAddRequest request) {
        DataItem dataItem = new DataItem();
        dataItem.setCode(request.getCode());
        dataItem.setName(request.getName());
        dataItem.setMemo(request.getMemo());
        dataItem.setItemFields(request.getItemFields());
        return dataItem;
    }

}
