package org.zzd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;

/**
 * @author :zzd
 * @date : 2023-02-26 15:37
 */
@SpringBootApplication(exclude = {FreeMarkerAutoConfiguration.class})
public class RbacStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacStartApplication.class, args);
    }
}
