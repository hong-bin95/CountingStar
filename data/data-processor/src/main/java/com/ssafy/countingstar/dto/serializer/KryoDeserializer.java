package com.ssafy.countingstar.dto.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.ByteArrayInputStream;

public class KryoDeserializer<T> implements Deserializer<T> {

    private final Class<T> targetType;
    private final Kryo kryo;

    public KryoDeserializer(Class<T> targetType) {
        this.targetType = targetType;
        this.kryo = new Kryo();
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        Input input = new Input(inputStream);
        T deserializedObject = kryo.readObject(input, targetType);
        input.close();
        return deserializedObject;
    }
}