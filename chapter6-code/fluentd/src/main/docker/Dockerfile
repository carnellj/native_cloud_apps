FROM fluent/fluentd
USER fluent
WORKDIR /home/fluent
ENV PATH /home/fluent/.gem/ruby/2.3.0/bin:$PATH
RUN gem install fluent-plugin-secure-forward
#RUN apt-get install make
#RUN apt-get install -y gcc
#RUN apt-get install -y ruby-full
#RUN mkdir -p /usr/local/organizationservice
#RUN gem install fluentd
RUN gem install fluent-plugin-elasticsearch
ADD fluentd.conf /home/fluent
EXPOSE 24224
EXPOSE 9800
#VOLUME /fluentd/log

CMD fluentd -c /home/fluent/fluentd.conf -vv

