package com.example.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy     // Spring AOP proxy support
@ComponentScan("com.example.aopdemo")
public class DemoConfig {


}
