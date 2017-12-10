package com.mauriciotogneri.momowars.templates;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public abstract class BaseTemplate
{
    private final String templateName;

    public BaseTemplate(String templateName)
    {
        this.templateName = templateName;
    }

    protected String render(Map<String, Object> parameters) throws Exception
    {
        Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setClassForTemplateLoading(getClass(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = configuration.getTemplate(templateName);
        Writer writer = new StringWriter();
        template.process(parameters, writer);

        return writer.toString();
    }

    public abstract String render() throws Exception;
}