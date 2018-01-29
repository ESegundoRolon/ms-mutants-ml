package org.github.erolon.configuration;

import java.io.File;
import java.util.UUID;
import org.dizitart.no2.Nitrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseConfiguration.class);

	@Bean
	public Nitrite nitriteDataBase(){
		String dataDir = System.getProperty("java.io.tmpdir") + File.separator + "nitrite" + File.separator + "data";
		File file = new File(dataDir);
        if (!file.exists()) 
            file.mkdirs();
        String fullPath = file.getPath() + File.separator + UUID.randomUUID().toString() + ".db";
        LOGGER.info("Base de datos embebida sin volumen mapeado : {}", fullPath);
        return Nitrite.builder()
		        .compressed()
		        .filePath(fullPath)
		        .openOrCreate("user", "password");
	}



}
