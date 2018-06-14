package com.example.demo.resources;

import com.example.demo.versioning.PersionV1;
import com.example.demo.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningResource {

    //Way 1 - URI versioning
    @GetMapping("v1/person")
    public PersionV1 getPersonV1() {
        return new PersionV1("bhuvan chandra");
    }


    @GetMapping("v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2("bhuvan","chandra");
    }

    //Way 2 = differentiating based on request parameters
    //person/param?version=1
    @GetMapping(value = "/person/param", params = "version=1")
    public PersionV1 paramV1() {
        return new PersionV1("bhuvan chandra");
    }

    //person/param?version=2
    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2("bhuvan","chandra");
    }


    //Way 3 - DIFFERENTIATION based on headers
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersionV1 headerVersioning1() {
        return new PersionV1("bhuvan chandra");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerVersioning2() {
        return new PersonV2("bhuvan","chandra");
    }


    //Way 4 - media type versioing or content negotiation or accept versioning uses produces
    //Accept : application/vnd.company.app-v1+json
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersionV1 ProducesV1() {
        return new PersionV1("bhuvan chandra");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV2 ProducesV2() {
        return new PersonV2("bhuvan","chandra");
    }



}
