package com.coding.args;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArgsTest {


    @Test
    public void should_is_correct_args_str() {
        assertThat(new Args().parser("asdfasd"),is("args is not correct args"));
    }

    @Test
    public void should_have_correct_schema() {
        assertThat(new Args().parser("printlogs -l"),is("args is not have correct schema"));
        assertThat(new Args().parser("printlogs -l -p 8080 -d /usr/logs"),is("args is not have correct schema"));
    }

    @Test
    public void should_return_correct_argument_str() {
        assertThat(new Args().parser("printlog -l -p 8080 -d /usr/logs"),is("logging=True port=8080 directory=/usr/logs "));
    }
}