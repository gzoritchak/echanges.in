dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    username = "echangesin"
    password = "echangesin"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            url = "jdbc:postgresql://localhost:5432/echangesin"
            hibernate.default_schema = "public"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            pooled = true
            }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            hibernate.default_schema = "public"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            pooled = true
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            url = "jdbc:postgresql://localhost:5432/echangesin"
            hibernate.default_schema = "public"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            pooled = true
        }
    }
}
