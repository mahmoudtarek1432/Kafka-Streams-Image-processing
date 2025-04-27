package Demo.KafkaStreams.Topology;

import Demo.KafkaStreams.Processes.TopicNameProcess;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SimpleProcessorTopology {
    @Bean
    public Topology CreateTopology(){
        StreamsBuilder builder = new StreamsBuilder();
        var topicList = new ArrayList<String>();
        topicList.add("dragonball-topic");
        topicList.add("sonic-topic");

        KStream sourceStream = builder.stream(topicList, Consumed.with(Serdes.String(),Serdes.String()));

        sourceStream.process(new TopicNameProcess()).to("topicNameProcessorSink");

        return builder.build();
    }

    private Topology processorTopology(){
        Topology topology = new Topology();
        topology.addSource("Source","dragonball-topic","sonic-topic")
                .addProcessor("topicNameProcessor",new TopicNameProcess(),"Source")
                .addSink("sink","topicNameProcessorSink","topicNameProcessor");
        return topology;
    }
}
