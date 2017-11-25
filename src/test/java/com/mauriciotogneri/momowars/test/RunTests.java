package com.mauriciotogneri.momowars.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {
                RunTests.PATH + "1.session.feature",
                RunTests.PATH + "2.account.feature",
                RunTests.PATH + "3.game.feature"
        }
)
public class RunTests
{
    static final String PATH = "src/test/resources/com/mauriciotogneri/momowars/test/";
}