package com.codingnomads.corespring.examples.autowiredannotation;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("digitalSoundCard")
@ToString
public class DigitalSoundCard implements SoundCard {
}
