package com.example.demo.resources;

import com.example.demo.entities.Message;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class FilteringResource {

    //use static filtering when you know that you have sensitive fields like passwords or ssn's in object that you dont want to be exposed
    @GetMapping("/static_filtering_message_entity")
    public Message filterMessage() {
        return new Message("1","2","hi there",new Date());
    }

    //use dynamic filtering when different actions require you to hide/show different parts of a bean
    @GetMapping("/dynamic_filtering_message_entity")
    public MappingJacksonValue filterDynamically() {
        Message message = new Message("1","2","hi there",new Date());
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("time");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("messagefilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(message);
        mappingJacksonValue.setFilters(filters);
        // i want to send fromuser, touser and msg
        return mappingJacksonValue;
    }

    @GetMapping("/dynamic_filtering_message_entity_2")
    public Message filterDynamically2() {
        // i want to send msg and timestamp
        return new Message("1","2","hi there",new Date());
        //todo
    }

}
