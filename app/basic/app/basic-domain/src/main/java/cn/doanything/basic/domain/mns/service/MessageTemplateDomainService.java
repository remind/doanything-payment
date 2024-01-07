package cn.doanything.basic.domain.mns.service;

import cn.doanything.basic.domain.mns.MessageAuthCode;
import cn.doanything.basic.domain.mns.MessageTemplate;
import cn.doanything.basic.domain.mns.repository.MessageTemplateRepository;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxj
 * 2024/1/7
 */
@Service
public class MessageTemplateDomainService {

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    public void render(MessageAuthCode messageAuthCode) {
        MessageTemplate template = messageTemplateRepository.findBySceneCodeAndProtocol(messageAuthCode.getSceneCode(), messageAuthCode.getProtocol());
        AssertUtil.isNotNull(template, "消息模板不存在");
        Map<String, Object> data = new HashMap<>();
        data.put("authCode", messageAuthCode.getAuthCode());
        data.put("validMinute", messageAuthCode.getValidMinute());
        messageAuthCode.setContent(renderByTemplate(template.getContent(), data));
    }

    /**
     * 根据模板渲染
     * @param templateContent   模板内容
     * @param templateParam     模板参数
     * @return
     */
    private String renderByTemplate(String templateContent, Map<String, Object> templateParam) {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
        Template template = engine.getTemplate(templateContent);
        return template.render(templateParam);
    }

}
