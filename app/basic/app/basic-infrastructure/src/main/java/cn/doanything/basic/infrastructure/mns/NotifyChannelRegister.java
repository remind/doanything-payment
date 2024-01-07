package cn.doanything.basic.infrastructure.mns;

import cn.doanything.basic.domain.mns.channel.NotifyChannelAdapter;
import cn.doanything.basic.infrastructure.mns.channel.DefaultNotifyChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通知渠道注册
 * @author wxj
 * 2024/1/7
 */
@Configuration
public class NotifyChannelRegister {

    @Bean
    DefaultNotifyChannel defaultNotifyChannel(NotifyChannelAdapter adapter) {
        DefaultNotifyChannel notifyChannel = new DefaultNotifyChannel();
        adapter.register("default", notifyChannel);
        return notifyChannel;
    }

}
