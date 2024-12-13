package com.codingnomads.corespring.examples.autowiredannotation;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("acerSoundCard")
@ToString
public class AcerSoundCard implements SoundCard{
}
