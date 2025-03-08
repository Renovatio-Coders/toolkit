package coders.renovatio.donghang.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource
import java.util.Properties


/**
 * 변경 commit을 위한 예시 1
 * 변경 commit을 위한 예시 2
 * 변경 commit을 위한 예시 3
 * 변경 commit을 위한 예시 4
 * 변경 commit을 위한 예시 5
 * 변경 commit을 위한 예시 6
 * 변경 commit을 위한 예시 7
 * 변경 commit을 위한 예시 8
 * */
@Configuration
class JpaConfig(private val env: Environment) { // Environment 객체를 주입받아 설정을 동적으로 로드

    @Bean
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.jpaVendorAdapter = HibernateJpaVendorAdapter() // Hibernate 설정 자동 적용

        val properties = Properties()

        // 애플리케이션 설정(application.properties)에서 Dialect를 동적으로 가져옴
        val dialect = env.getProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
        properties["hibernate.dialect"] = dialect

        // DDL 자동 생성 설정을 동적으로 가져옴
        properties["hibernate.hbm2ddl.auto"] = env.getProperty("spring.jpa.hibernate.ddl-auto", "none")

        em.setJpaProperties(properties)
        return em
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
