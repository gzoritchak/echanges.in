databaseChangeLog = {

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-1") {
        createTable(tableName: "communaute") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "communaute_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-2") {
        createTable(tableName: "ech_user") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ech_user_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "communaute_id", type: "int8")

            column(name: "mail", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_hash", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_salt", type: "bytea") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-3") {
        createTable(tableName: "echange") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "echange_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "donneur_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "porte_sur_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "receveur_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-4") {
        createTable(tableName: "echange_def") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "echange_def_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-5") {
        createTable(tableName: "echange_def_tags") {
            column(name: "tag_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "echange_def_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-6") {
        createTable(tableName: "permission") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "permission_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "access_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "communaute_id", type: "int8")

            column(name: "domain", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-7") {
        createTable(tableName: "role") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "role_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-8") {
        createTable(tableName: "tag") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tag_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "nom", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "parent_id", type: "int8")
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-9") {
        createTable(tableName: "user_permission") {
            column(name: "id", type: "int8") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_permission_pkey")
            }

            column(name: "version", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "permission_id", type: "int8") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-10") {
        addPrimaryKey(columnNames: "echange_def_id, tag_id", constraintName: "echange_def_tags_pkey", tableName: "echange_def_tags")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-11") {
        addUniqueConstraint(columnNames: "nom", constraintName: "communaute_nom_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "communaute")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-12") {
        addUniqueConstraint(columnNames: "mail", constraintName: "ech_user_mail_key", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ech_user")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-13") {
        addForeignKeyConstraint(baseColumnNames: "communaute_id", baseTableName: "ech_user", baseTableSchemaName: "public", constraintName: "fk27c943605e9b5c7b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "communaute", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-14") {
        addForeignKeyConstraint(baseColumnNames: "donneur_id", baseTableName: "echange", baseTableSchemaName: "public", constraintName: "fk8dac7455f31c3f8f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ech_user", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-15") {
        addForeignKeyConstraint(baseColumnNames: "porte_sur_id", baseTableName: "echange", baseTableSchemaName: "public", constraintName: "fk8dac7455d7627c76", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "echange_def", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-16") {
        addForeignKeyConstraint(baseColumnNames: "receveur_id", baseTableName: "echange", baseTableSchemaName: "public", constraintName: "fk8dac7455424f5cc5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ech_user", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-17") {
        addForeignKeyConstraint(baseColumnNames: "echange_def_id", baseTableName: "echange_def_tags", baseTableSchemaName: "public", constraintName: "fka327f0bd5b609530", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "echange_def", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-18") {
        addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "echange_def_tags", baseTableSchemaName: "public", constraintName: "fka327f0bdd5274b99", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tag", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-19") {
        addForeignKeyConstraint(baseColumnNames: "communaute_id", baseTableName: "permission", baseTableSchemaName: "public", constraintName: "fke125c5cf5e9b5c7b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "communaute", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-20") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "tag", baseTableSchemaName: "public", constraintName: "fk1bf9a8514a889", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tag", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-21") {
        addForeignKeyConstraint(baseColumnNames: "permission_id", baseTableName: "user_permission", baseTableSchemaName: "public", constraintName: "fk30ba72c35eb6a25b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "permission", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-22") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_permission", baseTableSchemaName: "public", constraintName: "fk30ba72c3234ca59b", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "ech_user", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
    }

    changeSet(author: "gzoritchak (generated)", id: "1356559172296-23") {
        createSequence(schemaName: "public", sequenceName: "hibernate_sequence")
    }
}