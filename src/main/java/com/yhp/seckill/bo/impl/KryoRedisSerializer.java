package com.yhp.seckill.bo.impl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.yhp.seckill.exception.SeckillException;

/**
 * @desc kryo序列化器
 * 
 * @author yuhuiping
 * @version v1.0 
 * @date 2017年10月20日下午5:47:31
 */
public class KryoRedisSerializer<T> implements RedisSerializer<T> {
	

	
	private static final int DEFAULT_CAPACITY = 4096;
	
	private static final int DEFAULT_MAX_CAPACITY = -1;

	private static final Logger log = LoggerFactory.getLogger(KryoRedisSerializer.class);
	
	private final Queue<Kryo> pool = new ConcurrentLinkedQueue<Kryo>();

	protected Kryo createKryo() {
        Kryo kryo = new Kryo();
//		kryo.setRegistrationRequired(false);
//		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.setReferences(false);
//      kryo.setWarnUnregisteredClasses(true);//可以提示某类未被注册，以警告的方式输出
		kryo.register(GregorianCalendar.class);
		kryo.register(BigDecimal.class);
		kryo.register(BigInteger.class);
		kryo.register(Pattern.class);
		kryo.register(BitSet.class);
		kryo.register(URI.class);
		kryo.register(UUID.class);
		kryo.register(HashMap.class);
		kryo.register(List.class);
		kryo.register(ArrayList.class);
		kryo.register(LinkedList.class);
		kryo.register(HashSet.class);
		kryo.register(TreeSet.class);
		kryo.register(Hashtable.class);
		kryo.register(Date.class);
		kryo.register(Calendar.class);
		kryo.register(ConcurrentHashMap.class);
		kryo.register(SimpleDateFormat.class);
		kryo.register(Vector.class);
		kryo.register(BitSet.class);
		kryo.register(StringBuffer.class);
		kryo.register(StringBuilder.class);
		kryo.register(Object.class);
		kryo.register(Object[].class);
		kryo.register(String[].class);
		kryo.register(byte[].class);
		kryo.register(char[].class);
		kryo.register(int[].class);
		kryo.register(float[].class);
		kryo.register(double[].class);
        return kryo;
    }
	
    public void returnKryo(Kryo kryo) {
        pool.offer(kryo);
    }

    public void close() {
        pool.clear();
    }

    public Kryo getKryo() {
        Kryo kryo = pool.poll();
        if (kryo == null) {
            kryo = createKryo();
        }
        return kryo;
    }
	

	@Override
	public byte[] serialize(T t) throws SerializationException {
		Output output = new Output(DEFAULT_CAPACITY,DEFAULT_MAX_CAPACITY);
		Kryo kryo = getKryo();
		try{
			kryo.writeClassAndObject(output, t);
			return output.toBytes();
		}catch(Exception e){
			log.error("Kryo serialize error!", e);
			throw new SeckillException("Kryo serialize error!");
		}finally{
			returnKryo(kryo);
			output.close();
			output = null;
		}
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null){
			return null;
		}
		Input input = new Input(new ByteArrayInputStream(bytes), bytes.length);
		Kryo kryo = getKryo();
		try{
			return (T)kryo.readClassAndObject(input);
		}catch(Exception e){
			log.error("Kryo deserialize error!", e);
			throw new SeckillException("Kryo deserialize error!");
		}finally{
			returnKryo(kryo);
			input.close();
			input = null;
		}
	}

}

