databaseChangeLog = {
    changeSet(author: "gzoritchak", id: "add-code-to-communaute") {

        addColumn(tableName: "communaute") {
            column(name: "code", type: "varchar(255)")
        }

        grailsChange {
            change {
                sql.execute("update communaute set code = nom")
            }
            rollback {
            }
        }
        addNotNullConstraint(tableName: "communaute", columnName: "code")
    }
}