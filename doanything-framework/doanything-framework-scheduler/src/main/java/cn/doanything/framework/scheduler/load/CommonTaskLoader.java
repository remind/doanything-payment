package cn.doanything.framework.scheduler.load;

/**
 * Created by 2023/12/13
 *
 * @author wxj
 * @version V1.0
 * @description:任务加载器
 */
public interface CommonTaskLoader {

    /**
     * 加载并分发
     * @return
     */
    int loadAndDistribute();
}
