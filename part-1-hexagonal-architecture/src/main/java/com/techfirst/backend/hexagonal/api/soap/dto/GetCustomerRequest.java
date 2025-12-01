package com.techfirst.backend.hexagonal.api.soap.dto;

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
    "id"
})
@XmlRootElement(name = "getCustomerRequest", namespace = "http://techfirst.com/backend/hexagonal/soap")
public class GetCustomerRequest {

    @XmlElement(namespace = "http://techfirst.com/backend/hexagonal/soap")
    protected long id;

}