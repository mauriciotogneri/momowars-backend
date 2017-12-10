package com.mauriciotogneri.momowars.templates;

import java.util.HashMap;
import java.util.Map;

public class ExampleTemplate extends BaseTemplate
{
    private final String foo;

    public ExampleTemplate(String foo)
    {
        super("example.html");

        this.foo = foo;
    }

    @Override
    public String render() throws Exception
    {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("foo", foo);

        return render(parameters);
    }
}