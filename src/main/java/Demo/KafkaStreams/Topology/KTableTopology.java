package Demo.KafkaStreams.Topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KTableTopology {
    //@Bean
    public Topology CreateTopology(){
        StreamsBuilder builder = new StreamsBuilder();
        Materialized animeMaterialized = Materialized.as("anime-quote");
        Materialized gameMaterialized = Materialized.as("game-quote");
        KTable simpleConsumer = builder.table("sonic-topic");
        KTable materializedConsumer = builder.table("dragonball-topic", animeMaterialized);
        KTable simpleConsumerSerde = builder.table("naruto-topic", Consumed.with(Serdes.String(),Serdes.String()));
        KTable materializedConsumerSerde = builder.table("megaman-topic", Consumed.with(Serdes.String(),Serdes.String()), gameMaterialized);

        simpleConsumer.toStream().to("simpleConsumer");
        materializedConsumer.toStream().to("materializedConsumer");
        simpleConsumerSerde.toStream().to("simpleConsumerSerde");
        materializedConsumerSerde.toStream().to("materializedConsumerSerde");

        return builder.build();
    }
}
