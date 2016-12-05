package uk.ac.brighton.uni.ch629.catshop;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("uk.ac.brighton.uni.ch629.catshop.data")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = {"uk.ac.brighton.uni.ch629.catshop.data"})
public class WebAppConfig extends WebMvcConfigurerAdapter {
    private static final String
            DATABASE_DRIVER = "db.driver",
            DATABASE_PASSWORD = "db.password",
            DATABASE_URL = "db.url",
            DATABASE_USERNAME = "db.username",

    HIBERNATE_DIALECT = "hibernate.dialect",
            HIBERNATE_SHOW_SQL = "hibernate.show_sql",
            HIBERNATE_FORMAT_SQL = "hibernate.format_sql",
            HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto",

    ENTITY_MANAGER_PACKAGES_TO_SCAN = "entity-manager.packages-to-scan";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.
                getRequiredProperty(ENTITY_MANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setMappingResources("Entities.hbm.xml");
        entityManagerFactoryBean.setJpaProperties(hibProperties());
//        entityManagerFactoryBean.setMappingResources("Product.hbm.xml", "Order.hbm.xml", "OrderProduct.hbm.xml");

        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
        properties.put(HIBERNATE_SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
        properties.put(HIBERNATE_FORMAT_SQL, env.getRequiredProperty(HIBERNATE_FORMAT_SQL));
        properties.put(HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/META-INF/resources/public/");
    }
}
