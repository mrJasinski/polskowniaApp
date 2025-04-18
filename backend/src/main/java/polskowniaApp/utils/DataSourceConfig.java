package polskowniaApp.utils;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class DataSourceConfig
{
    @Bean
    public DataSource getDataSource()
    {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/testDb")
                .username("root")
                .build();
    }

}
