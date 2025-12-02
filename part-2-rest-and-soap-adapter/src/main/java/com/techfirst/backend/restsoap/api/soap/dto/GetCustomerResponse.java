package com.techfirst.backend.restsoap.api.soap.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "email"
})
@XmlRootElement(name = "getCustomerResponse", namespace = "http://techfirst.com/backend/restsoap/soap")
public class GetCustomerResponse {

    @XmlElement(namespace = "http://techfirst.com/backend/restsoap/soap")
    protected long id;
    @XmlElement(namespace = "http://techfirst.com/backend/restsoap/soap", required = true)
    protected String name;
    @XmlElement(namespace = "http://techfirst.com/backend/restsoap/soap", required = true)
    protected String email;

}