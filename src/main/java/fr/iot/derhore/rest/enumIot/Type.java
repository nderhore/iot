package fr.iot.derhore.rest.enumIot;

import com.fasterxml.jackson.annotation.JsonProperty;


public enum Type {

    @JsonProperty("AMPOULE")
    AMPOULE,
    @JsonProperty("TEMPERATURE")
    TEMPERATURE
}
