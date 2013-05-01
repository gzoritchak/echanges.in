hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

environments {
    production {
        dataSource {
            dbCreate = "none"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            uri = new URI(System.env.DATABASE_URL ?: "postgresql://echangesin:echangesin@localhost:5432/echangesin")
            url = "jdbc:postgresql://" + uri.host + uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
    development {
        dataSource {
            driverClassName = "org.postgresql.Driver"
            username = "echangesin"
            password = "echangesin"
            url = "jdbc:postgresql://localhost:5432/echangesin"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            hibernate.default_schema = "public"
            pooled = true
        }
    }
    test {
        dataSource {
            driverClassName = "org.h2.Driver"
            username = "sa"
            password = ""
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:testDb"
            pooled = false
        }
    }
}
