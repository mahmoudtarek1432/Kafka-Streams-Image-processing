package Demo.KafkaStreams.Topology;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class KStreamTopology {
    KStreamTopology(){

    }


    public Topology CreateTopology(){
        StreamsBuilder builder = new StreamsBuilder();

        KStream singleStream = builder.stream("A.topic", Consumed.with(Serdes.String(),Serdes.String()));
        KStream multipleStream = builder.stream("B.topic", Consumed.with(Serdes.String(),Serdes.String()));

        singleStream.to("singleStream");
        multipleStream.to("multipleStream");
        return builder.build();
    }
}
