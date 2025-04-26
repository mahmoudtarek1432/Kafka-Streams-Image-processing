package Demo.KafkaStreams.Processes;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.ProcessorSupplier;
import org.apache.kafka.streams.processor.api.Record;

public class TopicNameProcess implements ProcessorSupplier<String,String,String,String> {


    @Override
    public Processor<String, String, String, String> get() {
        return new Processor<String, String, String, String>() {

            ProcessorContext context;

            @Override
            public void init(final ProcessorContext<String, String> context) {

                this.context = context;

            }

            @Override
            public void process(Record<String, String> record) {
                context.forward(new Record(record.key(),
                                           String.format("%s:%s",this.context.recordMetadata().get().topic(),record.value()),
                                           context.currentSystemTimeMs()));
            }
        };
    }
}
