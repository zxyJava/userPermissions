package com.kwsinfo.ocam.maintenance.springSupport;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kws.security.config")
@Data
public class KwsSecurityConfigProperties {
}
