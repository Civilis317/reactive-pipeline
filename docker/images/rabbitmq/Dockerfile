FROM rabbitmq:3.7.7-management

COPY rabbitmq.config /etc/rabbitmq/
COPY custom_definitions.json /etc/rabbitmq/

RUN rabbitmq-plugins enable rabbitmq_shovel rabbitmq_shovel_management

EXPOSE 5672 15672

CMD ["rabbitmq-server"]

