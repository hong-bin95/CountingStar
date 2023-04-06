package com.ssafy.countingstar.dto.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.apache.kafka.common.serialization.Serializer;
import java.io.ByteArrayOutputStream;

public class KryoSerializer<T> implements Serializer<T> {

    private final Kryo kryo = new Kryo();

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Output output = new Output(outputStream);
        kryo.writeObject(output, data);
        output.close();
        return outputStream.toByteArray();
    }
}