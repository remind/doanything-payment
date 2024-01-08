package cn.doanything.basic.domain.mns.service;

import cn.doanything.basic.domain.mns.NotifyChannel;
import cn.doanything.basic.domain.mns.repository.NotifyChannelRepository;
import cn.doanything.basic.mns.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxj
 * 2024/1/7
 */
@Service
public class NotifyChannelDomainService {

    @Autowired
    private NotifyChannelRepository notifyChannelRepository;

    public NotifyChannel getDefault(Protocol protocol) {
        List<NotifyChannel> notifyChannels = notifyChannelRepository.findAll();
        return notifyChannels.stream().filter(notifyChannel ->
                notifyChannel.getProtocol().contains(protocol)
                && notifyChannel.getIsDefault()
        ).findFirst().orElse(null);
    }
}
