package polskowniaApp.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
class MultiPartConfig
{
    @Bean
    public MultipartResolver multipartResolver()
    {
        return new StandardServletMultipartResolver();
    }


//    @Bean
//    public StandardServletMultipartResolver multipartResolver()
//    {
//        var multipart = new StandardServletMultipartResolver();
//        multipart.setMaxUploadSize(3 * 1024 * 1024);
//
//        return multipart;
//    }
//
//    @Bean
//    @Order(0)
//    public MultipartFilter multipartFilter()
//    {
//        var multipartFilter = new MultipartFilter(); multipartFilter.setMultipartResolverBeanName("multipartResolver");
//        return multipartFilter;
//    }
}
