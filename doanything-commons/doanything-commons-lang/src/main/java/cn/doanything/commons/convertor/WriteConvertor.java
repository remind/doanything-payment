package cn.doanything.commons.convertor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2023/12/16
 */
public interface WriteConvertor<EntityType, DoType> {

    DoType toDo(EntityType entityType);

    default List<DoType> toDo(List<EntityType> entityTypes) {
        List<DoType> doTypes = new ArrayList<>();
        if (entityTypes != null) {
            entityTypes.forEach(entityType -> doTypes.add(toDo(entityType)));
        }
        return doTypes;
    }
}
