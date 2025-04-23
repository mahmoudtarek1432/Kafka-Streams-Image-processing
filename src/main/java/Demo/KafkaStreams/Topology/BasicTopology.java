package Demo.KafkaStreams.Topology;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BasicTopology {
    BasicTopology(){

    }

    @Bean
    public Topology CreateTopology(){
        StreamsBuilder builder = new StreamsBuilder();

        return builder.build();
    }
}
