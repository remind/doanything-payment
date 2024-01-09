package cn.doanything.basic.application.mns.processor;

import cn.doanything.basic.domain.mns.MessageTemplate;
import cn.doanything.basic.domain.mns.repository.MessageTemplateRepository;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
public class AbstractMessageContentProcessor {

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;


    /**
     * 根据模板渲染
     * @param sceneCode
     * @param protocol
     * @param templateParam
     * @return
     */
    public String renderByTemplate(String sceneCode, Protocol protocol, Map<String, Object> templateParam) {
        MessageTemplate template = messageTemplateRepository.findBySceneCodeAndProtocol(sceneCode, protocol);
        AssertUtil.isNotNull(template, "消息模板不存在");
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
        Template templateEngine = engine.getTemplate(template.getContent());
        return templateEngine.render(templateParam);
    }
}
