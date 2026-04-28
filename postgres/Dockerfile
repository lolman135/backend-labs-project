FROM postgres:17-alpine

ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=AdminAdmin

COPY init/init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432