#!/bin/sh
sqlite3 p01.db < DB_SCHEMA.sql
sqlite3 p01.db < DB_INSERTS.sql